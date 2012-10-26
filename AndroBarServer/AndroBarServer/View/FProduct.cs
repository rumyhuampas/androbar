using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using AndroBarServer.Controller;
using AndroBarServer.Properties;
using AndroBarServer.Model;
using System.IO;

namespace AndroBarServer.View
{
    public partial class FProduct : Form
    {
        private Product _controller;
        private bool _imageChanged;
        private int _prodId;
        BackgroundWorker productLoader;
        IEnumerable<dynamic> dgvCategories = null;
        IEnumerable<dynamic> comboCategories = null;
        DataGridViewImageColumn deleteButtonColumn;

        public FProduct()
        {
            InitializeComponent();

            _controller = new Product();
            _prodId = -1;
            _imageChanged = false;

            productLoader = new BackgroundWorker();
            productLoader.DoWork += new DoWorkEventHandler(productLoader_DoWork);
            productLoader.RunWorkerCompleted += new RunWorkerCompletedEventHandler(productLoader_RunWorkerCompleted);

            deleteButtonColumn = new DataGridViewImageColumn();
            deleteButtonColumn.Image = Resources.delete;
            deleteButtonColumn.ImageLayout = DataGridViewImageCellLayout.Zoom;
        }

        public FProduct(int prodId)
        {
            InitializeComponent();

            _controller = new Product();
            _prodId = prodId;
            _imageChanged = false;

            productLoader = new BackgroundWorker();
            productLoader.DoWork += new DoWorkEventHandler(productLoader_DoWork);
            productLoader.RunWorkerCompleted += new RunWorkerCompletedEventHandler(productLoader_RunWorkerCompleted);

            deleteButtonColumn = new DataGridViewImageColumn();
            deleteButtonColumn.Image = Resources.delete;
            deleteButtonColumn.ImageLayout = DataGridViewImageCellLayout.Zoom;

            LoadProduct();
        }

        private void LoadProduct()
        {
            product prod = ABMHelper.GetProduct(_prodId);
            txtName.Text = prod.Name;
            if(prod.Description != null) rtxtDesc.Text = prod.Description;
            txtCostPrice.Text = prod.CostPrice.ToString();
            txtPrice.Text = prod.Price.ToString();
            if (prod.Image != null)
            {
                pboxImag.Image = Image.FromStream(new MemoryStream(prod.Image));
            }
        }

        private void ClearForm()
        {
            pboxImag.Image = null;
            ofImag.FileName = "";
            _imageChanged = false;
            _prodId = -1;
            txtName.Clear();
            rtxtDesc.Clear();
            txtPrice.Clear();
            txtCostPrice.Clear();
            dgvCat.Rows.Clear();
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void pboxImag_Click(object sender, EventArgs e)
        {
            ofImag.ShowDialog(this);
        }

        private void ofImag_FileOk(object sender, CancelEventArgs e)
        {
            pboxImag.ImageLocation = ofImag.FileName;
            _imageChanged = true;
        }

        private void FProduct_Shown(object sender, EventArgs e)
        {
            LoadInfo();
        }

        private void LoadInfo()
        {
            productLoader.RunWorkerAsync();
        }

        void productLoader_DoWork(object sender, DoWorkEventArgs e)
        {
            if (_prodId != -1)
            {
                dgvCategories = _controller.GetProdCategories(_prodId);
            }
            comboCategories = _controller.GetAllCategories();
        }

        void productLoader_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            dgvCat.Columns.Clear();
            //dgvCat.DataSource = dgvCategories;
            dgvCat.Columns.Add("Id", "Id");
            dgvCat.Columns.Add("Name", "Name");
            foreach (dynamic obj in dgvCategories)
            {
                dgvCat.Rows.Add(obj.Id, obj.Name);
            }
            dgvCat.Columns.Add(deleteButtonColumn);
            cmbCat.DataSource = comboCategories;
            cmbCat.DisplayMember = "Name";
            cmbCat.ValueMember = "Id";
        }

        private void dgvCat_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.ColumnIndex == deleteButtonColumn.Index)
            {
                dgvCat.Rows.RemoveAt(e.RowIndex);
            }
        }

        private void dgvCat_CellMouseEnter(object sender, DataGridViewCellEventArgs e)
        {
            if (e.ColumnIndex == deleteButtonColumn.Index)
            {
                this.Cursor = Cursors.Hand;
            }
        }

        private void dgvCat_CellMouseLeave(object sender, DataGridViewCellEventArgs e)
        {
            if (e.ColumnIndex == deleteButtonColumn.Index)
            {
                this.Cursor = Cursors.Default;
            }
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            bool found = false;
            foreach (DataGridViewRow r in dgvCat.Rows)
            {
                if ((int)r.Cells["Id"].Value == (int)cmbCat.SelectedValue)
                {
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                dgvCat.Rows.Add((int)cmbCat.SelectedValue, cmbCat.Text);
            }
            else
            {
                MessageBox.Show("La categoria ya esta en la lista.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btnOK_Click(object sender, EventArgs e)
        {
            try
            {
                _controller.DeleteProductCategoryRelations(_prodId);
                if (_imageChanged == true)
                {
                    _prodId = _controller.SaveProduct(_prodId, txtName.Text, rtxtDesc.Text, txtPrice.Text, txtCostPrice.Text, ofImag.OpenFile());
                }
                else
                {
                    _prodId = _controller.SaveProduct(_prodId, txtName.Text, rtxtDesc.Text, txtPrice.Text, txtCostPrice.Text, null);
                }
                if (_prodId != -1)
                {
                    if (dgvCat.Rows.Count > 0)
                    {
                        List<int> cats = new List<int>();
                        for (int i = 0; i < dgvCat.Rows.Count; i++)
                        {
                            cats.Add((int)dgvCat.Rows[i].Cells[0].Value);
                        }
                        _controller.SaveProductCategoyRelations(_prodId, cats);
                    }
                    MessageBox.Show("Exito al guardar", "Producto", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    if (MessageBox.Show("Desea guardar otro producto?", "Producto", MessageBoxButtons.YesNo,
                        MessageBoxIcon.Question) == DialogResult.Yes)
                    {
                        ClearForm();
                    }
                    else
                    {
                        this.Close();
                    }
                }
                else
                {
                    MessageBox.Show("Error al guardar. No se pudo guardar el producto", "Producto", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    this.Close();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al guardar. " + ex.Message, "Producto", MessageBoxButtons.OK, MessageBoxIcon.Error);
                this.Close();
            }
        }
    }
}

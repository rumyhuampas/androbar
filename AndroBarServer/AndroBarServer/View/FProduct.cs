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

namespace AndroBarServer.View
{
    public partial class FProduct : Form
    {
        private Product _controller;
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
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
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
            KeyValuePair<int, string> value;
            for (int i = 0; i < dgvCategories.Count(); i++)
            {
                value = dgvCategories.ElementAt<in;
                dgvCat.Rows.Add(value.Key, value.Value);
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
                _controller.DeleteProductCategory(_prodId, (int)dgvCat.Rows[e.RowIndex].Cells[0].Value);
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
            if (found)
            {
                
            }
            else
            {
                MessageBox.Show("La categoria ya esta en la lista.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}

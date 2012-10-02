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

namespace AndroBarServer.View
{
    public partial class FProducts : Form
    {
        private Products _controller;
        BackgroundWorker productLoader;
        IEnumerable<dynamic> dgvProducts = null;
        DataGridViewImageColumn deleteButtonColumn;

        public FProducts()
        {
            InitializeComponent();

            _controller = new Products();

            productLoader = new BackgroundWorker();
            productLoader.DoWork += new DoWorkEventHandler(productLoader_DoWork);
            productLoader.RunWorkerCompleted += new RunWorkerCompletedEventHandler(productLoader_RunWorkerCompleted);

            deleteButtonColumn = new DataGridViewImageColumn();
            deleteButtonColumn.Image = Resources.delete;
            deleteButtonColumn.ImageLayout = DataGridViewImageCellLayout.Zoom;
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            FProduct fProd = new FProduct();
            fProd.ShowDialog(this);
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void dgvProd_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if (dgvProd.SelectedRows.Count > 0)
            {
                FProduct fprod = new FProduct((int)dgvProd.SelectedRows[0].Cells["id"].Value);
                fprod.ShowDialog(this);
                LoadProducts();
            }
        }

        private void txtSearch_TextChanged(object sender, EventArgs e)
        {
            dgvProd.DataSource = _controller.GetData(txtSearch.Text.Trim());
            tsslblInfo.Text = "Productos: " + dgvProd.Rows.Count.ToString();
        }

        private void FProducts_Shown(object sender, EventArgs e)
        {
            LoadProducts();
        }

        private void LoadProducts()
        {
            tspbLoading.Visible = true;
            tsslblInfo.Text = "Cargando...";
            productLoader.RunWorkerAsync();
        }

        void productLoader_DoWork(object sender, DoWorkEventArgs e)
        {
            dgvProducts = _controller.GetAllProducts();
        }

        void productLoader_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            dgvProd.Columns.Clear();
            dgvProd.DataSource = dgvProducts;
            dgvProd.Columns.Add(deleteButtonColumn);
            tsslblInfo.Text = "Productos: " + dgvProd.Rows.Count.ToString();
            tspbLoading.Visible = false;
        }

        private void dgvProd_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.ColumnIndex == deleteButtonColumn.Index)
            {
                if (MessageBox.Show("Eliminar el producto: '" + dgvProd.Rows[e.RowIndex].Cells[1].Value.ToString() + "'?",
                    "Eliminar?", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == System.Windows.Forms.DialogResult.Yes)
                {
                    _controller.DeleteProduct((int)dgvProd.Rows[e.RowIndex].Cells[0].Value);
                    LoadProducts();
                }
            }
        }

        private void dgvProd_CellMouseEnter(object sender, DataGridViewCellEventArgs e)
        {
            if (e.ColumnIndex == deleteButtonColumn.Index)
            {
                this.Cursor = Cursors.Hand;
            }
        }

        private void dgvProd_CellMouseLeave(object sender, DataGridViewCellEventArgs e)
        {
            if (e.ColumnIndex == deleteButtonColumn.Index)
            {
                this.Cursor = Cursors.Default;
            }
        }
    }
}

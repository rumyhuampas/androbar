using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using AndroBarServer.Controller;
using System.Threading;
using AndroBarServer.Properties;

namespace AndroBarServer.View
{
    public partial class FCategories : Form
    {
        private Categories _controller;
        BackgroundWorker categoryLoader;
        IEnumerable<dynamic> dgvCategories = null;

        public FCategories()
        {
            InitializeComponent();

            _controller = new Categories();

            categoryLoader = new BackgroundWorker();
            categoryLoader.DoWork += new DoWorkEventHandler(categoryLoader_DoWork);
            categoryLoader.RunWorkerCompleted += new RunWorkerCompletedEventHandler(categoryLoader_RunWorkerCompleted);
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            FCategory fCat = new FCategory();
            fCat.ShowDialog(this);
            LoadCategories();
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void dgvCat_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if (dgvCat.SelectedRows.Count > 0)
            {
                FCategory fcat = new FCategory((int)dgvCat.SelectedRows[0].Cells["id"].Value);
                fcat.ShowDialog(this);
                LoadCategories();
            }
        }

        private void txtSearch_TextChanged(object sender, EventArgs e)
        {
            dgvCat.DataSource = _controller.GetData(txtSearch.Text.Trim());
            tsslblInfo.Text = "Categorias: " + dgvCat.Rows.Count.ToString();
        }

        private void FCategories_Shown(object sender, EventArgs e)
        {
            LoadCategories();
        }

        private void LoadCategories()
        {
            tspbLoading.Visible = true;
            tsslblInfo.Text = "Cargando...";
            categoryLoader.RunWorkerAsync();
        }

        void categoryLoader_DoWork(object sender, DoWorkEventArgs e)
        {
            dgvCategories = _controller.GetAllCategories();
        }

        void categoryLoader_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            dgvCat.Columns.Clear();
            dgvCat.DataSource = dgvCategories;
            DataGridViewImageColumn col = new DataGridViewImageColumn();
            col.Image = Resources.delete;
            col.ImageLayout = DataGridViewImageCellLayout.Zoom;
            dgvCat.Columns.Add(col);
            tsslblInfo.Text = "Categorias: " + dgvCat.Rows.Count.ToString();
            tspbLoading.Visible = false;
        }
    }
}

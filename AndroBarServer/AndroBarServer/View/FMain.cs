using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using AndroBarServer.View;
using AndroBarServer.Controller;
using AndroBarServer.Properties;
using AndroBarServer.Model;

namespace AndroBarServer
{
    public partial class FMain : Form
    {
        private Main _controller;

        public FMain()
        {
            InitializeComponent();

            _controller = new Main();
        }

        private void miCat_Click(object sender, EventArgs e)
        {
            FCategories fCat = new FCategories();
            fCat.ShowDialog(this);
        }

        private void miProd_Click(object sender, EventArgs e)
        {
            FProducts fProd = new FProducts();
            fProd.ShowDialog(this);
        }

        private void miExit_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void FMain_Load(object sender, EventArgs e)
        {
            dgvTables.Columns.Clear();
            dgvTables.DataSource = _controller.GetTables();
            dgvTables.Columns[0].Visible = false;
            dgvTables.Columns[2].Visible = false;

            DataGridViewImageColumn imageColumn = new DataGridViewImageColumn();
            imageColumn.Image = Resources.cart;
            imageColumn.ImageLayout = DataGridViewImageCellLayout.Zoom;

            dgvTables.Columns.Add(imageColumn);
        }

        private void dgvTables_SelectionChanged(object sender, EventArgs e)
        {
            if (dgvTables.SelectedRows.Count > 0)
            {
                lblOrderTitle.Text = "Mesa " + dgvTables.SelectedRows[0].Cells[1].Value.ToString();
                pnlOrder.Controls.Clear();

                order ord = _controller.GetTableLastOrder((int)dgvTables.SelectedRows[0].Cells[0].Value);
                DataGridView dgv = new DataGridView();
                /*DataGridViewTextBoxColumn colName = new DataGridViewTextBoxColumn();
                DataGridViewTextBoxColumn colAmount = new DataGridViewTextBoxColumn();
                dgv.Columns.Add(colName);
                dgv.Columns.Add(colAmount);*/
                dgv.Dock = DockStyle.Top;
                dgv.Height = 250;
                dgv.BackgroundColor = Color.White;
                dgv.MultiSelect = false;
                dgv.RowHeadersVisible = false;
                dgv.AllowUserToAddRows = false;
                dgv.AllowUserToDeleteRows = false;
                dgv.AllowUserToResizeRows = false;
                dgv.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
                dgv.ReadOnly = true;
                dgv.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
                dgv.DataSource = _controller.GetOrderProds(ord);

                pnlOrder.Controls.Add(dgv);
            }
        }
    }
}

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
            //dgvTables.Columns[0].Visible = false;
            //dgvTables.Columns[2].Visible = false;
            dgvTables.Columns[1].HeaderText = "Mesas";

            DataGridViewImageColumn imageColumn = new DataGridViewImageColumn();
            //imageColumn.Image = Resources.cart;
            imageColumn.ImageLayout = DataGridViewImageCellLayout.Zoom;
            imageColumn.DefaultCellStyle.NullValue = null;

            dgvTables.Columns.Add(imageColumn);

            tmrOrders.Start();
        }

        private void dgvTables_SelectionChanged(object sender, EventArgs e)
        {
            if (dgvTables.SelectedRows.Count > 0)
            {
                int tableId = (int) dgvTables.SelectedRows[0].Cells[1].Value;
                lblOrderTitle.Text = "Mesa " + tableId.ToString();
                pnlOrder.Controls.Clear();

                List<order> ords = _controller.GetTableOrders((int)dgvTables.SelectedRows[0].Cells[0].Value).ToList();
                foreach (order ord in ords)
                {
                    Panel pnlTitle = new Panel();
                    pnlTitle.Dock = DockStyle.Top;

                    ComboBox cmbStatus = new ComboBox();
                    cmbStatus.Items.AddRange(ABMHelper.OrderStatusComboList.GetList());
                    cmbStatus.Font = new System.Drawing.Font("Arial", 14, FontStyle.Bold);
                    cmbStatus.DropDownStyle = ComboBoxStyle.DropDownList;
                    cmbStatus.Dock = DockStyle.Left;
                    cmbStatus.Tag = ord.Id;
                    cmbStatus.SelectedIndex = ABMHelper.OrderStatusComboList.GetItemIndex(ord.Status);
                    cmbStatus.SelectedIndexChanged += new EventHandler(cmbStatus_SelectedIndexChanged);

                    Label lblTitle = new Label();
                    lblTitle.Text = "Pedido: " + ord.DateTime.ToString() + " - Estado: ";
                    lblTitle.Font = new Font("Arial", 14, FontStyle.Bold);
                    lblTitle.AutoSize = true;
                    lblTitle.Dock = DockStyle.Left;
                    
                    pnlTitle.Controls.Add(cmbStatus);
                    pnlTitle.Controls.Add(lblTitle);

                    pnlTitle.Height = lblTitle.Height + 20;
                    pnlTitle.Padding = new System.Windows.Forms.Padding(10);

                    DataGridView dgv = new DataGridView();
                    dgv.Dock = DockStyle.Top;
                    dgv.Height = 150;
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
                    pnlOrder.Controls.Add(pnlTitle);
                }

                CheckTableImage(tableId, dgvTables.SelectedRows[0]);
            }
        }

        void cmbStatus_SelectedIndexChanged(object sender, EventArgs e)
        {
            ComboBox cmb = (ComboBox)sender;
            _controller.ChangeOrderStatus((int)cmb.Tag, ABMHelper.OrderStatusComboList.GetItemValue(cmb.SelectedIndex));
            CheckTableImage((int)dgvTables.SelectedRows[0].Cells[1].Value, dgvTables.SelectedRows[0]);
        }

        private void CheckTableImage(int tableId, DataGridViewRow row)
        {
            if (dgvTables.Columns.Count > 3)
            {
                if (_controller.AreAnyReceivedOrders(tableId) == false)
                {
                    row.Cells[3].Value = null;
                }
                else
                {
                    row.Cells[3].Value = Resources.cart;
                }
            }
        }

        private void tmrOrders_Tick(object sender, EventArgs e)
        {
            tmrOrders.Stop();
            for (int i = 0; i < dgvTables.Rows.Count; i++)
            {
                CheckTableImage((int) dgvTables.Rows[i].Cells[1].Value, dgvTables.Rows[i]);
            }
            tmrOrders.Start();
        }
    }
}

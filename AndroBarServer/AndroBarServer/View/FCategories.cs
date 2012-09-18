using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using AndroBarServer.Controller;
using AndroBarServer.Model;

namespace AndroBarServer.View
{
    public partial class FCategories : Form
    {
        private Categories _controller;

        public FCategories()
        {
            InitializeComponent();

            _controller = new Categories();
        }

        private void FCategories_Load(object sender, EventArgs e)
        {
            dgvCat.DataSource = _controller.GetAllCategories();
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            FCategory fCat = new FCategory();
            fCat.ShowDialog(this);
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void dgvCat_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if (dgvCat.SelectedRows.Count > 0)
            {
                category cat = ABMHelper.GetCategory((int)dgvCat.SelectedRows[0].Cells["id"].Value);
                if (cat != null)
                {
                    FCategory fcat = new FCategory(ABMHelper.ABMMODE.ABMMODE_EDIT, cat);
                    fcat.ShowDialog(this);
                }
            }
        }

        private void txtSearch_TextChanged(object sender, EventArgs e)
        {
            dgvCat.DataSource = _controller.GetData(txtSearch.Text.Trim());
        }
    }
}

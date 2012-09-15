using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using AndroBarServer.Controller;

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
    }
}

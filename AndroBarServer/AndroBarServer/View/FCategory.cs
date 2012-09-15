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
    public partial class FCategory : Form
    {
        private ABMHelper.ABMMODE _mode;
        private Category _controller;

        public FCategory()
        {
            InitializeComponent();
            _mode = ABMHelper.ABMMODE.ABMMODE_NEW;
            _controller = new Category();
        }

        public FCategory(ABMHelper.ABMMODE mode)
        {
            InitializeComponent();
            _mode = mode;
            _controller = new Category();
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
        }

        private void btnOK_Click(object sender, EventArgs e)
        {
            _controller.SaveCategory(ofImag.OpenFile(), txtName.Text);
        }
    }
}

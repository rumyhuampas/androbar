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

        public FCategory()
        {
            InitializeComponent();
            _mode = ABMHelper.ABMMODE.ABMMODE_NEW;
        }

        public FCategory(ABMHelper.ABMMODE mode)
        {
            InitializeComponent();
            _mode = mode;
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}

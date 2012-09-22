using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using AndroBarServer.View;

namespace AndroBarServer
{
    public partial class FMain : Form
    {
        public FMain()
        {
            InitializeComponent();
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
    }
}

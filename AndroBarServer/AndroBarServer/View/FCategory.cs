using System;
using System.ComponentModel;
using System.Drawing;
using System.IO;
using System.Windows.Forms;
using AndroBarServer.Controller;
using AndroBarServer.Model;

namespace AndroBarServer.View
{
    public partial class FCategory : Form
    {
        private int _catId;
        private Category _controller;
        private bool _imageChanged;

        public FCategory()
        {
            InitializeComponent();
            _controller = new Category();
            _catId = -1;
            _imageChanged = false;
        }

        public FCategory(int catId)
        {
            InitializeComponent();
            _controller = new Category();
            _catId = catId;
            _imageChanged = false;
            LoadCategory();
        }

        private void ClearForm()
        {
            pboxImag.Image = null;
            ofImag.FileName = "";
            _catId = -1;
            _imageChanged = false;
            txtName.Clear();
        }

        private void LoadCategory()
        {
            category cat = ABMHelper.GetCategory(_catId);
            if (cat.Image != null)
            {
                pboxImag.Image = Image.FromStream(new MemoryStream(cat.Image));
            }
            txtName.Text = cat.Name;
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
            _imageChanged = true;
        }

        private void btnOK_Click(object sender, EventArgs e)
        {
            try
            {
                bool success = false;
                if (_imageChanged == true)
                {
                    success = _controller.SaveCategory(_catId, ofImag.OpenFile(), txtName.Text);
                }
                else
                {
                    success = _controller.SaveCategory(_catId, null, txtName.Text);
                }
                if (success == true)
                {
                    MessageBox.Show("Exito al guardar", "Categoria", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    if (MessageBox.Show("Desea guardar otra categoria?", "Categoria", MessageBoxButtons.YesNo,
                        MessageBoxIcon.Question) == DialogResult.Yes)
                    {
                        ClearForm();
                    }
                    else
                    {
                        this.Close();
                    }
                }
                else
                {
                    MessageBox.Show("Error al guardar. No se pudo guardar la categoria", "Categoria", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    this.Close();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al guardar. " + ex.Message, "Categoria", MessageBoxButtons.OK, MessageBoxIcon.Error);
                this.Close();
            }
        }
    }
}

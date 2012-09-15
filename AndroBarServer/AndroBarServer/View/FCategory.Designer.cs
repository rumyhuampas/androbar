namespace AndroBarServer.View
{
    partial class FCategory
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.gbData = new System.Windows.Forms.GroupBox();
            this.lblImag = new System.Windows.Forms.Label();
            this.pboxImag = new System.Windows.Forms.PictureBox();
            this.lblName = new System.Windows.Forms.Label();
            this.txtName = new System.Windows.Forms.TextBox();
            this.btnCancel = new System.Windows.Forms.Button();
            this.btnOK = new System.Windows.Forms.Button();
            this.ofImag = new System.Windows.Forms.OpenFileDialog();
            this.gbData.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pboxImag)).BeginInit();
            this.SuspendLayout();
            // 
            // gbData
            // 
            this.gbData.Controls.Add(this.txtName);
            this.gbData.Controls.Add(this.lblName);
            this.gbData.Controls.Add(this.pboxImag);
            this.gbData.Controls.Add(this.lblImag);
            this.gbData.Location = new System.Drawing.Point(12, 12);
            this.gbData.Name = "gbData";
            this.gbData.Size = new System.Drawing.Size(280, 217);
            this.gbData.TabIndex = 0;
            this.gbData.TabStop = false;
            this.gbData.Text = "Datos";
            // 
            // lblImag
            // 
            this.lblImag.AutoSize = true;
            this.lblImag.Location = new System.Drawing.Point(6, 30);
            this.lblImag.Name = "lblImag";
            this.lblImag.Size = new System.Drawing.Size(45, 13);
            this.lblImag.TabIndex = 0;
            this.lblImag.Text = "Imagen:";
            // 
            // pboxImag
            // 
            this.pboxImag.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pboxImag.Location = new System.Drawing.Point(59, 19);
            this.pboxImag.Name = "pboxImag";
            this.pboxImag.Size = new System.Drawing.Size(150, 150);
            this.pboxImag.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pboxImag.TabIndex = 1;
            this.pboxImag.TabStop = false;
            this.pboxImag.Click += new System.EventHandler(this.pboxImag_Click);
            // 
            // lblName
            // 
            this.lblName.AutoSize = true;
            this.lblName.Location = new System.Drawing.Point(6, 185);
            this.lblName.Name = "lblName";
            this.lblName.Size = new System.Drawing.Size(47, 13);
            this.lblName.TabIndex = 2;
            this.lblName.Text = "Nombre:";
            // 
            // txtName
            // 
            this.txtName.Location = new System.Drawing.Point(59, 182);
            this.txtName.Name = "txtName";
            this.txtName.Size = new System.Drawing.Size(215, 20);
            this.txtName.TabIndex = 3;
            // 
            // btnCancel
            // 
            this.btnCancel.Location = new System.Drawing.Point(217, 247);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(75, 23);
            this.btnCancel.TabIndex = 1;
            this.btnCancel.Text = "Cancelar";
            this.btnCancel.UseVisualStyleBackColor = true;
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // btnOK
            // 
            this.btnOK.Location = new System.Drawing.Point(136, 247);
            this.btnOK.Name = "btnOK";
            this.btnOK.Size = new System.Drawing.Size(75, 23);
            this.btnOK.TabIndex = 2;
            this.btnOK.Text = "Aceptar";
            this.btnOK.UseVisualStyleBackColor = true;
            this.btnOK.Click += new System.EventHandler(this.btnOK_Click);
            // 
            // ofImag
            // 
            this.ofImag.FileName = "Imagen";
            this.ofImag.Filter = "Imagenes|*.bmp;*.jpg;*.png";
            this.ofImag.Title = "Imagen";
            this.ofImag.FileOk += new System.ComponentModel.CancelEventHandler(this.ofImag_FileOk);
            // 
            // FCategory
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(304, 282);
            this.Controls.Add(this.btnOK);
            this.Controls.Add(this.btnCancel);
            this.Controls.Add(this.gbData);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "FCategory";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Categoria";
            this.gbData.ResumeLayout(false);
            this.gbData.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pboxImag)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox gbData;
        private System.Windows.Forms.TextBox txtName;
        private System.Windows.Forms.Label lblName;
        private System.Windows.Forms.PictureBox pboxImag;
        private System.Windows.Forms.Label lblImag;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.Button btnOK;
        private System.Windows.Forms.OpenFileDialog ofImag;
    }
}
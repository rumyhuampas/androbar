namespace AndroBarServer.View
{
    partial class FProducts
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
            this.ssProd = new System.Windows.Forms.StatusStrip();
            this.SuspendLayout();
            // 
            // ssProd
            // 
            this.ssProd.Location = new System.Drawing.Point(0, 412);
            this.ssProd.Name = "ssProd";
            this.ssProd.Size = new System.Drawing.Size(742, 22);
            this.ssProd.TabIndex = 0;
            this.ssProd.Text = "statusStrip1";
            // 
            // FProducts
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(742, 434);
            this.Controls.Add(this.ssProd);
            this.Name = "FProducts";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Productos";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.StatusStrip ssProd;
    }
}
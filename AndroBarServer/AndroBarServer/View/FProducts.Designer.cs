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
            this.dgvProd = new System.Windows.Forms.DataGridView();
            this.pnlSearch = new System.Windows.Forms.Panel();
            this.txtSearch = new System.Windows.Forms.TextBox();
            this.lblSearch = new System.Windows.Forms.Label();
            this.pnlButtons = new System.Windows.Forms.Panel();
            this.pnlButtonsRight = new System.Windows.Forms.Panel();
            this.btnNew = new System.Windows.Forms.Button();
            this.btnCancel = new System.Windows.Forms.Button();
            this.ssCat = new System.Windows.Forms.StatusStrip();
            this.tsslblInfo = new System.Windows.Forms.ToolStripStatusLabel();
            this.tspbLoading = new System.Windows.Forms.ToolStripProgressBar();
            ((System.ComponentModel.ISupportInitialize)(this.dgvProd)).BeginInit();
            this.pnlSearch.SuspendLayout();
            this.pnlButtons.SuspendLayout();
            this.pnlButtonsRight.SuspendLayout();
            this.ssCat.SuspendLayout();
            this.SuspendLayout();
            // 
            // dgvProd
            // 
            this.dgvProd.AllowUserToAddRows = false;
            this.dgvProd.AllowUserToDeleteRows = false;
            this.dgvProd.AllowUserToResizeRows = false;
            this.dgvProd.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvProd.BackgroundColor = System.Drawing.Color.White;
            this.dgvProd.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvProd.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvProd.Location = new System.Drawing.Point(10, 60);
            this.dgvProd.MultiSelect = false;
            this.dgvProd.Name = "dgvProd";
            this.dgvProd.ReadOnly = true;
            this.dgvProd.RowHeadersVisible = false;
            this.dgvProd.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvProd.Size = new System.Drawing.Size(722, 308);
            this.dgvProd.TabIndex = 8;
            this.dgvProd.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvProd_CellClick);
            this.dgvProd.CellDoubleClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvProd_CellDoubleClick);
            this.dgvProd.CellMouseEnter += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvProd_CellMouseEnter);
            this.dgvProd.CellMouseLeave += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvProd_CellMouseLeave);
            // 
            // pnlSearch
            // 
            this.pnlSearch.Controls.Add(this.txtSearch);
            this.pnlSearch.Controls.Add(this.lblSearch);
            this.pnlSearch.Dock = System.Windows.Forms.DockStyle.Top;
            this.pnlSearch.Location = new System.Drawing.Point(10, 10);
            this.pnlSearch.Name = "pnlSearch";
            this.pnlSearch.Size = new System.Drawing.Size(722, 50);
            this.pnlSearch.TabIndex = 7;
            // 
            // txtSearch
            // 
            this.txtSearch.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.txtSearch.Location = new System.Drawing.Point(49, 16);
            this.txtSearch.Name = "txtSearch";
            this.txtSearch.Size = new System.Drawing.Size(670, 20);
            this.txtSearch.TabIndex = 1;
            this.txtSearch.TextChanged += new System.EventHandler(this.txtSearch_TextChanged);
            // 
            // lblSearch
            // 
            this.lblSearch.AutoSize = true;
            this.lblSearch.Location = new System.Drawing.Point(3, 19);
            this.lblSearch.Name = "lblSearch";
            this.lblSearch.Size = new System.Drawing.Size(40, 13);
            this.lblSearch.TabIndex = 0;
            this.lblSearch.Text = "Buscar";
            // 
            // pnlButtons
            // 
            this.pnlButtons.Controls.Add(this.pnlButtonsRight);
            this.pnlButtons.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.pnlButtons.Location = new System.Drawing.Point(10, 368);
            this.pnlButtons.Name = "pnlButtons";
            this.pnlButtons.Size = new System.Drawing.Size(722, 34);
            this.pnlButtons.TabIndex = 6;
            // 
            // pnlButtonsRight
            // 
            this.pnlButtonsRight.Controls.Add(this.btnNew);
            this.pnlButtonsRight.Controls.Add(this.btnCancel);
            this.pnlButtonsRight.Dock = System.Windows.Forms.DockStyle.Right;
            this.pnlButtonsRight.Location = new System.Drawing.Point(522, 0);
            this.pnlButtonsRight.Name = "pnlButtonsRight";
            this.pnlButtonsRight.Size = new System.Drawing.Size(200, 34);
            this.pnlButtonsRight.TabIndex = 0;
            // 
            // btnNew
            // 
            this.btnNew.Location = new System.Drawing.Point(15, 6);
            this.btnNew.Name = "btnNew";
            this.btnNew.Size = new System.Drawing.Size(75, 23);
            this.btnNew.TabIndex = 3;
            this.btnNew.Text = "Nuevo";
            this.btnNew.UseVisualStyleBackColor = true;
            this.btnNew.Click += new System.EventHandler(this.btnNew_Click);
            // 
            // btnCancel
            // 
            this.btnCancel.Location = new System.Drawing.Point(110, 6);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(75, 23);
            this.btnCancel.TabIndex = 2;
            this.btnCancel.Text = "Cancelar";
            this.btnCancel.UseVisualStyleBackColor = true;
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // ssCat
            // 
            this.ssCat.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsslblInfo,
            this.tspbLoading});
            this.ssCat.Location = new System.Drawing.Point(10, 402);
            this.ssCat.Name = "ssCat";
            this.ssCat.Size = new System.Drawing.Size(722, 22);
            this.ssCat.TabIndex = 5;
            this.ssCat.Text = "statusStrip1";
            // 
            // tsslblInfo
            // 
            this.tsslblInfo.Name = "tsslblInfo";
            this.tsslblInfo.Size = new System.Drawing.Size(0, 17);
            // 
            // tspbLoading
            // 
            this.tspbLoading.Name = "tspbLoading";
            this.tspbLoading.Size = new System.Drawing.Size(100, 16);
            this.tspbLoading.Style = System.Windows.Forms.ProgressBarStyle.Marquee;
            this.tspbLoading.Visible = false;
            // 
            // FProducts
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(742, 434);
            this.Controls.Add(this.dgvProd);
            this.Controls.Add(this.pnlSearch);
            this.Controls.Add(this.pnlButtons);
            this.Controls.Add(this.ssCat);
            this.MinimumSize = new System.Drawing.Size(758, 472);
            this.Name = "FProducts";
            this.Padding = new System.Windows.Forms.Padding(10);
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Productos";
            this.Shown += new System.EventHandler(this.FProducts_Shown);
            ((System.ComponentModel.ISupportInitialize)(this.dgvProd)).EndInit();
            this.pnlSearch.ResumeLayout(false);
            this.pnlSearch.PerformLayout();
            this.pnlButtons.ResumeLayout(false);
            this.pnlButtonsRight.ResumeLayout(false);
            this.ssCat.ResumeLayout(false);
            this.ssCat.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dgvProd;
        private System.Windows.Forms.Panel pnlSearch;
        private System.Windows.Forms.TextBox txtSearch;
        private System.Windows.Forms.Label lblSearch;
        private System.Windows.Forms.Panel pnlButtons;
        private System.Windows.Forms.Panel pnlButtonsRight;
        private System.Windows.Forms.Button btnNew;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.StatusStrip ssCat;
        private System.Windows.Forms.ToolStripStatusLabel tsslblInfo;
        private System.Windows.Forms.ToolStripProgressBar tspbLoading;

    }
}
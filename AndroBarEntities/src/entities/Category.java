package entities;

import image.ImageManagement;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Category {

    private static final String TABLENAME = "Categories";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_NAME = "Name";
    private static final String FIELD_IMAGE = "Image";
    
    private Connection Conn;
    private int Id;
    public String Name;
    private Image Img;
    private File ImageFile;
    public boolean ImageChanged;
    public InputStream ImageStream;

    public int getId() {
        return this.Id;
    }

    public void SetImage(String imagePath) {
        if (imagePath != null) {
            this.ImageFile = new File(imagePath);
            if (this.ImageFile.exists()) {
                try {
                    this.ImageStream = new FileInputStream(this.ImageFile);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this.ImageFile = null;
            }
        }
    }

    public Image GetImage() {
        if (this.ImageStream != null) {
            try {
                this.Img = ImageIO.read(this.ImageStream);
                this.ImageStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.Img;
    }

    public ImageIcon GetImageIcon() {
        if (this.Img == null) GetImage();
        if (this.Img != null) {
            return new ImageIcon(this.Img);
        } else {
            return null;
        }
    }

    public ImageIcon GetImageIconResized(int width, int height) {
        if (this.Img == null) GetImage();
        if (this.Img != null) {
            Image image2 = this.Img;
            ImageManagement gImg = new ImageManagement(image2);
            image2 = gImg.getImage();
            BufferedImage tnsImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = tnsImg.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(image2, 0, 0, width, height, null);
            //  Icon icon = new ImageIcon(tnsImg);


            return new ImageIcon(tnsImg);
        } else {
            return null;
        }

    }
    

    public void ClearImage() {
        this.Img = null;
        this.ImageFile = null;
        this.ImageStream = null;
    }

    public Category(Connection conn) throws SQLException {
        this.Conn = conn;
        this.Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.Name = "";
        this.ClearImage();
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectProduct(qry);
        } finally {
            qry.close();
        }
    }

    public boolean Load(String name) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_NAME + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, name);
            return SelectProduct(qry);
        } finally {
            qry.close();
        }
    }

    private boolean SelectProduct(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.Name = results.getString(this.FIELD_NAME);
                this.ImageStream = results.getBinaryStream(this.FIELD_IMAGE);
                return true;
            } else {
                return false;
            }
        } finally {
            results.close();
        }
    }

    public boolean Save() throws SQLException {
        if (!Exists()) {
            if (this.Id == -1) {
                return InsertProduct();
            } else {
                return UpdateProduct();
            }
        } else {
            return false;
        }
    }

    private boolean InsertProduct() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_NAME + ", "
                + this.FIELD_IMAGE
                + ") VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setString(1, this.Name);
            if (this.ImageFile != null) {
                qry.setBinaryStream(2, this.ImageStream, (int) (this.ImageFile.length()));
            } else {
                qry.setBinaryStream(2, null);
            }
            if (qry.executeUpdate() > 0) {
                ResultSet result = qry.getGeneratedKeys();
                result.next();
                this.Id = result.getInt("GENERATED_KEY");
                return true;
            } else {
                return false;
            }
        } finally {
            qry.close();
        }
    }

    private boolean UpdateProduct() throws SQLException {
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_NAME + " = ?,"
                + this.FIELD_IMAGE + " = ?"
                + " WHERE " + this.FIELD_ID + " = ?";

        String sql2 = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_NAME + " = ?"
                + " WHERE " + this.FIELD_ID + " = ?";
        if (ImageChanged == true) {
            PreparedStatement qry = this.Conn.prepareStatement(sql);
            try {
                qry.setString(1, this.Name);
                if (this.ImageFile != null) {
                    // DE DONDE VIENE IMAGEFILE (EL PATH)SI NO ESTA EN LA BASE DE DATOS Y EL MODIFICAR OBTIENE LOS DATOS D AHI
                    qry.setBinaryStream(2, this.ImageStream, (int) (this.ImageFile.length()));
                } else {
                    qry.setBinaryStream(2, null);
                }
                qry.setInt(3, this.Id);
                return qry.executeUpdate() > 0;
            } finally {
                qry.close();
            }
        } else {
            PreparedStatement qry = this.Conn.prepareStatement(sql2);
            try {
                qry.setString(1, this.Name);
                qry.setInt(2, this.Id);
                return qry.executeUpdate() > 0;
            } finally {
                qry.close();
            }
        }

    }

    public boolean Exists() throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_NAME + " = ? AND "
                + this.FIELD_ID + " <> ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Name);
            qry.setInt(2, this.Id);
            ResultSet results = qry.executeQuery();
            try {
                if (results.next()) {
                    return true;
                } else {
                    return false;
                }
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }

    public boolean Delete() throws SQLException {
        String sql = "DELETE FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, this.Id);
            if (qry.executeUpdate() > 0) {
                this.Id = -1;
                return true;
            } else {
                return false;
            }
        } finally {
            qry.close();
        }
    }
    
    public static Object[] GetAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM " + TABLENAME + " ORDER BY " + FIELD_NAME;
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList rows = new ArrayList();
            ArrayList row = new ArrayList();
            ResultSet results = qry.executeQuery();
            try {
                while (results.next()) {
                    row.add(results.getInt(FIELD_ID));
                    row.add(results.getString(FIELD_NAME));
                    rows.add(row.toArray());
                    row.clear();
                }
                return rows.toArray();
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }
}
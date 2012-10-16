package entities;

import java.sql.*;
import java.util.ArrayList;

public class Product {

    public static final String TABLENAME = "Products";
    public static final String FIELD_ID = "Id";
    public static final String FIELD_NAME = "Name";
    public static final String FIELD_DESC = "Description";
    public static final String FIELD_PRICE = "Price";
    public static final String FIELD_COSTPRICE = "CostPrice";
    private Connection Conn;
    private int Id;
    public String Name;
    public String Description;
    public Float Price;
    public Float CostPrice;

    public int getId() {
        return this.Id;
    }

    public Product(Connection conn) throws SQLException {
        this.Conn = conn;
        this.Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.Name = "";
        this.Description = "";
        this.Price = Float.parseFloat("0");
        this.CostPrice = Float.parseFloat("0");
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
                this.Description = results.getString(this.FIELD_DESC);
                this.Price = results.getFloat(this.FIELD_PRICE);
                this.CostPrice = results.getFloat(this.FIELD_COSTPRICE);
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
                + this.FIELD_DESC + ", "
                + this.FIELD_PRICE + ","
                + this.FIELD_COSTPRICE
                + ") VALUES (?,?,?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setString(1, this.Name);
            qry.setString(2, this.Description);
            qry.setFloat(3, this.Price);
            qry.setFloat(4, this.CostPrice);
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
                + this.FIELD_DESC + " = ?,"
                + this.FIELD_PRICE + " = ?,"
                + this.FIELD_COSTPRICE + " = ?"
                + " WHERE " + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Name);
            qry.setString(2, this.Description);
            qry.setFloat(3, this.Price);
            qry.setFloat(4, this.CostPrice);
            qry.setInt(5, this.Id);
            return qry.executeUpdate() > 0;
        } finally {
            qry.close();
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
                    row.add(results.getString(FIELD_DESC));
                    row.add(results.getFloat(FIELD_PRICE));
                    row.add(results.getFloat(FIELD_COSTPRICE));
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
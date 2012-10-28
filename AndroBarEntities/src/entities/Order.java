package entities;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order {

    private static final String TABLENAME = "Orders";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_TABLEID = "TableId";
    private static final String FIELD_DATETIME = "DateTime";
    private static final String FIELD_STATUS = "Status";
    private static final String CATPROD_TABLENAME = "Category_Products";
    private static final String FIELD_CATID = "CategoryId";
    private static final String FIELD_PRODID = "ProductId";
    private Connection Conn;
    private int Id;
    public int TableId;
    private Date DateTime;
    private String Status;

    public int getId() {
        return this.Id;
    }

    public Order(Connection conn) throws SQLException {
        this.Conn = conn;
        this.Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.TableId = -1;
        java.util.Date today = new java.util.Date();
        this.DateTime = new Date(today.getTime());
        this.Status = "RECEIVED";
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectOrder(qry);
        } finally {
            qry.close();
        }
    }

    private boolean SelectOrder(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.TableId = results.getInt(this.FIELD_TABLEID);
                this.DateTime = results.getDate(this.FIELD_DATETIME);
                this.Status = results.getString(FIELD_STATUS);
                return true;
            } else {
                return false;
            }
        } finally {
            results.close();
        }
    }

    public boolean Save() throws SQLException {
        if (this.Id == -1) {
            return InsertOrder();
        } else {
            return UpdateOrder();
        }
    }

    private boolean InsertOrder() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_TABLEID + ", "
                + this.FIELD_DATETIME + ", "
                + this.FIELD_STATUS
                + ") VALUES (?,?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setInt(1, this.TableId);
            qry.setDate(2, this.DateTime);
            qry.setString(3, this.Status);
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

    private boolean UpdateOrder() throws SQLException {
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_TABLEID + " = ?,"
                + this.FIELD_DATETIME + " = ?,"
                + this.FIELD_STATUS + " = ?"
                + " WHERE " + this.FIELD_ID + " = ?";

        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, this.TableId);
            qry.setDate(2, this.DateTime);
            qry.setString(3, this.Status);
            qry.setInt(4, this.Id);
            return qry.executeUpdate() > 0;
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

    /*
     * public static Object[] GetAll(Connection conn) throws SQLException {
     * String sql = "SELECT * FROM " + TABLENAME + " ORDER BY " + FIELD_NAME;
     * PreparedStatement qry = conn.prepareStatement(sql); try { ArrayList rows
     * = new ArrayList(); ArrayList row = new ArrayList(); ResultSet results =
     * qry.executeQuery(); try { while (results.next()) {
     * row.add(results.getInt(FIELD_ID));
     * row.add(results.getString(FIELD_NAME)); rows.add(row.toArray());
     * row.clear(); } return rows.toArray(); } finally { results.close(); } }
     * finally { qry.close(); } }
     *
     * public Object[] GetProducts() throws SQLException { String sql = "SELECT
     * * FROM " + Product.TABLENAME + " P, " + this.CATPROD_TABLENAME + " CP
     * WHERE " + "P." + Product.FIELD_ID + " = CP." + this.FIELD_PRODID + " AND
     * CP." + this.FIELD_CATID + " = ?"; PreparedStatement qry =
     * this.Conn.prepareStatement(sql); try { qry.setInt(1, this.Id); ArrayList
     * rows = new ArrayList(); ArrayList row = new ArrayList(); ResultSet
     * results = qry.executeQuery(); try { while (results.next()) {
     * row.add(results.getInt(Product.FIELD_ID));
     * row.add(results.getString(Product.FIELD_NAME));
     * row.add(results.getString(Product.FIELD_DESC));
     * row.add(results.getFloat(Product.FIELD_PRICE));
     * row.add(results.getFloat(Product.FIELD_COSTPRICE));
     * rows.add(row.toArray()); row.clear(); } return rows.toArray(); } finally
     * { results.close(); } } finally { qry.close(); }
    }
     */
}
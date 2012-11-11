package entities;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Order {

    private static final String TABLENAME = "Orders";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_TABLEID = "TableId";
    private static final String FIELD_DATETIME = "DateTime";
    private static final String FIELD_STATUS = "Status";
    private static final String CATPROD_TABLENAME = "Category_Products";
    private static final String FIELD_CATID = "CategoryId";
    private static final String FIELD_PRODID = "ProductId";
    private static final String OP_TABLENAME = "Order_Products";
    private static final String FIELD_ORDERID = "OrderId";
    private Connection Conn;
    private int Id;
    public int TableId;
    private Date DateTime;
    private String Status;
    public ArrayList Products;

    public int getId() {
        return this.Id;
    }
    
    public Date getDateTime(){
        return DateTime;
    }

    public Order(Connection conn) throws SQLException {
        this.Conn = conn;
        this.Products = new ArrayList();
        this.Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.TableId = -1;
        this.DateTime = null;
        this.Status = "RECEIVED";
        this.Products.clear();
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
                SelectProducts(this.Id);
                return true;
            } else {
                return false;
            }
        } finally {
            results.close();
        }
    }

    private boolean SelectProducts(int orderId) throws SQLException {
        String sql = "SELECT * FROM " + this.OP_TABLENAME + " WHERE "
                + this.FIELD_ORDERID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, orderId);
            ResultSet results = qry.executeQuery();
            try {
                while (results.next()) {
                    Product prod = new Product(this.Conn);
                    prod.Load(results.getInt(this.FIELD_PRODID));
                    this.Products.add(prod);
                }
                return true;
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }

    public boolean Save() throws SQLException {
        if (this.Id == -1) {
            if (InsertOrder()) {
                return InsertProducts();
            } else {
                return false;
            }
        } else {
            if (UpdateOrder()) {
                if (DeleteProducts()) {
                    return InsertProducts();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    private boolean InsertOrder() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_TABLEID + ", "
                + this.FIELD_DATETIME + ", "
                + this.FIELD_STATUS
                + ") VALUES (?,?,?)";
        String sql2 = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_TABLEID + ", "
                + this.FIELD_DATETIME + ", "
                + this.FIELD_STATUS
                + ") VALUES (?,NOW(),?)";
        PreparedStatement qry = null;
        if (this.DateTime != null) {
            qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } else {
            qry = this.Conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
        }
        try {
            qry.setInt(1, this.TableId);
            if (this.DateTime != null) {
                qry.setDate(2, this.DateTime);
                qry.setString(3, this.Status);
            } else {
                qry.setString(2, this.Status);
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

    public boolean DeleteProducts() throws SQLException {
        String sql = "DELETE FROM " + this.OP_TABLENAME + " WHERE "
                + this.FIELD_ORDERID + " = ?";
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

    private boolean InsertProducts() throws SQLException {
        boolean result = false;
        String sql = "INSERT INTO " + this.OP_TABLENAME + " ("
                + this.FIELD_ORDERID + ", "
                + this.FIELD_PRODID
                + ") VALUES (?,?)";
        this.Conn.setAutoCommit(false);
        try {
            PreparedStatement qry = null;
            qry = this.Conn.prepareStatement(sql);
            for (int i = 0; i < this.Products.size(); i++) {
                qry.setInt(1, this.Id);
                Product prod = (Product) this.Products.get(i);
                qry.setInt(2, prod.getId());
                qry.addBatch();
            }
            qry.executeBatch();
            this.Conn.commit();

            result = true;
        } catch (Exception ex) {
            this.Conn.rollback();
            result = false;
        } finally {
            this.Conn.setAutoCommit(true);
        }
        return result;
    }

    public static Object[] GetAll(Connection conn, Integer tableId) throws SQLException {
        String sql = "SELECT * FROM " + TABLENAME 
                + " WHERE " + FIELD_TABLEID + " = " + tableId.toString()
                + " ORDER BY " + FIELD_DATETIME;
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList rows = new ArrayList();
            ResultSet results = qry.executeQuery();
            try {
                while (results.next()) {
                    Order ord = new Order(conn);
                    ord.Load(results.getInt(FIELD_ID));
                    rows.add(ord);
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
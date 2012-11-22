package entities;

import java.sql.*;
import java.util.ArrayList;

public class Table {

    public static final String TABLENAME = "Tables";
    public static final String FIELD_ID = "Id";
    public static final String FIELD_NUMBER = "Number";
    
    private static final String ORDERSTABLENAME = "Orders";
    private static final String FIELD_TABLEID = "TableId";
    private static final String FIELD_STATUS = "Status";
    
    private static final String OS_RECEIVED = "RECEIVED";
    private static final String OS_PENDING = "PENDING";
    private static final String OS_DELIVERED = "DELIVERED";
    private static final String OS_CANCEL_REQUESTED = "CANCEL_REQUESTED";
    private static final String OS_CANCELED = "CANCELED";
    private static final String OS_CHARGE_REQUESTED = "CHARGE_REQUESTED";
    private static final String OS_CHARGED = "CHARGED";
    
    private Connection Conn;
    private int Id;
    public String Number;

    public int getId() {
        return this.Id;
    }

    public Table(Connection conn) throws SQLException {
        this.Conn = conn;
        this.Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.Number = "";
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectTable(qry);
        } finally {
            qry.close();
        }
    }

    public boolean Load(String number) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_NUMBER + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, number);
            return SelectTable(qry);
        } finally {
            qry.close();
        }
    }

    private boolean SelectTable(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.Number = results.getString(this.FIELD_NUMBER);
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
                return InsertTable();
            } else {
                return UpdateTable();
            }
        } else {
            return false;
        }
    }

    private boolean InsertTable() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_NUMBER
                + ") VALUES (?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setString(1, this.Number);
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

    private boolean UpdateTable() throws SQLException {
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_NUMBER + " = ?"
                + " WHERE " + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Number);
            qry.setInt(2, this.Id);
            return qry.executeUpdate() > 0;
        } finally {
            qry.close();
        }
    }

    public boolean Exists() throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_NUMBER + " = ? AND "
                + this.FIELD_ID + " <> ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Number);
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
        String sql = "SELECT * FROM " + TABLENAME + " ORDER BY " + FIELD_NUMBER;
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList rows = new ArrayList();
            ArrayList row = new ArrayList();
            ResultSet results = qry.executeQuery();
            try {
                while (results.next()) {
                    row.add(results.getInt(FIELD_ID));
                    row.add(results.getString(FIELD_NUMBER));
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
    
    private boolean RequestClose() throws SQLException {
        String sql = "UPDATE " + this.ORDERSTABLENAME + " SET "
                + this.FIELD_STATUS + " = ?"
                + " WHERE " + this.FIELD_TABLEID + " = ?"
                 + " AND " + FIELD_STATUS + " NOT IN ('" 
                + OS_CANCEL_REQUESTED + "','" + OS_CANCELED + "','" 
                + OS_CHARGE_REQUESTED + "','" + OS_CHARGED + "') ";

        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, this.Id);
            qry.setString(2, OS_CHARGE_REQUESTED);
            return qry.executeUpdate() > 0;
        } finally {
            qry.close();
        }
    }
}
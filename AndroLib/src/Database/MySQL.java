package Database;

import Interfaces.IConn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQL implements IConn {

    private String BD;
    private String LOGIN;
    private String PASS;
    private String URL;
    public Connection Conn;
    
    public MySQL(String url, String bd, String user, String pass){
        URL = "jdbc:mysql://" + url + "/" + bd;
        BD = bd;
        LOGIN = user;
        PASS = pass;
    }

    @Override
    public boolean Open() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.Conn = DriverManager.getConnection(this.URL, this.LOGIN, this.PASS);
            if (this.Conn != null) {
                return true;
            } else {
                return false;
            }
            /*} catch (InstantiationException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
            } catch (IllegalAccessException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;*/
            /*} catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;*/
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean Close() {
        try {
            this.Conn.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

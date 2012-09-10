package Interfaces;

import java.sql.SQLException;

public interface IConn {

    public boolean Open() throws Exception;

    public boolean Close();
}

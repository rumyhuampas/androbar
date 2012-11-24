package andro.bar.models;

import entities.Table;
import java.sql.SQLException;

public class TableOrders extends andro.bar.models.Base {

    public void RequestTableClose(Integer tableId) throws SQLException, Exception {
        Table table = new Table(andro.bar.controllers.Welcome.mysql.Conn);
        table.Load(tableId);
        table.RequestClose();
    }
}

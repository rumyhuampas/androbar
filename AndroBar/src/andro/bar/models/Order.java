package andro.bar.models;

import andro.bar.wrappers.ViewDrawer;
import android.view.View;
import java.sql.SQLException;

public class Order extends andro.bar.models.Base {

    public void SaveOrder(Integer tableId) throws SQLException, Exception {
        entities.Order order = new entities.Order(andro.bar.controllers.Welcome.mysql.Conn);
        order.TableId = tableId;
        order.Save();
    }
}

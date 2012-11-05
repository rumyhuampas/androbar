package andro.bar.models;

import andro.bar.wrappers.OrderItem;
import entities.Table;
import entities.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Order extends andro.bar.models.Base {

    public void SaveOrder(Integer tableId) throws SQLException, Exception {
        entities.Order order = new entities.Order(andro.bar.controllers.Welcome.mysql.Conn);
        order.TableId = tableId;
        for(int i=0;i<andro.bar.controllers.Welcome.MainList.Size();i++){
            Product prod = ((OrderItem)andro.bar.controllers.Welcome.MainList.Get(i)).product;
            order.Products.add(prod);
        }
        order.Save();
    }

    public ArrayList GetTables() {
        Object[] tables = null;
        try {
            tables = Table.GetAll(andro.bar.controllers.Welcome.mysql.Conn);
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList t = new ArrayList();
        for (int i = 0; i < tables.length; i++) {
            t.add(((Object[]) tables[i])[1]);
        }
        return t;
    }
}

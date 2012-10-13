package andro.bar.models;

import entities.Product;
import java.sql.SQLException;

public class Products extends andro.bar.models.Base {

    public Object[] LoadProducts(Integer catId) throws SQLException, Exception {
        Object[] products = Product.GetAll(andro.bar.controllers.Welcome.mysql.Conn);
        return products;
    }
}
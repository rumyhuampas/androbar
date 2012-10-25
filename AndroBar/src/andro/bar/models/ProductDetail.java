package andro.bar.models;

import entities.Product;
import java.sql.SQLException;

public class ProductDetail extends andro.bar.models.Base {

    public Product LoadProduct(Integer prodId) throws SQLException, Exception {
        Product prod = new Product(andro.bar.controllers.Welcome.mysql.Conn);
        prod.Load(prodId);
        return prod;
    }
}
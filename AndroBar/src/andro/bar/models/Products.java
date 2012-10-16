package andro.bar.models;

import entities.Category;
import entities.Product;
import java.sql.SQLException;

public class Products extends andro.bar.models.Base {

    public Object[] LoadProducts(Integer catId) throws SQLException, Exception {
        Category cat = new Category(andro.bar.controllers.Welcome.mysql.Conn);
        cat.Load(catId);
        Object[] products = cat.GetProducts();
        return products;
    }
}
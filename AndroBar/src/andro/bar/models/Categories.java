package andro.bar.models;

import entities.Category;
import java.sql.SQLException;
import java.util.ArrayList;

public class Categories extends andro.bar.models.Base {

    public Object[] LoadCategories() throws SQLException, Exception {
        Object[] categories = Category.GetAllWithImage(andro.bar.controllers.Welcome.mysql.Conn);
        return categories;
    }
}

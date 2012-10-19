package andro.bar.models;

import andro.bar.wrappers.ViewDrawer;
import android.view.View;
import entities.Category;
import java.sql.SQLException;

public class Categories extends andro.bar.models.Base {

    public Object[] LoadCategories() throws SQLException, Exception {
        Object[] categories = Category.GetAllWithImage(andro.bar.controllers.Welcome.mysql.Conn);
        return categories;
    }
    
    public Integer GetCategoryId(View objView) throws SQLException{
        return ViewDrawer.GetCategoryId(objView);
    }
}

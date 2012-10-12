package andro.bar.models;

import andro.bar.wrappers.ViewDrawer;
import android.view.View;
import entities.Category;
import java.sql.SQLException;
import java.util.ArrayList;

public class Categories extends andro.bar.models.Base {

    public Object[] LoadCategories() throws SQLException, Exception {
        Object[] categories = Category.GetAllWithImage(andro.bar.controllers.Welcome.mysql.Conn);
        return categories;
    }
    
    public Object[] GetCategory(View objView) throws SQLException{
        Category cat = new Category(andro.bar.controllers.Welcome.mysql.Conn);
        cat.Load(ViewDrawer.GetCategoryId(objView));
        Object[] catObj = new Object[3];
        catObj[0] = cat.getId();
        catObj[1] = cat.Name;
        //catObj[2] = cat.ImageStream;
        return catObj;
    }
}

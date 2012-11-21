package andro.bar.wrappers;

import andro.bar.R;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import java.io.InputStream;

public class ViewDrawer {
    
    public static LinearLayout DrawCategoryRow(Context context) {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        row.setLayoutParams(params);
        
        return row;
    }
    
    public static View DrawCategory(Context context, Integer id, String name, InputStream image) {
        FrameLayout main = new FrameLayout(context);
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        mainParams.weight = 1;
        //mainParams.setMargins(10, 10, 10, 10);
        main.setLayoutParams(mainParams);
        //main.setPadding(10, 10, 10, 10);
        
        ImageView imBack = new ImageView(context);
        imBack.setBackgroundResource(R.drawable.buttonblack);
        
        main.addView(imBack);

        LinearLayout cat = new LinearLayout(context);
        cat.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams catParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        catParams.weight = 1;
        cat.setLayoutParams(catParams);
        cat.setPadding(10, 10, 10, 10);
        //catParams.setMargins(10, 10, 10, 10);
        
        main.addView(cat);
        
        TextView txtId = new TextView(context);
        txtId.setText(id.toString());
        //txtId.setHeight(0);
        txtId.setWidth(0);
        txtId.setVisibility(View.INVISIBLE);
        
        cat.addView(txtId);
        
        ImageView im = new ImageView(context);
        LinearLayout.LayoutParams imParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        imParams.weight = 1;
        imParams.gravity = Gravity.CENTER;
        im.setImageBitmap(BitmapFactory.decodeStream((InputStream) image));
        im.setLayoutParams(imParams);
        
        cat.addView(im);
        
        TextView txt = new TextView(context);
        LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        txtParams.weight = 2;
        txt.setLayoutParams(txtParams);
        txt.setText(name);
        txt.setTextColor(Color.BLACK);
        txt.setGravity(Gravity.CENTER);
        
        cat.addView(txt);
        
        return main;
    }
    
    public static Integer GetCategoryId(View catView){
        FrameLayout main = (FrameLayout) catView;
        LinearLayout cat = (LinearLayout) main.getChildAt(1);
        TextView idView = (TextView) cat.getChildAt(0);
        Integer id = Integer.parseInt(idView.getText().toString());
        return id;
    }
    
    public static Integer GetProductId(View prodView){
        LinearLayout main = (LinearLayout) prodView;
        LinearLayout prod = (LinearLayout) main.getChildAt(0);
        TextView idView = (TextView) prod.getChildAt(0);
        Integer id = Integer.parseInt(idView.getText().toString());
        return id;
    }
    
    public static View DrawProduct(Context context, Integer id, String name, Float price) {
        LinearLayout main = new LinearLayout(context);
        main.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 
                LinearLayout.LayoutParams.FILL_PARENT);
        main.setPadding(10, 10, 10, 10);
        main.setLayoutParams(mainParams);

        LinearLayout prod = new LinearLayout(context);
        prod.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams prodParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 
                LinearLayout.LayoutParams.FILL_PARENT);
        prod.setPadding(10, 10, 10, 10);
        prod.setLayoutParams(prodParams);
        
        main.addView(prod);
        
        TextView txtId = new TextView(context);
        txtId.setText(id.toString());
        //txtId.setHeight(0);
        txtId.setWidth(0);
        txtId.setVisibility(View.INVISIBLE);
        
        prod.addView(txtId);
        
        TextView txt = new TextView(context);
        LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        txtParams.weight = 1;
        txt.setLayoutParams(txtParams);
        txt.setText(name);
        txt.setTextColor(Color.WHITE);
        
        prod.addView(txt);
        
        TextView txtPrice = new TextView(context);
        LinearLayout.LayoutParams txtPriceParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        txtPriceParams.weight = 4;
        txtPrice.setLayoutParams(txtPriceParams);
        txtPrice.setText("$ " + String.valueOf(price));
        txtPrice.setTextColor(Color.WHITE);
        txtPrice.setGravity(Gravity.RIGHT);
        
        prod.addView(txtPrice);
        
        View sep = new View(context);
        LinearLayout.LayoutParams sepParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 1);
        sep.setLayoutParams(sepParams);
        sep.setBackgroundColor(Color.WHITE);
        
        main.addView(sep);
        
        return main;
    }
    
    public static View DrawToolBar(Context context, View.OnClickListener orderOnClickHandler, View.OnClickListener searchOnClickHandler) {
        LinearLayout main = new LinearLayout(context);
        main.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 
                LinearLayout.LayoutParams.FILL_PARENT);
        main.setPadding(10, 10, 10, 10);
        main.setLayoutParams(mainParams);
        main.setBackgroundColor(Color.LTGRAY);
        //main.setGravity(Gravity.RIGHT);
        
        LinearLayout llLeft = new LinearLayout(context);
        llLeft.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams llLeftParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 
                LinearLayout.LayoutParams.FILL_PARENT);
        llLeftParams.weight = 1;
        llLeft.setPadding(10, 10, 10, 10);
        llLeft.setLayoutParams(llLeftParams);
        llLeft.setBackgroundColor(Color.LTGRAY);
        
        LinearLayout llRight = new LinearLayout(context);
        main.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams llRightParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 
                LinearLayout.LayoutParams.FILL_PARENT);
        llRightParams.weight = 1;
        llRight.setPadding(10, 10, 10, 10);
        llRight.setLayoutParams(llRightParams);
        llRight.setBackgroundColor(Color.LTGRAY);
        llRight.setGravity(Gravity.RIGHT);
        
        main.addView(llLeft);
        main.addView(llRight);
        
        ImageView imSearch = new ImageView(context);
        LinearLayout.LayoutParams imSearchParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        //imCartParams.weight = 1;
        //imCartParams.gravity = Gravity.RIGHT;
        imSearch.setBackgroundResource(R.drawable.search);
        imSearch.setLayoutParams(imSearchParams);
        
        imSearch.setOnClickListener(searchOnClickHandler);
        
        llLeft.addView(imSearch);
        
        ImageView imCart = new ImageView(context);
        LinearLayout.LayoutParams imCartParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        //imCartParams.weight = 1;
        //imCartParams.gravity = Gravity.RIGHT;
        imCart.setBackgroundResource(R.drawable.cart);
        imCart.setLayoutParams(imCartParams);
        
        imCart.setOnClickListener(orderOnClickHandler);
        
        llRight.addView(imCart);
        
        return main;
    }
    
    public static View DrawTableOrdersToolBar(Context context, View.OnClickListener btnSearchOnClickHandler) {
        LinearLayout main = new LinearLayout(context);
        main.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 
                LinearLayout.LayoutParams.FILL_PARENT);
        main.setPadding(10, 10, 10, 10);
        main.setLayoutParams(mainParams);
        main.setBackgroundColor(Color.LTGRAY);
        //main.setGravity(Gravity.RIGHT);
        
        TextView lbl = new TextView(context);
        lbl.setTextColor(Color.BLACK);
        lbl.setText("Buscar Mesa: ");
        
        main.addView(lbl);
        
        EditText txt = new EditText(context);
        LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 
                LinearLayout.LayoutParams.FILL_PARENT);
        txtParams.weight = 1;
        txt.setLayoutParams(txtParams);
        txt.setInputType(InputType.TYPE_CLASS_NUMBER);
        
        main.addView(txt);
        
        Button btn = new Button(context);
        btn.setText("Buscar");
        btn.setOnClickListener(btnSearchOnClickHandler);
        
        main.addView(btn);
        
        return main;
    }
    
    public static String GetTableToolbarSearch(View toolbar){
        EditText txt = (EditText)((LinearLayout)toolbar).getChildAt(1);
        return txt.getText().toString();
    }
}

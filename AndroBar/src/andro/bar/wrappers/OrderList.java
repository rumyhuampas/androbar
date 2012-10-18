package andro.bar.wrappers;

import andro.bar.R;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import entities.Product;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderList {

    ArrayList list;

    public OrderList() {
        list = new ArrayList();
    }

    public void Clear() {
        list.clear();
    }

    public void Add(int prodId) {
        try {
            andro.bar.controllers.Welcome.mysql.Open();
            try {
                boolean found = false;
                OrderItem item = null;
                for (int i = 0; i < list.size(); i++) {
                    item = (OrderItem) list.get(i);
                    if (item.product.getId() == prodId) {
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    Product prod = new Product(andro.bar.controllers.Welcome.mysql.Conn);
                    prod.Load(prodId);
                    OrderItem newItem = new OrderItem();
                    newItem.product = prod;
                    newItem.amount = 1;
                    list.add(newItem);
                } else {
                    item.amount++;
                }
            } finally {
                andro.bar.controllers.Welcome.mysql.Close();
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public View DrawList(Context context) {
        LinearLayout main = new LinearLayout(context);
        main.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        main.setPadding(10, 10, 10, 10);
        main.setLayoutParams(mainParams);

        main.addView(DrawTableHeaders(context));

        main.addView(DrawSeparator(context));

        for (int i = 0; i < list.size(); i++) {
            OrderItem item = (OrderItem) list.get(i);

            LinearLayout prod = new LinearLayout(context);
            prod.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams prodParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT);
            prod.setPadding(10, 10, 10, 10);
            prod.setLayoutParams(prodParams);

            main.addView(prod);

            TextView txt = new TextView(context);
            LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT);
            txtParams.weight = 1;
            txt.setLayoutParams(txtParams);
            txt.setText(item.product.Name);
            txt.setTextColor(Color.WHITE);

            prod.addView(txt);

            TextView txtPrice = new TextView(context);
            LinearLayout.LayoutParams txtPriceParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT);
            txtPriceParams.width = 128;
            txtPrice.setLayoutParams(txtPriceParams);
            txtPrice.setText("$ " + String.valueOf(item.product.Price));
            txtPrice.setTextColor(Color.WHITE);
            txtPrice.setGravity(Gravity.RIGHT);

            prod.addView(txtPrice);

            TextView txtAmount = new TextView(context);
            LinearLayout.LayoutParams txtAmountParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT);
            txtAmountParams.width = 64;
            txtAmount.setLayoutParams(txtAmountParams);
            txtAmount.setText(String.valueOf(item.amount));
            txtAmount.setTextColor(Color.WHITE);
            txtAmount.setGravity(Gravity.CENTER);

            prod.addView(txtAmount);

            ImageView im = new ImageView(context);
            LinearLayout.LayoutParams imParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT);
            imParams.width = 32;
            imParams.gravity = Gravity.CENTER;
            im.setBackgroundResource(R.drawable.stop);
            im.setLayoutParams(imParams);
            im.setPadding(5, 5, 5, 5);

            prod.addView(im);

            main.addView(DrawSeparator(context));
        }

        return main;
    }

    private View DrawTableHeaders(Context context) {
        LinearLayout header = new LinearLayout(context);
        header.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams prodParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        header.setPadding(10, 10, 10, 10);
        header.setLayoutParams(prodParams);

        TextView txt = new TextView(context);
        LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        txtParams.weight = 1;
        txt.setLayoutParams(txtParams);
        txt.setText("Nombre");
        txt.setTextColor(Color.WHITE);

        header.addView(txt);

        TextView txtPrice = new TextView(context);
        LinearLayout.LayoutParams txtPriceParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        txtPriceParams.width = 128;
        txtPrice.setLayoutParams(txtPriceParams);
        txtPrice.setText("Precio Unit.");
        txtPrice.setTextColor(Color.WHITE);
        txtPrice.setGravity(Gravity.RIGHT);

        header.addView(txtPrice);

        TextView txtAmount = new TextView(context);
        LinearLayout.LayoutParams txtAmountParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        txtAmountParams.width = 64;
        txtAmount.setLayoutParams(txtAmountParams);
        txtAmount.setText("Cant.");
        txtAmount.setTextColor(Color.WHITE);
        txtAmount.setGravity(Gravity.RIGHT);

        header.addView(txtAmount);
        
        TextView txtBtn = new TextView(context);
        LinearLayout.LayoutParams txtBtnParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        txtBtnParams.width = 32;
        txtBtn.setLayoutParams(txtBtnParams);
        txtBtn.setText("");
        txtBtn.setTextColor(Color.WHITE);
        txtBtn.setGravity(Gravity.RIGHT);

        header.addView(txtBtn);

        return header;
    }

    private View DrawSeparator(Context context) {
        View sep = new View(context);
        LinearLayout.LayoutParams sepParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 1);
        sep.setLayoutParams(sepParams);
        sep.setBackgroundColor(Color.WHITE);

        return sep;
    }
}

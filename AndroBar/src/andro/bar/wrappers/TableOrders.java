package andro.bar.wrappers;

import andro.bar.R;
import andro.bar.wrappers.dialogs.LoadingDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import entities.Order;
import entities.Table;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableOrders {

    private Integer TableId;
    private Object[] Orders;
    private Context currentContext;
    private LinearLayout mainView;
    private Button TableCloseButton;
    private andro.bar.views.Base view;

    public TableOrders(andro.bar.views.Base v, Integer tableId) {
        view = v;
        TableId = tableId;
        GetOrders();
    }

    private void GetOrders() {
        try {
            try {
                andro.bar.controllers.Welcome.mysql.Open();
                Orders = Table.GetActiveOrders(andro.bar.controllers.Welcome.mysql.Conn, TableId);
            } catch (Exception ex) {
                Logger.getLogger(TableOrders.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            andro.bar.controllers.Welcome.mysql.Close();
        }
    }
    private View.OnClickListener ItemOnClickHandler = new View.OnClickListener() {

        public void onClick(View objView) {
            //final LoadingDialog loadDialog = view.CreateLoadingMessage(currentContext, "Orden", "Enviando pedido...");
            //loadDialog.show();

            try {
                try {
                    Integer id = (Integer) ((ImageView) objView).getTag();

                    andro.bar.controllers.Welcome.mysql.Open();
                    Order order = new Order(andro.bar.controllers.Welcome.mysql.Conn);
                    order.Load(id);
                    order.RequestCancelation();

                } catch (Exception ex) {
                    Logger.getLogger(TableOrders.class.getName()).log(Level.SEVERE, null, ex);
                }
            } finally {
                andro.bar.controllers.Welcome.mysql.Close();
            }

            mainView.removeAllViews();
            GetOrders();
            DrawList(currentContext, mainView, TableCloseButton);

            //loadDialog.hide();
            view.ShowToast(currentContext, "Pedido enviado");
        }
    };
    /*
     * private View.OnLongClickListener ItemOnLongClickHandler = new
     * View.OnLongClickListener() {
     *
     * public boolean onLongClick(View objView) { Integer id = (Integer)
     * ((ImageView) objView).getTag();
     *
     * OrderItem item = null; for (int i = 0; i < list.size(); i++) { item =
     * (OrderItem) list.get(i); if (item.product.getId() == id) {
     * list.remove(i); } } mainView.removeAllViews(); DrawList(currentContext,
     * mainView, TableCloseButton); return true; } };
     */

    public void DrawList(Context context, LinearLayout mainV, Button tableCloseButton) {
        currentContext = context;
        mainView = mainV;
        TableCloseButton = tableCloseButton;

        if (Orders.length <= 0) {
            TableCloseButton.setEnabled(false);
        } else {
            TableCloseButton.setEnabled(true);
        }

        LinearLayout main = new LinearLayout(context);
        main.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        main.setPadding(10, 10, 10, 10);
        main.setLayoutParams(mainParams);

        main.addView(DrawTableHeaders(context));

        main.addView(DrawSeparator(context));

        for (int i = 0; i < Orders.length; i++) {
            Order item = (Order) Orders[i];

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
            txt.setText(((Integer) item.getId()).toString());
            txt.setTextColor(Color.WHITE);

            prod.addView(txt);

            TextView txtDate = new TextView(context);
            LinearLayout.LayoutParams txtDateParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT);
            txtDateParams.weight = 1;
            txtDate.setLayoutParams(txtDateParams);
            txtDate.setText(String.valueOf(item.getDateTime()));
            txtDate.setTextColor(Color.WHITE);

            prod.addView(txtDate);

            ImageView im = new ImageView(context);
            LinearLayout.LayoutParams imParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT);
            imParams.width = 32;
            imParams.gravity = Gravity.CENTER;
            im.setBackgroundResource(R.drawable.stop);
            im.setLayoutParams(imParams);
            //im.setPadding(5, 5, 5, 5);
            im.setTag(item.getId());
            im.setOnClickListener(ItemOnClickHandler);
            //im.setOnLongClickListener(ItemOnLongClickHandler);

            prod.addView(im);

            main.addView(DrawSeparator(context));
        }

        mainV.addView(main);
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
        txt.setText("Num de Orden");
        txt.setTextColor(Color.WHITE);

        header.addView(txt);

        TextView txtDate = new TextView(context);
        LinearLayout.LayoutParams txtDateParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        txtDateParams.weight = 1;
        txtDate.setLayoutParams(txtDateParams);
        txtDate.setText("Fecha");
        txtDate.setTextColor(Color.WHITE);

        header.addView(txtDate);

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

package andro.bar.views;

import andro.bar.R;
import andro.bar.wrappers.ViewDrawer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class TableOrders extends andro.bar.views.Base {
    private andro.bar.TableOrders Activity;
    private View Toolbar;
    

    public TableOrders(andro.bar.TableOrders activity) {
        Activity = activity;
    }
    
    public void DrawToolBar(View.OnClickListener btnSearchOnClickHandler){
        LinearLayout llMain = (LinearLayout) Activity.findViewById(R.id.tableorder_Main);
        llMain.removeAllViews();
        Toolbar = ViewDrawer.DrawTableOrdersToolBar(Activity, btnSearchOnClickHandler);
        llMain.addView(Toolbar);
    }
    
    public String GetSearch(){
        return ViewDrawer.GetTableToolbarSearch(Toolbar);
    }
    
    public void DrawList(andro.bar.wrappers.TableOrders orders){
        LinearLayout llMain = (LinearLayout) Activity.findViewById(R.id.tableorder_List);
        llMain.removeAllViews();
        orders.DrawList(Activity, llMain, (Button)Activity.findViewById(R.id.tableorder_btnTableClose));
        //llMain.addView(order);
    }
}

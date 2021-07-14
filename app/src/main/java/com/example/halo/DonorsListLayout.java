package com.example.halo;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DonorsListLayout extends LinearLayout {
    TextView userID;
    TextView quantity;
    TextView itemName;
    TextView description;

    public DonorsListLayout(Context p){
        super(p);
        setOrientation(LinearLayout.HORIZONTAL);
        userID = new TextView(p);
        quantity = new TextView(p);
        itemName = new TextView(p);
        description = new TextView(p);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.weight = 0;
        userID.setPaddingRelative(50,0,0,0);
        addView(userID, lp);

        LinearLayout rightLayout = new LinearLayout(p);
        rightLayout.setOrientation(LinearLayout.VERTICAL);
        rightLayout.addView(quantity);
        rightLayout.addView(itemName);
        rightLayout.addView(description);

        LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LP.weight = 1;
        quantity.setPaddingRelative(200,0,0,0);
        itemName.setPaddingRelative(200,0,0,0);
        description.setPaddingRelative(200,0,0,0);
        addView(rightLayout, LP);
    }

    public void populate(JSONObject item) throws JSONException {
        userID.setText("ID Number: " + item.getString("USER_ID"));
        quantity.setText(item.getString("QUANTITY") + " items donated");
        itemName.setText(item.getString("ITEM_NAME"));
        description.setText(item.getString("DESCRIPTION"));
    }
}

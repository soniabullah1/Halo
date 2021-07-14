package com.example.halo;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class RecipientListLayout extends LinearLayout {
    TextView userID;
    TextView quantity;
    TextView itemName;
    TextView description;
    TextView motivation;
    TextView phoneNum;

    public RecipientListLayout(Context p){
        super(p);
        setOrientation(LinearLayout.HORIZONTAL);
        userID = new TextView(p);
        quantity = new TextView(p);
        itemName = new TextView(p);
        description = new TextView(p);
        motivation = new TextView(p);
        phoneNum = new TextView(p);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.weight = 0;
        userID.setPaddingRelative(50,0,150,0);
        addView(userID, lp);

        LinearLayout rightLayout = new LinearLayout(p);
        rightLayout.setOrientation(LinearLayout.VERTICAL);
        rightLayout.addView(quantity);
        rightLayout.addView(itemName);
        rightLayout.addView(description);
        rightLayout.addView(motivation);
        rightLayout.addView(phoneNum);

        LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LP.weight = 1;
        quantity.setPaddingRelative(100,0,0,0);
        itemName.setPaddingRelative(100,0,0,0);
        description.setPaddingRelative(100,0,0,0);
        motivation.setPaddingRelative(100,0,0,0);
        phoneNum.setPaddingRelative(100,0,0,0);
        addView(rightLayout, LP);
    }

    public void populate(JSONObject item) throws JSONException {
        userID.setText("ID Number: " + item.getString("USER_ID"));
        quantity.setText(item.getString("QUANTITY") + " items requested");
        itemName.setText("Item name: " + item.getString("ITEM_NAME"));
        description.setText("Description: " + item.getString("DESCRIPTION"));
        motivation.setText("Motivation: " + item.getString("MOTIVATION"));
        phoneNum.setText("Contact Number: " + item.getString("CONTACT_NUM"));
    }
}




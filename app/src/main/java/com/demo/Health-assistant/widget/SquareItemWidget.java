package com.demo.motion.widget;

import android.view.View;
import android.widget.TextView;
import com.demo.motion.R;

public class SquareItemWidget {           

    private TextView mSquareTitle;
    private TextView mSquareValue;
    
    public SquareItemWidget(View layout, String title) {
    	mSquareTitle = (TextView)layout.findViewById(R.id.SquareItemTitle);
    	mSquareValue = (TextView)layout.findViewById(R.id.SquareItemValue);
    	mSquareTitle.setText(title);    	
    }         
    
    public void setItemValue(String value) {
    	mSquareValue.setText(value);
    }
    
    public String getItemValue() {
    	return mSquareValue.getText().toString();
    }
}

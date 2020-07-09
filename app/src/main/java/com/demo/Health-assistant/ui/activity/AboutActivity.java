package com.demo.motion.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import com.demo.motion.R;
public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    getActionBar().setDisplayHomeAsUpEnabled(true);        
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));        		
		setContentView(R.layout.activity_about);
		getActionBar().setTitle(getString(R.string.activity_title_about));
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case android.R.id.home:
	         finish();
	    default:
	         break;
	    }
	    return true;
	}
}

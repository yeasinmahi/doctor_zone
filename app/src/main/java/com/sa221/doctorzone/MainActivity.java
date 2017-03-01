package com.sa221.doctorzone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		PopulatedOpenHelper.getInstance(getApplicationContext());

	}

	public void Next(View v) {
		Intent intentMain = new Intent(MainActivity.this, Search.class);
		MainActivity.this.startActivity(intentMain);
		Log.i("Content ", " Main layout ");
	}
	public void Tab(View v) {
		Intent intentMain = new Intent(MainActivity.this, Search.class);
		MainActivity.this.startActivity(intentMain);
		Log.i("Content ", " Main layout ");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

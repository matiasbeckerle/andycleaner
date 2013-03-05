package com.matiasb.andycleaner;

import java.lang.reflect.Method;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Buttons
		Button bClean = (Button) findViewById(R.id.bClean);
		Button bAbout = (Button) findViewById(R.id.bAbout);
		Button bExit = (Button) findViewById(R.id.bExit);

		// Events
		bClean.setOnClickListener(this);
		bAbout.setOnClickListener(this);
		bExit.setOnClickListener(this);
	}

	/**
	 * Click listener.
	 * 
	 * @param View
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bClean:
				// The main action button
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage(R.string.alert_delete_all)
						.setPositiveButton(R.string.yes,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										deleteAll();
									}
								}).setNegativeButton(R.string.cancel, null);
				AlertDialog alert = builder.create();
				alert.show();
				break;
	
			case R.id.bAbout:
				// New intent for the about screen
				Intent about = new Intent(Main.this, About.class);
				startActivity(about);
				break;
	
			case R.id.bExit:
				// Close the app
				finish();
				break;
		}
	}

	/**
	 * Delete the cache of all apps.
	 */
	private void deleteAll() {
		PackageManager pm = getPackageManager();
		Method[] methods = pm.getClass().getDeclaredMethods();

		for (Method m : methods) {
			if (m.getName().equals("freeStorageAndNotify")) {
				try {
					long desiredFreeStorage = Long.MAX_VALUE;
					m.invoke(pm, desiredFreeStorage, null);
				} catch (Exception e) {
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setMessage(e.getMessage()).setNegativeButton(
							R.string.cancel, null);
					AlertDialog alert = builder.create();
					alert.show();
				}
				break;
			}
		}
	}

}

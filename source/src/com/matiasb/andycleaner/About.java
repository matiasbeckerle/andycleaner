package com.matiasb.andycleaner;

import android.os.Bundle;
import android.app.Activity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class About extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.about);
		
		// Active links
		TextView lblCopyright = (TextView) findViewById(R.id.lblCopyright);
		lblCopyright.setMovementMethod(LinkMovementMethod.getInstance());

		// Button
		Button bReturn = (Button) findViewById(R.id.bReturn);
		bReturn.setOnClickListener(this);
	}

	/**
	 * Click listener.
	 * 
	 * @param View
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bReturn:
				finish();
				break;
		}
	}

}
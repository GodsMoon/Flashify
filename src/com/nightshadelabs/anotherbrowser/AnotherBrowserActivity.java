package com.nightshadelabs.anotherbrowser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class AnotherBrowserActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent intent = getIntent();
		if (savedInstanceState == null && intent != null) {
            Log.d("TAG", "intent != null");

            if (intent.getAction().equals(Intent.ACTION_SEND)) {
                Log.d("TAG","intent.getAction().equals(Intent.ACTION_SEND)");
                String url = intent.getStringExtra(Intent.EXTRA_TEXT);

				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				//startActivity(i);
				
				//i.putExtra(android.content.Intent.EXTRA_TEXT), url);
				startActivity(Intent.createChooser(i, "Open in New Browser"));
            }
    	}
		finish();
    }
}
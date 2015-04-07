package com.nightshadelabs.anotherbrowser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpVersion;

public class AnotherBrowserActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
		if (intent != null) {

            if (intent.getAction().equals(Intent.ACTION_SEND)) {

                if(BuildConfig.DEBUG)
                    Log.d("TAG","intent.getAction().equals(Intent.ACTION_SEND)");

                String urlText = intent.getStringExtra(Intent.EXTRA_TEXT);

                Uri uri = findUrlInString(urlText);

                Intent i = new Intent(Intent.ACTION_VIEW);

                if(uri != null) {
                    i.setData(uri);
                    startActivity(Intent.createChooser(i, getString(R.string.open_in_browser)));
                }else{
                    Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show();
                }
            }
    	}
		finish();
    }

    private Uri findUrlInString(String urlText) {

        int indexOfUrl = urlText.toLowerCase().indexOf(HttpVersion.HTTP.toLowerCase());
        if(indexOfUrl == -1)
            return null;
        else{
            String containsURL = urlText.substring(indexOfUrl);

            int endOfUrl = containsURL.indexOf(" ");

            String url;
            if(endOfUrl != -1){
                url = containsURL.substring(0, endOfUrl);
            }else{
                url = containsURL;
            }

            return Uri.parse(url);
        }
    }
}


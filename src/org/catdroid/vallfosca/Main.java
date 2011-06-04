package org.catdroid.vallfosca;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);      
        i.setDataAndType(Uri.parse("file://sdcard/vallfosca/json_demo.txt"), "application/mixare-json");
        startActivity(i);
    }
}
package org.catdroid.vallfosca;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Main extends Activity implements OnClickListener {
	LanguageManager languageManager;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        findViewById(R.id.option_villages).setOnClickListener(this);
//        findViewById(R.id.option_routes).setOnClickListener(this);
//        findViewById(R.id.option_virtual).setOnClickListener(this);
               
//        languageManager=new LanguageManager(this);
        FileManager.saveas(R.raw.vallfosca, "//sdcard/vallfosca/", "vallfosca.json", this);
       
    }
	
	public void onClick(View v)
	{
		switch (v.getId())
		{
			
			case R.id.option_villages:
		        Intent i = new Intent();
		        i.setAction(Intent.ACTION_VIEW);      
		       
		        i.setDataAndType(Uri.parse("file://sdcard/vallfosca/vallfosca.json"), "application/mixare-vallfosca-json");
//		        i.setDataAndType(Uri.parse("org.catdroid.vallfosca:raw/vallfosca"), "application/mixare-json");
		        startActivity(i);

		        break;
			
		}
		
	}
}
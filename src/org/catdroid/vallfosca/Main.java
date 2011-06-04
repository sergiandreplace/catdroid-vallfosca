package org.catdroid.vallfosca;

import android.app.Activity;
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
        languageManager=new LanguageManager(this);
        
        findViewById(R.id.option_info).setOnClickListener(this);
        findViewById(R.id.option_routes).setOnClickListener(this);
       
    }
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.option_info:
				View group_info = findViewById(R.id.group_info);
				if (group_info.getVisibility()==View.VISIBLE) 
					group_info.setVisibility(View.GONE);
				else 
					group_info.setVisibility(View.VISIBLE);
				break;
			case R.id.option_routes:
				View group_routes = findViewById(R.id.group_routes);
				if (group_routes.getVisibility()==View.VISIBLE) 
					group_routes.setVisibility(View.GONE);
				else 
					group_routes.setVisibility(View.VISIBLE);
				break;
			
		}
		
	}
}
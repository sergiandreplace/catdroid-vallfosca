package org.catdroid.vallfosca;

import java.util.Locale;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Configuration;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class LanguageManager implements OnCancelListener, OnClickListener
{
	private Context context;
	private Preferences preferences;
	private Dialog languageDialog;
 
	public LanguageManager(Context context)
	{
		this.context = context;
		preferences = new Preferences(context);
	}

	public void showLanguageDialog()
	{
		languageDialog = new Dialog(context);
		languageDialog.setContentView(R.layout.language_select);
		if (preferences.isLanguageSet())
		{
			languageDialog.setCancelable(true);
		}
		((LinearLayout)languageDialog.findViewById(R.id.catalan)).setOnClickListener(this);
		((LinearLayout)languageDialog.findViewById(R.id.spanish)).setOnClickListener(this);
		((LinearLayout)languageDialog.findViewById(R.id.english)).setOnClickListener(this);
		languageDialog.show();
	}

	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.catalan:
				preferences.setLanguage("ca_ES");
				break;
			case R.id.spanish:
				preferences.setLanguage("es_ES");
				break;
			case R.id.english:
				preferences.setLanguage("en_US");
				break;
		}
		 Locale locale = new Locale("es");
	        //Locale.setDefault(locale);
	        Configuration config = new Configuration();
	        config.locale = locale;
	        context.getResources().updateConfiguration(config,
	        	context.getResources().getDisplayMetrics());
		languageDialog.dismiss();
	}

	public void onCancel(DialogInterface arg0)
	{
		// TODO Auto-generated method stub

	}
}

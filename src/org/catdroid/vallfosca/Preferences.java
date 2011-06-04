package org.catdroid.vallfosca;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences 
{
	private SharedPreferences preferences;

	public Preferences(Context context)
	{
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public String getLanguage()
	{
		return preferences.getString("Language", null);
	}
	
	public void setLanguage(String language)
	{
		
		preferences.edit().putString("Language", language);
	}
}

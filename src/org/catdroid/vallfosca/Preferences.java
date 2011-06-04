package org.catdroid.vallfosca;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
/**
 * This class is responsible to handle the application preferences
 * @author Sergi
 *
 */
public class Preferences 
{
	private SharedPreferences preferences;
	private Context context;
	/**
	 * Constructor for the class
	 * @param context the context that will be used to retrieve ResourceManager
	 */
	public Preferences(Context context)
	{
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
		this.context=context;
	}
	/**
	 * returns the current language selected by user
	 * @return String containing the language
	 */
	public String getLanguage()
	{
		return preferences.getString(getPreferenceName(R.string.pref_language), null);
	}
	/**
	 * Sets the language preference
	 * @param language the language name
	 */
	public void setLanguage(String language)
	{
		
		preferences.edit().putString(getPreferenceName(R.string.pref_language), language);
	}
	/**
	 * Checks if the language is already set or not
	 * @return boolean telling if the language is set
	 */
	public boolean isLanguageSet()
	{
		return getLanguage()!=null;
	}
	/**
	 * Shortcut to get the name of a preference based on its resource id
	 * @param resId the resource id
	 * @return String The name of the preference
	 */
	private String getPreferenceName(int resId)
	{
		return context.getString(resId);
	}
}

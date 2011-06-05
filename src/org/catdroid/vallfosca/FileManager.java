package org.catdroid.vallfosca;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

public class FileManager
{
	public static boolean saveas(int resource,String path, String filename, Context context)
	{
		byte[] buffer = null;
		InputStream fIn = context.getResources().openRawResource(resource);
		int size = 0;

		try
		{
			size = fIn.available();
			buffer = new byte[size];
			fIn.read(buffer);
			fIn.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			return false;
		}

		boolean exists = (new File(path)).exists();
		if (!exists)
		{
			new File(path).mkdirs();
		}

		FileOutputStream save;
		try
		{
			save = new FileOutputStream(path + filename);
			save.write(buffer);
			save.flush();
			save.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			return false;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			return false;
		}

		return true;
	}
}

package settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class SettingStorage
{
	private String name;

	private Properties properties;

	public SettingStorage(String name)
	{
		this.name = name;

		properties = new Properties();

		loadProperties();
	}

	public int getIntSetting(String key)
	{
		return getIntSetting(key, 0);
	}

	public int getIntSetting(String key, int defaultValue)
	{
		return Integer.parseInt(properties.getProperty(key, Integer.toString(defaultValue)));
	}

	public void setIntSetting(String key, int value)
	{
		properties.setProperty(key, Integer.toString(value));

		storeProperties();
	}

	private void loadProperties()
	{
		try (InputStream is = new FileInputStream(getFile(name)))
		{
			properties.load(is);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void storeProperties()
	{
		try (OutputStream os = new FileOutputStream(getFile(name)))
		{
			properties.store(os, null);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static File getFile(String name)
	{
		File file = Paths.get(System.getenv("APPDATA"), "PPM Assistant", name).toFile();

		try
		{
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return file;
	}
}

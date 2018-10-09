package settings;

public class AppSettings
{
	private static final String PREVIOUS_TAB_INDEX = "previousTabIndex";

	private SettingStorage settingStorage;

	public AppSettings(SettingStorage settingStorage)
	{
		this.settingStorage = settingStorage;
	}

	public int getPreviousTabIndex()
	{
		return settingStorage.getIntSetting(PREVIOUS_TAB_INDEX, 0);
	}

	public void setPreviousTabIndex(int index)
	{
		settingStorage.setIntSetting(PREVIOUS_TAB_INDEX, index);
	}
}

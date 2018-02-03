package settings;

public class Settings
{
	private static final String FACILITY_LEVEL = "facilityLevel";
	private static final String STAFF_EFFECTIVNESS = "staffEffectivness";

	private SettingStorage settingStorage;

	public Settings(SettingStorage settingStorage)
	{
		this.settingStorage = settingStorage;
	}

	public int getFacilityLevel()
	{
		return settingStorage.getIntSetting(FACILITY_LEVEL, 1);
	}

	public void setFacilityLevel(int facilityLevel)
	{
		settingStorage.setIntSetting(FACILITY_LEVEL, facilityLevel);
	}

	public int getStaffEffectivness()
	{
		return settingStorage.getIntSetting(STAFF_EFFECTIVNESS);
	}

	public void setStaffEffectivness(int staffEffectivness)
	{
		settingStorage.setIntSetting(STAFF_EFFECTIVNESS, staffEffectivness);
	}
}

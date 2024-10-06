package com.staples.payment.shared.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class CountryCodeUtil
{
	private final Map<String, String> iso3ToIso2CountryMap;

	public CountryCodeUtil()
	{
		iso3ToIso2CountryMap = createIso3ToIso2CountryMap();
	}

	private static Map<String, String> createIso3ToIso2CountryMap()
	{
		Map<String, String> iso3ToIso2CountryMap = new HashMap<String, String>();

		String[] iso2CountryCodes = Locale.getISOCountries();
		for(String iso2CountryCode : iso2CountryCodes)
		{
			Locale locale = new Locale("", iso2CountryCode);
			String iso3CountryCode = locale.getISO3Country();

			if(!"".equals(iso3CountryCode) && !"".equals(locale.getCountry())
					&& !"".equals(locale.getDisplayCountry()))
			{
				iso3ToIso2CountryMap.put(iso3CountryCode, iso2CountryCode);
			}
		}
		return iso3ToIso2CountryMap;
	}

	public @Nullable String getLength2Code(@Nullable String countryCode)
	{
		if(countryCode == null)
		{
			return null;
		}
		else if(countryCode.length() == 2)
		{
			return countryCode; // TODO: Do we want to validate the correctness of the length 2 code?
		}
		else if(countryCode.length() == 3)
		{
			return convertIso3ToIso2(countryCode);
		}
		else
		{
			throw new RuntimeException("The length of " + countryCode + " is not a valid length for a country code in our system. Must be length 2 or 3.");
		}
	}

	private String convertIso3ToIso2(String iso3CountryCode)
	{
		return iso3ToIso2CountryMap.get(iso3CountryCode.toUpperCase());
	}

	public @Nullable String getLength3Code(@Nullable String countryCode) // TODO: Do we want to store the Country Codes in a unified format by using this is settlemain?
	{
		if(countryCode == null)
		{
			return null;
		}
		else if(countryCode.length() == 2)
		{
			return convertIso2ToIso3(countryCode);
		}
		else if(countryCode.length() == 3)
		{
			return countryCode; // TODO: Do we want to validate the correctness of the length 3 code?
		}
		else
		{
			throw new RuntimeException("The length of " + countryCode + " is not a valid length for a country code in our system. Must be length 2 or 3.");
		}
	}

	private String convertIso2ToIso3(String iso2CountryCode)
	{
		Locale locale = new Locale("", iso2CountryCode.toUpperCase());

		return locale.getISO3Country();
	}
}
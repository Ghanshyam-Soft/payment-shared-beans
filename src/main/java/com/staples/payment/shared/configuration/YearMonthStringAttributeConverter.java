package com.staples.payment.shared.configuration;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.lang.Nullable;

@Converter(autoApply = true)
public class YearMonthStringAttributeConverter implements AttributeConverter<YearMonth, String>
{
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");

	@Override
	public @Nullable String convertToDatabaseColumn(@Nullable YearMonth attribute)
	{
		if(attribute == null)
		{
			return null;
		}

		return attribute.format(formatter);
	}

	@Override
	public @Nullable YearMonth convertToEntityAttribute(@Nullable String dbData)
	{
		if(dbData == null)
		{
			return null;
		}

		return YearMonth.parse(dbData, formatter);
	}
}
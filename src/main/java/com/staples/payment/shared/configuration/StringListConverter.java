package com.staples.payment.shared.configuration;

import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class StringListConverter implements AttributeConverter<List<String>, String>
{
	private static final String SPLIT_CHAR = ",";

	@Override
	public String convertToDatabaseColumn(List<String> stringList)
	{
		return stringList != null ? String.join(SPLIT_CHAR, stringList) : null;
	}

	@Override
	public List<String> convertToEntityAttribute(String string)
	{
		return string != null ? Arrays.asList(string.split(SPLIT_CHAR)) : null;
	}
}
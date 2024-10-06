package com.staples.payment.shared.configuration;

import java.net.URI;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.lang.Nullable;

@Converter(autoApply = true)
public class UriStringAttributeConverter implements AttributeConverter<URI, String>
{
	@Override
	public @Nullable String convertToDatabaseColumn(@Nullable URI attribute)
	{
		if(attribute == null)
		{
			return null;
		}

		return attribute.toString();
	}

	@Override
	public @Nullable URI convertToEntityAttribute(@Nullable String dbData)
	{
		if(dbData == null)
		{
			return null;
		}

		return URI.create(dbData);
	}
}
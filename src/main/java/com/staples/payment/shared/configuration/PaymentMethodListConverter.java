package com.staples.payment.shared.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.lang.Nullable;

import com.staples.payment.shared.constant.PaymentMethod;

@Converter(autoApply = false)
public class PaymentMethodListConverter implements AttributeConverter<List<PaymentMethod>, String>
{
	private static final String SPLIT_CHAR = ",";

	@Override
	public @Nullable String convertToDatabaseColumn(@Nullable List<PaymentMethod> paymentMethods)
	{
		if(paymentMethods != null)
		{
			return paymentMethods.stream()
					.map(PaymentMethod::toString)
					.collect(Collectors.joining(SPLIT_CHAR));
		}
		else
		{
			return null;
		}
	}

	@Override
	public @Nullable List<PaymentMethod> convertToEntityAttribute(@Nullable String string)
	{
		if(string != null)
		{
			return Arrays.stream(string.split(SPLIT_CHAR))
					.map(PaymentMethod::valueOf)
					.collect(Collectors.toList());
		}
		else
		{
			return null;
		}
	}
}
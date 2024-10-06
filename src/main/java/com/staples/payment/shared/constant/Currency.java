package com.staples.payment.shared.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Currency
{
	USD("840"), CAD("124");

	// --------------------

	private final String currencyCode;
}

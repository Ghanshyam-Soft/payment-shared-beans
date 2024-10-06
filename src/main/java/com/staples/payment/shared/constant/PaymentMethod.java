package com.staples.payment.shared.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PaymentMethod
{
	VI("Visa"),
	MC("Mastercard"),
	AM("Amex"),
	DI("Discover"),
	BT("BlueTarp"),
	AR("Account Receivables"),
	GC("Staples Gift Card"),
	PP("PayPal"),
	ST("Staples"),
	SO("Staples Open"),
	AP("Apple Pay");

	// --------------------

	private final String prettyName;
}

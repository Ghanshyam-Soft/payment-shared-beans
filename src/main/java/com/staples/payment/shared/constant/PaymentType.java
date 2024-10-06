package com.staples.payment.shared.constant;

import static com.staples.payment.shared.constant.AuthRequestType.Authorization;
import static com.staples.payment.shared.constant.AuthRequestType.AuthorizationComplete;
import static com.staples.payment.shared.constant.AuthRequestType.BalanceInquiry;
import static com.staples.payment.shared.constant.AuthRequestType.Lookup;
import static com.staples.payment.shared.constant.AuthRequestType.PartialReversal;
import static com.staples.payment.shared.constant.AuthRequestType.PreAuthorization;
import static com.staples.payment.shared.constant.AuthRequestType.ReAuthorization;
import static com.staples.payment.shared.constant.AuthRequestType.Refund;
import static com.staples.payment.shared.constant.AuthRequestType.Reversal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PaymentType
{
	Credit(List.of(PreAuthorization, Authorization, ReAuthorization, Reversal, PartialReversal, Refund)),

	@JsonProperty("Gift Card")
	GiftCard(List.of(BalanceInquiry, Authorization, AuthorizationComplete, Reversal)),

	Prepaid(List.of(BalanceInquiry, Authorization, ReAuthorization, Reversal, PartialReversal, Refund)),

	PayPal(List.of(Authorization, ReAuthorization, Reversal, Lookup)),

	ApplePay(List.of(PreAuthorization, Authorization, ReAuthorization, Reversal, PartialReversal, Refund, Lookup));

	// --------------------

	private final List<AuthRequestType> validAuthRequestTypes;
}

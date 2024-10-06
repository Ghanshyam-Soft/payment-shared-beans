package com.staples.payment.shared.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * This is the list of valid RequestTypes for AuthorizationMain.
 * 
 * SettlementMain has its own separate list. 
 */
public enum AuthRequestType
{
	@JsonProperty("Pre-Authorization")
	PreAuthorization,

	Authorization,

	@JsonProperty("Re-Authorization")
	ReAuthorization,

	Reversal,

	@JsonProperty("Partial Reversal")
	PartialReversal,

	Refund,

	@JsonProperty("Balance Inquiry")
	BalanceInquiry,

	@JsonProperty("AuthorizationComplete")
	AuthorizationComplete,

	Lookup;
}

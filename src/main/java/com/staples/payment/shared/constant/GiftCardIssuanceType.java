package com.staples.payment.shared.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * This is the list of valid IssuanceTypes for Gift Card issued from Staples. 
 */
public enum GiftCardIssuanceType
{
	@JsonProperty("M")
	Manual,

	@JsonProperty("V")
	Virtual;
}

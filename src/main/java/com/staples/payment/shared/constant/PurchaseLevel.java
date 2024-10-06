package com.staples.payment.shared.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PurchaseLevel
{
	@JsonProperty("1")
	ONE,

	@JsonProperty("2")
	TWO,

	@JsonProperty("3")
	THREE;
}

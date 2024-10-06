package com.staples.payment.shared.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AvsResponseCode
{
	B, C, Z, I, A, Y, N, P, R, G, S, U, T,

	// Cybersource changes //

	X, W,

	@JsonProperty("0")
	ZERO,

	@JsonProperty("2")
	TWO;
}

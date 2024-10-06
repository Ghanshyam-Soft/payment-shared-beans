package com.staples.payment.shared.constant;

public class SettledCode
{
	public static final String processed = "P";
	public static final String sent = "Y"; // this is used for processes where the response to a settlement is asynchronous
	public static final String notSettled = null;
	public static final String rejected = "D";
	public static final String error = "E";
	public static final String terminated = "Z"; // this is used for manual interventions

	// The above are the only valid values for settled_flag
	// TODO: Look into converting to an enum to guarantee that
}

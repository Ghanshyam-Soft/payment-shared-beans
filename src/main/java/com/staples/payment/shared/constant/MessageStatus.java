package com.staples.payment.shared.constant;

public enum MessageStatus
{
	// note one use of System_Issue is for when the bank tells us about the problem
	Successful, System_Issue, Timeout, Duplicate;
}

package com.staples.payment.shared.request;

import lombok.Value;

@Value
public class CacheRefresh
{
	boolean refreshBankResponseCache;
	boolean refreshMerchantCache;
}

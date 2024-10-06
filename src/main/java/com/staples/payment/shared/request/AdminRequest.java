package com.staples.payment.shared.request;

import lombok.Value;

@Value
public class AdminRequest // TODO: When we add l2 cache we want to get rid of this. Alternatively if we don't do l2 caching we want to get this functionality into a finished state
{
	CacheRefresh cacheRefresh;
}

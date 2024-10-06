package com.staples.payment.shared.cache;

import com.staples.payment.shared.constant.Bank;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class ResponseInfoCacheKey
{
	Bank bankId;
	String bankCode;
	String channel;
}
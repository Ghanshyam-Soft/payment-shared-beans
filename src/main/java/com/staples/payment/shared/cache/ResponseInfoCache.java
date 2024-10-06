package com.staples.payment.shared.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.staples.payment.shared.constant.Bank;
import com.staples.payment.shared.entity.respInfo.AvsResponseInfo;
import com.staples.payment.shared.entity.respInfo.CcinResponseInfo;
import com.staples.payment.shared.entity.respInfo.ReasonResponseInfo;
import com.staples.payment.shared.entity.respInfo.RespResponseInfo;
import com.staples.payment.shared.entity.respInfo.ResponseInfo;
import com.staples.payment.shared.exceptions.MissingRespInfoException;
import com.staples.payment.shared.repo.ResponseInfoRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseInfoCache // Authmain/SettlementMain/Batches will instantiate this themselves with a different list of bankIds
{
	private final Map<ResponseInfoCacheKey, AvsResponseInfo> avsResponseInfoMap;
	private final Map<ResponseInfoCacheKey, CcinResponseInfo> ccinResponseInfoMap;
	private final Map<ResponseInfoCacheKey, ReasonResponseInfo> reasonResponseInfoMap;
	private final Map<ResponseInfoCacheKey, RespResponseInfo> respResponseInfoMap;

	public ResponseInfoCache(ResponseInfoRepo responseInfoRepo, final List<Bank> bankIds)
	{
		log.info("fetching response info info at startup..");

		final List<ResponseInfo> responseInfo = responseInfoRepo.findByBankIdIn(bankIds);

		avsResponseInfoMap = new HashMap<>();
		ccinResponseInfoMap = new HashMap<>();
		reasonResponseInfoMap = new HashMap<>();
		respResponseInfoMap = new HashMap<>();
		for(ResponseInfo info : responseInfo)
		{
			final ResponseInfoCacheKey key = ResponseInfoCacheKey.builder()
					.bankId(info.getBankId())
					.bankCode(info.getBankCode())
					.channel(info.getChannel())
					.build();

			if(info instanceof AvsResponseInfo)
			{
				avsResponseInfoMap.put(key, (AvsResponseInfo) info);
			}
			else if(info instanceof CcinResponseInfo)
			{
				ccinResponseInfoMap.put(key, (CcinResponseInfo) info);
			}
			else if(info instanceof ReasonResponseInfo)
			{
				reasonResponseInfoMap.put(key, (ReasonResponseInfo) info);
			}
			else if(info instanceof RespResponseInfo)
			{
				respResponseInfoMap.put(key, (RespResponseInfo) info);
			}
		}
	}

	public AvsResponseInfo getAvsResponseInfoBy(Bank bank, String bankCode, String channel, String bankDescription)
	{
		final ResponseInfoCacheKey key = ResponseInfoCacheKey.builder()
				.bankId(bank)
				.bankCode(bankCode)
				.channel(channel)
				.build();

		final AvsResponseInfo responseInfo = avsResponseInfoMap.get(key);

		if(responseInfo != null)
		{
			return responseInfo;
		}
		else
		{
			throw new MissingRespInfoException("There is no AVS row in gpasrespinfo for the combination of bankId " + bank + " bankCode " + bankCode
					+ " and channel " + channel + ". The bank description of this code was: " + bankDescription);
		}
	}

	public CcinResponseInfo getCcinResponseInfoBy(Bank bank, String bankCode, String channel, String bankDescription)
	{
		final ResponseInfoCacheKey key = ResponseInfoCacheKey.builder()
				.bankId(bank)
				.bankCode(bankCode)
				.channel(channel)
				.build();

		final CcinResponseInfo responseInfo = ccinResponseInfoMap.get(key);

		if(responseInfo != null)
		{
			return responseInfo;
		}
		else
		{
			throw new MissingRespInfoException("There is no CCIN row in gpasrespinfo for the combination of bankId " + bank + " bankCode " + bankCode
					+ " and channel " + channel + ". The bank description of this code was: " + bankDescription);
		}
	}

	public ReasonResponseInfo getReasonResponseInfoBy(Bank bank, String bankCode, String channel, String bankDescription)
	{
		final ResponseInfoCacheKey key = ResponseInfoCacheKey.builder()
				.bankId(bank)
				.bankCode(bankCode)
				.channel(channel)
				.build();

		final ReasonResponseInfo responseInfo = reasonResponseInfoMap.get(key);

		if(responseInfo != null)
		{
			return responseInfo;
		}
		else
		{
			throw new MissingRespInfoException("There is no REAS row in gpasrespinfo for the combination of bankId " + bank + " bankCode " + bankCode
					+ " and channel " + channel + ". The bank description of this code was: " + bankDescription);
		}
	}

	public RespResponseInfo getRespResponseInfoBy(Bank bank, String bankCode, String channel, String bankDescription)
	{
		final ResponseInfoCacheKey key = ResponseInfoCacheKey.builder()
				.bankId(bank)
				.bankCode(bankCode)
				.channel(channel)
				.build();

		final RespResponseInfo responseInfo = respResponseInfoMap.get(key);

		if(responseInfo != null)
		{
			return responseInfo;
		}
		else
		{
			throw new MissingRespInfoException("There is no RESP row in gpasrespinfo for the combination of bankId " + bank + " bankCode " + bankCode
					+ " and channel " + channel + ". The bank description of this code was: " + bankDescription);
		}
	}
}

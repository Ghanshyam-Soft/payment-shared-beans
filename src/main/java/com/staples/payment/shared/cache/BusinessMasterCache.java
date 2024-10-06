package com.staples.payment.shared.cache;

import java.time.Instant;
import java.util.List;

import org.springframework.lang.NonNull;

import com.staples.payment.shared.constant.PaymentMethod;
import com.staples.payment.shared.entity.BusinessMaster;
import com.staples.payment.shared.entity.MerchantMaster;
import com.staples.payment.shared.repo.BusinessMasterRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BusinessMasterCache // each service that needs this will instantiate it manually
{
	private List<BusinessMaster> businessMasters;
	private final BusinessMasterRepo businessMasterRepo;
	// private final MerchantMasterRepo merchantMasterRepo;

	public BusinessMasterCache(BusinessMasterRepo businessMasterRepo)// , MerchantMasterRepo merchantMasterRepo)
	{
		super();
		this.businessMasterRepo = businessMasterRepo;
		// this.merchantMasterRepo = merchantMasterRepo;

		log.info("fetching business master and merchant master tables at startup..");
		populateCache();
	}

	public void populateCache()
	{
		log.info("Populate cache start : " + Instant.now());

		// var mms = merchantMasterRepo.findAll();
		// log.info("{}", mms);
		businessMasters = businessMasterRepo.findAll();// TODO: Note because merchantMasterList is eager this is a really inefficient way to get the MerchantMasters

		if(businessMasters == null || businessMasters.isEmpty())
		{
			throw new RuntimeException("businessMasters cache is null or empty");// TODO: Make custom exception
		}

		log.info("Populate cache end : " + Instant.now());
		log.info("Cache contents {}", businessMasters);
	}

	public BusinessMaster getBusinessMasterBy(String businessUnit, String division)
	{
		for(BusinessMaster businessMaster : businessMasters)
		{
			if(division.equals(businessMaster.getBusinessDivision()) && businessUnit.equals(businessMaster.getBusinessUnit()))
			{
				return businessMaster;
			}
		}

		throw new RuntimeException("No matching BusinessMaster for businessUnit " + businessUnit + ", division " + division);// TODO: Make custom exception
	}

	public @NonNull MerchantMaster getMerchantMasterBy(String businessUnit, String division, PaymentMethod paymentType)
	{
		long starttime = System.currentTimeMillis();

		for(BusinessMaster businessMaster : businessMasters)
		{
			if(division.equals(businessMaster.getBusinessDivision()) && businessUnit.equals(businessMaster.getBusinessUnit()))
			{
				if(businessMaster.getMerchantMasterList() != null && !businessMaster.getMerchantMasterList().isEmpty())
				{
					for(MerchantMaster merchantMaster : businessMaster.getMerchantMasterList())
					{
						if(paymentType == merchantMaster.getPaymentMethod())
						{
							log.info("Time Taken for fetching the Merchant Master is: {} miliseconds", (System.currentTimeMillis() - starttime));
							return merchantMaster;
						}
					}

					throw new RuntimeException("No matching MerchantMaster in matching BusinessMaster for businessUnit " + businessUnit + ", division " + division + ", paymentType " + paymentType);// TODO: Make custom exception
				}
				else
				{
					throw new RuntimeException(
							"MerchantMaster list for matching BusinessMaster is null or empty for businessUnit " + businessUnit + ", division " + division + ", paymentType " + paymentType);// TODO: Make custom exception
				}
			}
		}

		throw new RuntimeException("No matching BusinessMaster for businessUnit " + businessUnit + ", division " + division + ", paymentType " + paymentType);// TODO: Make custom exception
	}
}

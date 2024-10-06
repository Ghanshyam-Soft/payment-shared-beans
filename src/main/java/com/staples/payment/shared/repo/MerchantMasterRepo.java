package com.staples.payment.shared.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staples.payment.shared.entity.MerchantMaster;

public interface MerchantMasterRepo extends JpaRepository<MerchantMaster, Integer>
{

}

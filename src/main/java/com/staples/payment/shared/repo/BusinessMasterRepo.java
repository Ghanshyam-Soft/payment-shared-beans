package com.staples.payment.shared.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staples.payment.shared.entity.BusinessMaster;

public interface BusinessMasterRepo extends JpaRepository<BusinessMaster, Integer>
{
	BusinessMaster findByBusinessUnitAndBusinessDivision(String businessUnit, String division);
}

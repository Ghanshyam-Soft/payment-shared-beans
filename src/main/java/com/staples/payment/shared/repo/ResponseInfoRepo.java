package com.staples.payment.shared.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staples.payment.shared.constant.Bank;
import com.staples.payment.shared.entity.respInfo.ResponseInfo;

public interface ResponseInfoRepo extends JpaRepository<ResponseInfo, Integer> // TODO: Once switch to l2 caching should split this into a repo for each type of ResponseInfo
{
	List<ResponseInfo> findByBankIdIn(List<Bank> bankIds);
}

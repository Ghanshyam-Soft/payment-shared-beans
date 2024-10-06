package com.staples.payment.shared.repo.bank;

import java.util.List;

import com.staples.payment.shared.entity.braintree.BraintreeLongterm;
import com.staples.payment.shared.entity.braintree.primarykey.BraintreeLongtermId;
import com.staples.payment.shared.repo.base.InsertUpdateRepository;

public interface BraintreeLongtermRepo extends InsertUpdateRepository<BraintreeLongterm, BraintreeLongtermId>
{
	List<BraintreeLongterm> findBySaleOrderNum(String orderNum);
}

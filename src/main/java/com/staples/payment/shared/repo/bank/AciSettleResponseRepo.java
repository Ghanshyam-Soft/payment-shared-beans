package com.staples.payment.shared.repo.bank;

import java.util.List;

import com.staples.payment.shared.entity.aci.AciSettlementResponse;
import com.staples.payment.shared.entity.settle.primarykey.GuidAndPaymentSequence;
import com.staples.payment.shared.repo.base.InsertUpdateRepository;

public interface AciSettleResponseRepo extends InsertUpdateRepository<AciSettlementResponse, GuidAndPaymentSequence>
{
	List<AciSettlementResponse> findByAuthKey(String authKey);
}

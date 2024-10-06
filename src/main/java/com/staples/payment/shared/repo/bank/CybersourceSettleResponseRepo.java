package com.staples.payment.shared.repo.bank;

import java.math.BigDecimal;

import com.staples.payment.shared.entity.cybersource.CybersourceSettlementResponse;
import com.staples.payment.shared.entity.settle.primarykey.GuidAndPaymentSequence;
import com.staples.payment.shared.repo.base.InsertUpdateRepository;

public interface CybersourceSettleResponseRepo extends InsertUpdateRepository<CybersourceSettlementResponse, GuidAndPaymentSequence>
{
	CybersourceSettlementResponse findByInvoiceNumberAndTokenIdAndAmount(String invoiceNumber, String tokenId, BigDecimal amount);
}

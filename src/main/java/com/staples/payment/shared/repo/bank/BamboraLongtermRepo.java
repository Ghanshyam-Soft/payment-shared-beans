package com.staples.payment.shared.repo.bank;

import java.util.Optional;

import com.staples.payment.shared.entity.bambora.BamboraLongterm;
import com.staples.payment.shared.entity.bambora.primarykey.BamboraLongtermId;
import com.staples.payment.shared.repo.base.InsertUpdateRepository;

public interface BamboraLongtermRepo extends InsertUpdateRepository<BamboraLongterm, BamboraLongtermId>
{
	Optional<BamboraLongterm> findByInvoiceNumAndTransactionStatus(String invoiceNumber, String status);
}

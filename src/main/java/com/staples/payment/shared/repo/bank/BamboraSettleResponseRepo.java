package com.staples.payment.shared.repo.bank;

import com.staples.payment.shared.entity.bambora.BamboraSettleResponse;
import com.staples.payment.shared.entity.bambora.primarykey.BamboraSettleResponseId;
import com.staples.payment.shared.repo.base.InsertUpdateRepository;

public interface BamboraSettleResponseRepo extends InsertUpdateRepository<BamboraSettleResponse, BamboraSettleResponseId>
{
}

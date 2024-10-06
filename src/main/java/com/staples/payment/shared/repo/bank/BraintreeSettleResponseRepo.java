package com.staples.payment.shared.repo.bank;

import com.staples.payment.shared.entity.braintree.BraintreeSettleResponse;
import com.staples.payment.shared.entity.braintree.primarykey.BraintreeSettleResponseId;
import com.staples.payment.shared.repo.base.InsertUpdateRepository;

public interface BraintreeSettleResponseRepo extends InsertUpdateRepository<BraintreeSettleResponse, BraintreeSettleResponseId>
{
}

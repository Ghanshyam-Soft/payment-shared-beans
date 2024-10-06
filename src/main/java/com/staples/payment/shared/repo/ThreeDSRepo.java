package com.staples.payment.shared.repo;

import com.staples.payment.shared.entity.ThreeDSResponse;
import com.staples.payment.shared.repo.base.InsertUpdateRepository;

public interface ThreeDSRepo extends InsertUpdateRepository<ThreeDSResponse, String>
{
}

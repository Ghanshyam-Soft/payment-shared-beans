package com.staples.payment.shared.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.staples.payment.shared.entity.AuthLog;
import com.staples.payment.shared.repo.base.InsertUpdateRepository;

public interface AuthLogRepo extends InsertUpdateRepository<AuthLog, String>
{
	List<AuthLog> findByChildKeyOrOriginatingKey(String childKey, String origKey);

	@Query(
			value = "SELECT count(*) FROM naddb_own.auth_log where payment_type = 'Credit' AND request_type = 'PreAuthorization' "
					+ "AND (message_status = 'Successful' OR message_status = 'Duplicate') AND parent_key = ?1 "
					+ "AND payment_token = ?2 AND request_receive_datetime > CURRENT_DATE - ( ?3 )\\:\\:interval",
			nativeQuery = true)
	Integer findPreAuthRetryCount(String parentKey, String paymentToken, String duration);
}

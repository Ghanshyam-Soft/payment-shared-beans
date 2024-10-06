/**
 * 
 */
package com.staples.payment.shared.repo;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.staples.payment.shared.constant.Bank;
import com.staples.payment.shared.constant.Country;
import com.staples.payment.shared.constant.PaymentMethod;
import com.staples.payment.shared.entity.settle.Header;
import com.staples.payment.shared.repo.base.InsertUpdateRepository;

/**
 * @author nanam001
 */
public interface HeaderRepo extends InsertUpdateRepository<Header, String>
{
	Header findByTransactionNumberAndCountryAndInvoiceNumber(String transactionNumber, Country country, String invoiceNo);

	Header findByTransactionNumberAndCountryAndOrderNumberAndOrderLinkAndShipmentNumberAndInvoiceNumber(String transactionNumber, Country country, String orderNo, Integer linkNo, String shipNo,
			String invoiceNo);

	boolean existsByTransactionNumberAndCountryAndInvoiceNumber(String transactionNumber, Country country, String invoiceNo);

	boolean existsByTransactionNumberAndCountryAndOrderNumberAndOrderLinkAndShipmentNumberAndInvoiceNumber(String transactionNumber, Country country, String orderNo, Integer linkNo, String shipNo,
			String invoiceNo);

	@Query(value = "select DISTINCT h from Header h "
			+ " INNER JOIN Tender t ON t.settleGuid=h.settleGuid"
			+ " where t.settledFlag in('D','P','E') and t.bank = ?1 and t.businessUnit = ?2  and t.division in ( ?3 ) and t.paymentMethod in ( ?4 ) and ((t.batchDatetime >= ?5) and (t.batchDatetime < ?6))  ",
			nativeQuery = false)
	List<Header> getSettlementData(Bank bank, String businessUnit, List<String> divisions, List<PaymentMethod> paymentMethod, Instant startOfDay, Instant startOfNextDay);
}

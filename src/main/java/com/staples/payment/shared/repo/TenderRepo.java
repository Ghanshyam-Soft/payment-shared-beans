package com.staples.payment.shared.repo;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.staples.payment.shared.constant.Bank;
import com.staples.payment.shared.constant.PaymentMethod;
import com.staples.payment.shared.dto.DeclinedSettlement;
import com.staples.payment.shared.dto.SettlementData;
import com.staples.payment.shared.entity.settle.Tender;
import com.staples.payment.shared.entity.settle.primarykey.GuidAndPaymentSequence;
import com.staples.payment.shared.repo.base.InsertUpdateRepository;

public interface TenderRepo extends InsertUpdateRepository<Tender, GuidAndPaymentSequence>
{

	List<Tender> findBySettledFlagAndBankAndTransactionDateTimeLessThan(String settledFlag, Bank bank, LocalDateTime transactionDateTime);

	// Testing
	List<Tender> findBySettledFlagAndBankAndTransactionDateTimeGreaterThan(String settledFlag, Bank bank, LocalDateTime transactionDateTime);

	List<Tender> findBySettledFlagAndBankAndTransactionDateTimeLessThanAndChargedAmountGreaterThanEqual(String settledFlag, Bank bank, LocalDateTime transactionDateTime, BigDecimal limit);

	List<Tender> findBySettledFlagAndBankAndTransactionDateTimeLessThanAndChargedAmountLessThan(String settledFlag, Bank bank, LocalDateTime transactionDateTime, BigDecimal limit);

	@Query(value = "select new com.staples.payment.shared.dto.SettlementData(cast(transactionDateTime as LocalDate),accountingLocation,"
			+ "sum(COALESCE(chargedAmount,0)) as rowTotal, "
			+ "sum(case when chargedAmount>=0 and settledFlag='P' then chargedAmount else 0 end) as approvedSaleAmount, "
			+ "sum(case when chargedAmount<0 and settledFlag='P' then chargedAmount else 0 end) as approvedRefundAmount, "
			+ "sum(case when chargedAmount>=0 and settledFlag in('D','E') then chargedAmount else 0 end) as declinedSaleAmount,"
			+ "sum(case when chargedAmount<0 and settledFlag in('D','E') then chargedAmount else 0 end) as declinedRefundAmount )"
			+ "from Tender "
			+ "where businessUnit = ?1  and division in ( ?2 ) and paymentMethod in ( ?3 )  and bank = ?4  and ((batchDatetime >= ?5) and (batchDatetime < ?6)) and settledFlag in('P','E','D')"
			+ "group by (cast(transactionDateTime as LocalDate),accountingLocation) order by cast(transactionDateTime as LocalDate) ",
			nativeQuery = false)
	List<SettlementData> getCompletedSettlementData(String businessUnits, List<String> divisions, List<PaymentMethod> paymentMethods, Bank bank, Instant startOfDay,
			Instant startOfNextDay);

	// TODO: Once I finished fixing the braintree response code problems, the field this is looking for the response code will need to change
	@Query(value = "select new com.staples.payment.shared.dto.DeclinedSettlement(h.orderNumber,h.invoiceNumber,cast(t.batchDatetime as LocalDate),cast(t.transactionDateTime as LocalDate),t.chargedAmount,t.settleGuid,b.processorResponseCode,b.processorResponseText) "
			+ "from Tender t  "
			+ "INNER JOIN  Header h ON t.settleGuid=h.settleGuid "
			+ "LEFT JOIN BraintreeSettleResponse b  ON t.settleGuid=b.settleGuid AND t.paymentSequence=b.paymentSequence "
			+ "where t.settledFlag in('D','E') and t.businessUnit = ?1  and t.division in ( ?2 ) and t.paymentMethod in ( ?3 )  and t.bank = 'BRAINTREE' and ((t.batchDatetime >= ?4) and (t.batchDatetime < ?5)) "
			+ "order by cast(t.transactionDateTime as LocalDate) ",
			nativeQuery = false)
	List<DeclinedSettlement> getDeclinedSettlementDataBraintree(String businessUnits, List<String> divisions, List<PaymentMethod> paymentMethods, Instant startOfDay,
			Instant startOfNextDay);

	@Query(value = "select new com.staples.payment.shared.dto.DeclinedSettlement(h.orderNumber,h.invoiceNumber,cast(t.batchDatetime as LocalDate),cast(t.transactionDateTime as LocalDate),t.chargedAmount,t.settleGuid,b.resultCode,b.resultDescription) "
			+ "from Tender t  "
			+ "INNER JOIN  Header h ON t.settleGuid=h.settleGuid "
			+ "LEFT JOIN AciSettlementResponse b  ON t.settleGuid=b.settleGuid AND t.paymentSequence=b.paymentSequence "
			+ "where t.settledFlag in('D','E') and t.businessUnit = ?1  and t.division in ( ?2 ) and t.paymentMethod in ( ?3 )  and t.bank = 'ACI'  and ((t.batchDatetime >= ?4) and (t.batchDatetime < ?5))  "
			+ "order by cast(t.transactionDateTime as LocalDate) ",
			nativeQuery = false)
	List<DeclinedSettlement> getDeclinedSettlementDataACI(String businessUnits, List<String> divisions, List<PaymentMethod> paymentMethods, Instant startOfDay,
			Instant startOfNextDay);

	@Query(value = "select new com.staples.payment.shared.dto.DeclinedSettlement(h.orderNumber,h.invoiceNumber,cast(t.batchDatetime as LocalDate),cast(t.transactionDateTime as LocalDate),t.chargedAmount,t.settleGuid,COALESCE(b.messageId, cast(b.errorCode as text)),b.validationError) "
			+ "from Tender t  "
			+ "INNER JOIN  Header h ON t.settleGuid=h.settleGuid "
			+ "LEFT JOIN BamboraSettleResponse b  ON t.settleGuid=b.settleGuid AND t.paymentSequence=b.paymentSequence "
			+ "where t.settledFlag in('D','E') and t.businessUnit = ?1  and t.division in ( ?2 ) and t.paymentMethod in ( ?3 )  and t.bank = 'BAMBORA'  and ((t.batchDatetime >= ?4) and (t.batchDatetime < ?5))  "
			+ "order by cast(t.transactionDateTime as LocalDate) ",
			nativeQuery = false)
	List<DeclinedSettlement> getDeclinedSettlementDataBambora(String businessUnits, List<String> divisions, List<PaymentMethod> paymentMethods, Instant startOfDay,
			Instant startOfNextDay);

	// Testing
	List<Tender> findBySettledFlagAndBankAndTransactionDateTimeGreaterThanAndChargedAmountGreaterThanEqual(String notsettled, Bank cyb, LocalDateTime datetime, BigDecimal zero);

	// Testing
	List<Tender> findBySettledFlagAndBankAndTransactionDateTimeGreaterThanAndChargedAmountLessThan(String notsettled, Bank cyb, LocalDateTime datetime, BigDecimal zero);
}

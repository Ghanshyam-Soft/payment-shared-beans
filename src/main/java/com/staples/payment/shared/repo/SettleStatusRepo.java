package com.staples.payment.shared.repo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.staples.payment.shared.entity.settle.Tender;

public interface SettleStatusRepo extends JpaRepository<Tender, String>
{

	@Query(value = "select tndr.* from naddb_own.settle_header hedr, "
			+ " naddb_own.settle_tender tndr "
			+ " where hedr.settle_guid=tndr.settle_guid "
			+ " and trans_num= ?1 "
			+ " and hedr.country= ?2 "
			+ " and business_unit= ?3 "
			+ " and division = ?4 "
			+ " and payment_token= ?5 "
			+ " and transaction_amt = ?6 "
			+ " and (order_num= ?7 or order_num is null) "
			+ " and (order_link_num= ?8 or order_link_num is null) "
			+ " and (shipment_num= ?9 or shipment_num is null) "
			+ " and (invoice_num= ?10 or invoice_num is null) ",
			nativeQuery = true)
	List<Tender> findSettlementStatusByKey(String transNumber,
			String country,
			String businessUnit,
			String division,
			String paymentToken,
			BigDecimal transactionAmt,
			String orderNo,
			Integer linkNo,
			String shipNo,
			String invoiceNo);

	@Query(value = "select tndr.* "
			+ " from naddb_own.settle_header hedr, "
			+ " naddb_own.settle_tender tndr "
			+ " where hedr.settle_guid=tndr.settle_guid "
			+ " and trans_num= ?1 "
			+ " and hedr.country= ?2 "
			+ " and (invoice_num= ?3 or invoice_num is null)",
			nativeQuery = true)
	List<Tender> validateTxnByInvoice(String transNumber, String country, String invoiceNo);

	@Query(value = "select tndr.* "
			+ " from naddb_own.settle_header hedr, "
			+ " naddb_own.settle_tender tndr "
			+ " where hedr.settle_guid=tndr.settle_guid "
			+ " and trans_num= ?1 "
			+ " and hedr.country= ?2 "
			+ " and (order_num= ?3 or order_num is null) "
			+ " and (order_link_num= ?4 or order_link_num is null) "
			+ " and (shipment_num= ?5 or shipment_num is null) "
			+ " and (invoice_num= ?6 or invoice_num is null)",
			nativeQuery = true)
	List<Tender> validateTxnByOrdShipAndInvoice(String transNumber,
			String country,
			String orderNo,
			Integer linkNo,
			String shipNo,
			String invoiceNo);
}

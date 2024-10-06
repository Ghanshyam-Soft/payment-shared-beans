package com.staples.payment.shared.entity.bambora;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.staples.payment.shared.entity.bambora.primarykey.BamboraLongtermId;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bambora_settlement_longterm")
@IdClass(BamboraLongtermId.class)
public class BamboraLongterm
{
	@Id
	String saleOrderNum; // for sales this is order_num for refunds this is original_order_number from header table

	@Id
	String invoiceNum; // invoice_num from header table

	@Id
	Integer settleSequence;// sequence for multiple partial refunds

	String refundOrderNum; // for refunds this is order_num from header table. Null for sales.

	String customerId;
	BigDecimal transactionAmount;

	String paymentId; // The transaction id of this transaction
	String originalPaymentId; // For a refund this will be the transaction id of the settlement. For a settlement this will be null.

	String transactionType;
	String transactionStatus;

	LocalDateTime createdAt;
}
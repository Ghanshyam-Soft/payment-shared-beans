package com.staples.payment.shared.entity.cybersource;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.http.HttpStatus;

import com.staples.payment.shared.entity.settle.primarykey.GuidAndPaymentSequence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cybersource_settlement_response")
@IdClass(GuidAndPaymentSequence.class)
public class CybersourceSettlementResponse
{
	@Id
	String settleGuid;

	@Id
	Integer paymentSequence;

	@Enumerated(EnumType.STRING)
	HttpStatus responseStatus;

	String orderNumber;
	String invoiceNumber;
	String authKey;

	String clientReferenceCode;
	String settleReferenceId;
	BigDecimal amount;
	String currency;
	String tokenId;

	String approvalCode;
	String networkTransactionId;
	String transactionId;
	String responseCode;
	String reconcilationId;
	String status;
	Instant requestSentTime;
	Instant responseReceivedTime;
	String reasonDescription;
	String messageDetails;
	String errorDetails;

}

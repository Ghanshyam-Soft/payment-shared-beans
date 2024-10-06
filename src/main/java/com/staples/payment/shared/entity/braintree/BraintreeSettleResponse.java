package com.staples.payment.shared.entity.braintree;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.staples.payment.shared.entity.braintree.primarykey.BraintreeSettleResponseId;

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
@Table(name = "braintree_settlement_response")
@IdClass(BraintreeSettleResponseId.class)
public class BraintreeSettleResponse // TODO: do we need all of these fields for settlement?
{
	@Id
	@Column(name = "settle_guid")
	String settleGuid;

	@Id
	@Column(name = "payment_sequence")
	Integer paymentSequence;

	@Id
	Integer responseSequence;

	String transactionId;
	String transactionType;
	String transactionStatus;
	String paymentInstrumentType;
	String captureId;
	String payerId;
	String payerEmail;
	String payerPhone;
	String payerStatus;
	String sellerProtectionStatus;

	String paymentMethodNonce;
	String processorResponseCode;
	String processorResponseText;
	String additionalProcessorResponse;
	String cvvResponseCode;
	String graphQlId;
	String networkResponseCode;
	String networkResponseText;
	String networkTransactionId;
	String retrievalReferenceNumber;

	@Column(columnDefinition = "TEXT")
	String transactionStatusHistory;

	@Column(columnDefinition = "TEXT")
	String validationError;

	@Column(columnDefinition = "TEXT")
	String deepValidationError;

	String gatewayRejectionReason;
	String debugId;
	String customFields;
	Instant authorizationExpiresAt;
	Instant trxnCreatedAt;
	Instant trxnUpdatedAt;
	Instant gpasRequestSentAt;
	Instant gpasResponseReceivedAt;
	String message;

	@Column(columnDefinition = "TEXT")
	String dispute;

	String deviceData;
}

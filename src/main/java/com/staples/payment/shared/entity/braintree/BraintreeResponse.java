package com.staples.payment.shared.entity.braintree;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "braintree_response")
public class BraintreeResponse
{
	@Id
	String gpasKey;

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
	String message; // TODO: What is the max length for this column?
	Instant gpasRequestSentAt;
	Instant gpasResponseReceivedAt;

	@Column(columnDefinition = "TEXT")
	String dispute;

	String deviceData;
}

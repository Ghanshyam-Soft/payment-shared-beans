package com.staples.payment.shared.entity.aci;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.http.HttpStatus;

import com.staples.payment.shared.aci.constants.ThreeDSResultConstants;
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
@Table(name = "aci_settlement_response")
@IdClass(GuidAndPaymentSequence.class)
public class AciSettlementResponse
{
	@Id
	String settleGuid;

	@Id
	Integer paymentSequence;

	// Wrapper fields
	URI targetUri;

	@Enumerated(EnumType.STRING)
	HttpStatus httpStatus;

	Instant timeSent;
	Instant timeReceived;

	// fields not from response
	String orderNumber;
	String invoiceNumber;
	String authKey;

	// main body fields
	String aciTransactionId;
	Instant responseTimestamp;
	BigDecimal amount;
	String currency;
	String tokenId;

	// result fields
	String resultCode;
	String resultDescription;
	String authorizationId;

	// result details fields excluding custom details
	String retrievalReferenceNumber;
	String acquirerResponse;
	String commercialCardIndicator;

	@Column(name = "level3_interchange_eligible")
	String level3InterchangeEligible;

	// ThreeDSecureResult fields
	@Column(name = "threed_secure_result")
	@Enumerated(EnumType.STRING)
	ThreeDSResultConstants threeDSecureResult;

	// custom details fields
	String resultCustomDetails;
	String customParameters;
}

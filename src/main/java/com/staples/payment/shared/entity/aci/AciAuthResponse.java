package com.staples.payment.shared.entity.aci;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.http.HttpStatus;

import com.staples.payment.shared.aci.constants.ThreeDSResultConstants;
import com.staples.payment.shared.aci.constants.pos.EntryMode;

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
@Table(name = "aci_auth_response")
public class AciAuthResponse
{
	@Id
	String gpasKey;

	// Wrapper fields
	URI targetUri;

	@Enumerated(EnumType.STRING)
	HttpStatus httpStatus;

	Instant timeSent;
	Instant timeReceived;

	// ACI Request fields
	@Enumerated(EnumType.STRING)
	EntryMode entryMode;

	// main body fields
	String aciTransactionId;
	Instant responseTimestamp;
	BigDecimal amount;
	String currency;
	String tokenId;

	// result fields
	String resultCode;
	String resultDescription;
	String avsResponse;
	String cvvResponse;
	String authorizationId;

	// result details fields except for custom details
	String retrievalReferenceNumber;
	String acquirerResponse;
	String acquirerAvsResponse;
	String commercialCardIndicator;

	@Column(name = "level3_interchange_eligible")
	String level3InterchangeEligible;

	String retailReturnHash;

	@Column(name = "pan_k_hash")
	String panKHash;

	// MIT fields
	String bankTransactionId;
	String banknetDate;
	String banknetRefNr;

	// custom details fields
	String resultCustomDetails;
	String customParameters;

	// ThreeDSecureResult and CardResponse fields
	@Column(name = "threed_secure_result")
	@Enumerated(EnumType.STRING)
	ThreeDSResultConstants threeDSecureResult;
	String cavvResultCode;

	// fields unique to balance request
	BigDecimal availableBalance;
}
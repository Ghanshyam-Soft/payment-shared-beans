package com.staples.payment.shared.entity.bambora;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name = "bambora_response")
public class BamboraResp
{
	@Id
	String gpasKey;

	String paymentId;
	String transactionType;
	// String cardType;
	String transactionStatus;
	String paymentMethod;
	String messageId;
	String authCode;
	BigDecimal riskscore;
	BigDecimal amount;
	String cvdResult;
	String avsResult;
	String cvvResponseCode;

	String addressMatch;
	String postalResult;
	String merchantId;

	@Column(columnDefinition = "TEXT")
	String validationError;

	@Column(columnDefinition = "TEXT")
	String deepValidationError;

	LocalDateTime trxnCreatedAt; // TODO: Change the column in the table to be timestamp without timezone
	// Instant trxnUpdatedAt; //TODO: We are not using this, should it be removed? Should the type be changed?

	Integer bankRespCode;
}

package com.staples.payment.shared.entity.bambora;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.staples.payment.shared.entity.bambora.primarykey.BamboraSettleResponseId;

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
@Table(name = "bambora_settlement_response")
@IdClass(BamboraSettleResponseId.class)
public class BamboraSettleResponse
{
	@Id
	@Column(name = "settle_guid")
	String settleGuid;

	@Id
	@Column(name = "payment_sequence")
	Integer paymentSequence;

	@Id
	Integer responseSequence;

	String paymentId;
	String transactionType;
	String transactionStatus;
	String paymentMethod;
	String messageId;
	String authCode;
	BigDecimal riskscore;
	BigDecimal amount;
	String merchantId;

	@Column(columnDefinition = "TEXT")
	String validationError;

	@Column(columnDefinition = "TEXT")
	String deepValidationError;

	LocalDateTime trxnCreatedAt;

	Integer errorCode;

	Integer category;

	Integer bankRespCode;
}

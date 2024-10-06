package com.staples.payment.shared.entity.bank;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.staples.payment.shared.constant.GpasRespCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "amex_redeem_response")
public class AmexRedeemResponse
{

	@Id
	String gpasKey;
	String messageId;
	String paymentToken;

	String accountId;
	String chargeId;
	BigDecimal amount;
	String amountCurrencyCode;
	BigDecimal basketAmount;
	String basketAmountCurrencyCode;
	Long pointsNeeded;
	BigDecimal conversionRate;
	BigDecimal currentBalance;
	BigDecimal amountBalance;
	String tierCode;
	Integer status;
	Integer retryCount;
	Instant reqReceiveDatetime;
	Instant reqSentTime;
	Instant resRcvdTime;
	Integer errorCode;
	// --------------------
	GpasRespCode responseCode;
	String reasonCode;
	String vendorResponseCode;
	String vendorReasonCode;
	String descriptionText;
	// ------------------------
	String errorDescription;

}
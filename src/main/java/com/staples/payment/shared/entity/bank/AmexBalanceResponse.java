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
@Table(name = "amex_balance_response")
public class AmexBalanceResponse
{
	@Id
	String messageId;

	String requestorOrderId;

	String paymentToken;

	String accountId;

	BigDecimal amount;
	String amountCurrencyCode;

	Long pointsNeeded;
	BigDecimal conversionRate;
	BigDecimal currentBalance;
	BigDecimal amountBalance;
	String tierCode;
	int status;
	Integer retryCount;
	Instant reqReceiveDatetime;
	Instant reqSentTime;
	Instant resRcvdTime;
	GpasRespCode responseCode;
	String reasonCode;
	String vendorResponseCode;
	String vendorReasonCode;
	String descriptionText;
	String errorDescription;
}
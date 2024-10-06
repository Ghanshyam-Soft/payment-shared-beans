package com.staples.payment.shared.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class DeclinedSettlement
{
	String orderNum;
	String invoiceNum;
	LocalDate settlementDate;
	LocalDate transactionDate;
	BigDecimal transactionAmount;

	String settleGuid;
	String declinedReasonCode;
	String declinedReasonText;
}
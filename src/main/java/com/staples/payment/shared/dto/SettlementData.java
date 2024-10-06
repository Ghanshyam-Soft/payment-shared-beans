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
public class SettlementData
{
	LocalDate transactionDate;
	Integer accountingLocation;

	BigDecimal rowTotal;

	BigDecimal approvedSaleAmount;
	BigDecimal approvedRefundAmount;
	BigDecimal declinedSaleAmount;
	BigDecimal declinedRefundAmount;
}
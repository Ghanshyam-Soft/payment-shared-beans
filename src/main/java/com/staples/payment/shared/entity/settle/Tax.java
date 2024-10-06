package com.staples.payment.shared.entity.settle;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.staples.payment.shared.constant.TaxType;
import com.staples.payment.shared.entity.settle.primarykey.TaxId;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "settle_tax")
@IdClass(TaxId.class)
public class Tax
{
	@Id
	@Column(name = "settle_guid")
	String settleGuid;

	@Id
	@Column(name = "item_line_no")
	Integer lineNumber;

	@Id
	Integer taxSequence;

	@Enumerated(EnumType.STRING)
	TaxType taxType;

	BigDecimal taxRate;
	BigDecimal taxAmount;
}

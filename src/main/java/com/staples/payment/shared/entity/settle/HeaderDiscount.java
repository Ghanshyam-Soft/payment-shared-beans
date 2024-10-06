package com.staples.payment.shared.entity.settle;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.staples.payment.shared.entity.settle.primarykey.HeaderDiscountId;

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
@Table(name = "settle_header_discount")
@IdClass(HeaderDiscountId.class)
public class HeaderDiscount
{
	@Id
	@Column(name = "settle_guid")
	String settleGuid;

	@Id
	@Column(name = "discount_number")
	Integer discountNumber;

	String posId;
	Instant createdDate;

	@Column(name = "type_desc")
	String type;

	@Column(name = "disc_sku")
	String sku;

	@Column(name = "disc_desc")
	String description;

	@Column(name = "disc_amt")
	BigDecimal amount; // Alternatively unitTotal

	@Column(name = "coupon_cd")
	String couponNumber;
}

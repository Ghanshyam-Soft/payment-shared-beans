package com.staples.payment.shared.entity.settle.primarykey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class HeaderDiscountId implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String settleGuid;
	private Integer discountNumber;
}
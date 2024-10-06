package com.staples.payment.shared.entity.braintree.primarykey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class BraintreeLongtermId implements Serializable
{
	private static final long serialVersionUID = 6551219389191529164L;

	private String saleOrderNum;
	private String invoiceNum;
	private Integer paymentSequence;
	private Integer settleSequence;
}

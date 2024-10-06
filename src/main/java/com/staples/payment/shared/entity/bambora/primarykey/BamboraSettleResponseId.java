package com.staples.payment.shared.entity.bambora.primarykey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class BamboraSettleResponseId implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String settleGuid;
	private Integer paymentSequence;
	private Integer responseSequence;
}
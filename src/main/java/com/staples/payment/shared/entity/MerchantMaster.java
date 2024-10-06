package com.staples.payment.shared.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import com.staples.payment.shared.constant.Bank;
import com.staples.payment.shared.constant.PaymentMethod;
import com.staples.payment.shared.constant.SecondaryBank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@javax.persistence.Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Immutable
@Table(name = "merchant_master")
public class MerchantMaster
{
	@Id
	int merchantBankUniqueId;

	@Enumerated(EnumType.STRING)
	PaymentMethod paymentMethod;

	@Enumerated(EnumType.STRING)
	Bank bankName;

	@Enumerated(EnumType.STRING)
	SecondaryBank secondaryBank;

	String bankProviderInfoBlock;
	String bankMerchantId;
	String bankTerminalId;

	// @Convert(converter = AttributeEncryptor.class) // TODO: Should this be here anymore? Check if will fail when given value
	String bankApiToken;

	String binPrefix;
	String merchantCategoryCode;

	// For Cybersource
	String merchantKeyId;
	String merchantSharedKey;

	// TODO: Consider adding a bankTimeout field

	boolean refundOnlineInBatch;

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "merchant_unique_id")
	BusinessMaster businessMaster;
}

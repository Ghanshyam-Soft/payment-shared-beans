package com.staples.payment.shared.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import com.staples.payment.shared.constant.Currency;

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
@Table(name = "business_master")
public class BusinessMaster
{
	@Id
	int merchantUniqueId;

	String businessUnit;
	String businessDivision;

	String countryCode;
	String businessDescription;

	@Enumerated(EnumType.STRING)
	Currency currency;

	Integer gpasStoreNo;
	boolean invoiceOnlyDuplicateCheck;

	String merchantName;
	String merchantCustServiceNo;
	String merchantTaxId;
	String merchantAddress;
	String merchantCity;
	String merchantState;
	String merchantZipCode;
	String merchantUrl;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "businessMaster")
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	List<MerchantMaster> merchantMasterList;
}

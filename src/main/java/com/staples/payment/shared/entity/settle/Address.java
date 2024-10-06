package com.staples.payment.shared.entity.settle;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.staples.payment.shared.entity.settle.primarykey.GuidAndPaymentSequence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "settle_address")
@IdClass(GuidAndPaymentSequence.class)
public class Address
{
	@Id
	@Column(name = "settle_guid")
	String settleGuid;

	@Id
	@Column(name = "payment_sequence")
	Integer paymentSequence;

	Instant createdDate;
	String posId;

	// TODO: Ensure the lengths I put into the columns are correct

	String orderedFirstName;
	String orderedLastName;
	String orderedByEmail; // Added for GC Refunds
	String clientRefKey;// TODO: remove this from this table since it is also in the Header

	String billToCompanyName;
	String billToFirstName;
	String billToLastName;
	String billToAddr1;
	String billToAddr2;
	String billToCity;
	String billToState;
	String billToPostalCode;
	String billToCountry;
	String billToPhone; // Added for GC Refunds
	String billToEmail;

	String shipToCompany;
	String shipToFirstName;
	String shipToLastName;
	String shipToAddress1;
	String shipToAddress2;
	String shipToCity;
	String shipToState;
	String shipToPostalCode;
	String shipToCountry;
	String shipToEmail;
}

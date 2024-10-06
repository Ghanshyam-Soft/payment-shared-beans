package com.staples.payment.shared.entity.settle;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.staples.payment.shared.constant.Country;
import com.staples.payment.shared.constant.InvoiceType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "settle_header")
public class Header
{
	@Id
	@Column(name = "settle_guid")
	String settleGuid;

	String posId;
	Instant createdDate;

	@Enumerated(EnumType.STRING)
	Country country;

	String parentGuid;

	@Column(name = "register_num")
	String registerNumber;

	@Column(name = "trans_num")
	String transactionNumber;

	@Column(name = "order_num")
	String orderNumber;

	String originalOrderNumber;

	@Column(name = "order_link_num")
	Integer orderLink;

	@Column(name = "shipment_num")
	String shipmentNumber;

	Integer currentShipment;
	Integer totalShipments;

	@Column(name = "final_shipment_ind")
	Boolean finalShipmentIndicator;

	@Column(name = "invoice_num")
	String invoiceNumber;

	@Column(name = "org_invoice_num")
	String originalInvoiceNumber;

	@Column(name = "customer_num")
	String customerNumber;

	@Column(name = "purch_order_num")
	String purchaseOrderNumber;

	@Column(name = "cust_po_release_num")
	String poReleaseNo;

	@Column(name = "bill_to_ref_num")
	String billToRef;

	@Column(name = "tax_exempt_num")
	String taxExemptNumber;

	String termsCode;

	@Column(name = "budget_center_num")
	String budgetCenter;

	BigDecimal transactionTotal;
	BigDecimal taxTotal;
	BigDecimal shippingCharge;
	BigDecimal netTotal;

	@Column(name = "creation_user_id")
	String creationUser;

	String clientRefKey; // Or CustomerRefInfo

	@Column(name = "discount_ind")
	Boolean discounted;

	@Column(name = "address_ind")
	Boolean hasAddressInfo;

	String orderHeaderKey;

	@Enumerated(EnumType.STRING)
	InvoiceType invoiceType;

	String shippingSku;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "header")
	List<Tender> tenders;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "settle_guid", referencedColumnName = "settle_guid")
	@NotFound(action = NotFoundAction.IGNORE)
	List<Item> items;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "settle_guid", referencedColumnName = "settle_guid")
	@NotFound(action = NotFoundAction.IGNORE)
	List<HeaderDiscount> discounts;
}

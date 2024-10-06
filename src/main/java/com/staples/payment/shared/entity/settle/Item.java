package com.staples.payment.shared.entity.settle;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.staples.payment.shared.constant.Category;
import com.staples.payment.shared.entity.settle.primarykey.ItemId;

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
@Table(name = "settle_item")
@IdClass(ItemId.class)
public class Item
{
	@Id
	@Column(name = "settle_guid")
	String settleGuid;

	@Id
	@Column(name = "item_line_no")
	Integer lineNumber;

	String posId;
	Instant createdDate;

	@Column(name = "disc_flg")
	Boolean hasLineItemDiscount;

	String itemType;
	String skuNo;
	String skuDesc;
	String unitOfMeasure;

	@Column(name = "item_qty")
	Integer quantity;

	BigDecimal unitPrice;
	BigDecimal unitTax;
	BigDecimal unitTotal;
	BigDecimal netTotal;

	@Column(name = "item_dept")
	String department;

	@Column(name = "item_upc")
	String upcCode;

	@Column(name = "item_budget_center")
	String lineItmBudgetCntr;

	// @Column(name = "item_tax_type") //TODO: Can we remove from table?
	// String taxType;
	//
	// @Column(name = "item_tax_rate")
	// BigDecimal taxRate;

	BigInteger primeLineNo;
	String orderInvoiceDetailKey;
	BigInteger subLineNo;
	Integer associatedItemLineNo;

	@Enumerated(EnumType.STRING)
	Category lineCategory;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "settle_guid", referencedColumnName = "settle_guid"),
			@JoinColumn(name = "item_line_no", referencedColumnName = "item_line_no")
	})
	@NotFound(action = NotFoundAction.IGNORE)
	List<LineDiscount> discounts;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "settle_guid", referencedColumnName = "settle_guid"),
			@JoinColumn(name = "item_line_no", referencedColumnName = "item_line_no")
	})
	@NotFound(action = NotFoundAction.IGNORE)
	List<Tax> taxes;

}

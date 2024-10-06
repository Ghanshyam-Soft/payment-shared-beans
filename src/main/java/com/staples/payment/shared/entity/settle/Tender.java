package com.staples.payment.shared.entity.settle;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.YearMonth;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.lang.Nullable;

import com.staples.payment.shared.constant.Bank;
import com.staples.payment.shared.constant.Channel;
import com.staples.payment.shared.constant.Country;
import com.staples.payment.shared.constant.GiftCardIssuanceType;
import com.staples.payment.shared.constant.GpasRespCode;
import com.staples.payment.shared.constant.PaymentMethod;
import com.staples.payment.shared.constant.PurchaseLevel;
import com.staples.payment.shared.constant.SecondarySettlementType;
import com.staples.payment.shared.entity.AuthLog;
import com.staples.payment.shared.entity.aci.AciSettlementResponse;
import com.staples.payment.shared.entity.bambora.BamboraSettleResponse;
import com.staples.payment.shared.entity.braintree.BraintreeSettleResponse;
import com.staples.payment.shared.entity.cybersource.CybersourceSettlementResponse;
import com.staples.payment.shared.entity.settle.primarykey.GuidAndPaymentSequence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "settle_tender")
@IdClass(GuidAndPaymentSequence.class)
public class Tender
{
	@Id
	@Column(name = "settle_guid")
	String settleGuid;

	@Id
	@Column(name = "payment_sequence")
	Integer paymentSequence;

	String posId;
	Instant createdDate;

	@Enumerated(EnumType.STRING)
	Country country;

	@Enumerated(EnumType.STRING)
	Bank bank;

	@Enumerated(EnumType.STRING)
	PaymentMethod paymentMethod;

	@Enumerated(EnumType.STRING)
	PurchaseLevel purchaseLevel;

	String businessUnit;
	String division;

	@Enumerated(EnumType.STRING)
	Channel channel;

	@Column(name = "settle_loc")
	Integer settlementLocation;

	@Column(name = "pick_loc")
	String pickLocation;

	@Column(name = "accounting_loc")
	Integer accountingLocation;

	@Column(name = "transaction_date")
	LocalDateTime transactionDateTime;

	String paymentToken;

	String giftCardNumber; // Gift card integration

	@Column(name = "auth_key")
	String authRequestGuid;

	@Column(name = "auth_code")
	String authorizationCode;

	@Column(name = "exp_date")
	YearMonth expirationDate;

	@Column(name = "transaction_amt")
	BigDecimal chargedAmount;

	BigDecimal settledAmount;

	String settledFlag;

	@Enumerated(EnumType.STRING)
	GpasRespCode responseCode;

	String batchName;
	Integer batchLine;

	@Column(name = "settled_date")
	Instant batchDatetime;

	@Column(name = "giftcard_issuance")
	@Enumerated(EnumType.STRING)
	GiftCardIssuanceType giftcardIssuanceType;

	String paymentKey;

	@Enumerated(EnumType.STRING)
	SecondarySettlementType secondarySettlementType;

	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "settle_guid", referencedColumnName = "settle_guid", insertable = false, updatable = false)
	Header header;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "settle_guid", referencedColumnName = "settle_guid"),
			@JoinColumn(name = "payment_sequence", referencedColumnName = "payment_sequence")
	})
	Address address;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "auth_key", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE) // Having an AuthRequestGuid that doesn't match a row in AuthLog is a valid scenario since the intention is to delete old authLogs.
	@Nullable
	AuthLog authLog;

	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "settle_guid", referencedColumnName = "settle_guid"),
			@JoinColumn(name = "payment_sequence", referencedColumnName = "payment_sequence")
	})
	@NotFound(action = NotFoundAction.IGNORE)
	@Nullable
	AciSettlementResponse aciSettlementResponse;

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "settle_guid", referencedColumnName = "settle_guid"),
			@JoinColumn(name = "payment_sequence", referencedColumnName = "payment_sequence")
	})
	@NotFound(action = NotFoundAction.IGNORE)
	@Nullable
	List<BamboraSettleResponse> bamboraSettleResponses;

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "settle_guid", referencedColumnName = "settle_guid"),
			@JoinColumn(name = "payment_sequence", referencedColumnName = "payment_sequence")
	})
	@NotFound(action = NotFoundAction.IGNORE)
	@Nullable
	List<BraintreeSettleResponse> braintreeSettleResponses;

	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "settle_guid", referencedColumnName = "settle_guid"),
			@JoinColumn(name = "payment_sequence", referencedColumnName = "payment_sequence")
	})
	@NotFound(action = NotFoundAction.IGNORE)
	@Nullable
	CybersourceSettlementResponse cybSettlementResponse;
}

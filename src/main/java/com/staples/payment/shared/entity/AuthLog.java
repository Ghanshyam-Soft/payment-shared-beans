package com.staples.payment.shared.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.YearMonth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.staples.payment.shared.constant.AuthRequestType;
import com.staples.payment.shared.constant.AvsResponseCode;
import com.staples.payment.shared.constant.Bank;
import com.staples.payment.shared.constant.CcinResponseCode;
import com.staples.payment.shared.constant.Country;
import com.staples.payment.shared.constant.GpasRespCode;
import com.staples.payment.shared.constant.MessageStatus;
import com.staples.payment.shared.constant.PaymentMethod;
import com.staples.payment.shared.constant.PaymentType;
import com.staples.payment.shared.constant.cof.BillingType;
import com.staples.payment.shared.constant.cof.COFSchedInd;
import com.staples.payment.shared.constant.cof.StoredCredInd;
import com.staples.payment.shared.constant.cof.TransactionInitiator;
import com.staples.payment.shared.entity.aci.AciAuthResponse;
import com.staples.payment.shared.entity.bambora.BamboraResp;
import com.staples.payment.shared.entity.bank.AmexRedeemResponse;
import com.staples.payment.shared.entity.braintree.BraintreeResponse;
import com.staples.payment.shared.entity.cybersource.CybersourceAuthResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auth_log")
public class AuthLog
{
	@Id
	String childKey;

	@Enumerated(EnumType.STRING)
	PaymentType paymentType;

	@Enumerated(EnumType.STRING)
	AuthRequestType requestType;

	@Enumerated(EnumType.STRING)
	Bank bank;

	Instant txnDatetime;
	LocalDateTime txnLocalDatetime;

	@Enumerated(EnumType.STRING)
	Country countryCode;

	String businessUnit;
	String businessDivision;
	String parentKey;
	String authReferenceKey;
	String originatingKey;
	String reversalKey;
	String gpasKey;
	String orderNo;
	String storeNo;
	String storeRegisterNo;
	String storeTransactionNo;
	String paymentToken;
	String giftCardNumber; // Gift card integration
	YearMonth cardExpiry;

	@Column(name = "card_type")
	@Enumerated(EnumType.STRING)
	PaymentMethod paymentMethod;

	String posDataCode;

	BigDecimal saleAmount;
	BigDecimal transactionAmount;
	// BigDecimal cashbackAmount; //TODO: Remove from table
	BigDecimal approvedAmount;
	BigDecimal remainingBalanceAmount;

	@Enumerated(EnumType.STRING)
	GpasRespCode gpasRespCode;

	String gpasReasCode;

	@Enumerated(EnumType.STRING)
	AvsResponseCode gpasAvsCode;

	@Enumerated(EnumType.STRING)
	CcinResponseCode gpasCvvCode;

	String gpasResponseDescription;
	String vendorRespCode;
	String vendorReasCode;
	String vendorAvsCode;
	String vendorCvvCode;
	String authCode;

	@Enumerated(EnumType.STRING)
	MessageStatus messageStatus;

	String vendorInfoBlock;
	Instant requestReceiveDatetime;
	Instant requestSentToBankDatetime;
	Instant respRcvdFromBankDatetime;
	Instant responseSentDatetime;

	Integer merchantUniqueId; // TODO: Remove merchantUniqueId when have removed db column (or at least the not null requirement)
	// Integer merchantBankUniqueId;

	@Enumerated(EnumType.STRING)
	@Column(name = "cof_tran_init_flg")
	TransactionInitiator cofTransactionInitiator;

	@Enumerated(EnumType.STRING)
	StoredCredInd cofStoredCredInd;

	@Enumerated(EnumType.STRING)
	COFSchedInd cofSchedInd;

	@Enumerated(EnumType.STRING)
	BillingType cofBillingType;

	String losInd;
	String ipAddress;
	String clientRefKey;
	boolean threeDsFlag;
	boolean isDefaultResponse;

	@ManyToOne(fetch = FetchType.LAZY) // TODO: Would this cascade if MerchantMaster data was deleted?
	@JoinColumn(name = "merchant_bank_unique_id")
	@NotFound(action = NotFoundAction.IGNORE) // this is additional to make sure there is no lazy initialization error with no session
	MerchantMaster merchantMaster;

	@ManyToOne
	@JoinColumn(name = "gpasKey", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE) // the gpasKey gets inserted before the call to the bank is even made so it is perfectly normal to have a gpasKey that doesn't match a row in response table
	AciAuthResponse aciResponse;

	@ManyToOne
	@JoinColumn(name = "gpasKey", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE) // the gpasKey gets inserted before the call to the bank is even made so it is perfectly normal to have a gpasKey that doesn't match a row in response table
	BamboraResp bamboraResponse;

	@ManyToOne
	@JoinColumn(name = "gpasKey", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE) // the gpasKey gets inserted before the call to the bank is even made so it is perfectly normal to have a gpasKey that doesn't match a row in the Response table
	BraintreeResponse braintreeResponse;

	@ManyToOne
	@JoinColumn(name = "gpasKey", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	AmexRedeemResponse amexRedeemResponse;

	@ManyToOne
	@JoinColumn(name = "gpasKey", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE) // the gpasKey gets inserted before the call to the bank is even made so it is perfectly normal to have a gpasKey that doesn't match a row in the Response table
	CybersourceAuthResponse cybersourceResponse;

	@ManyToOne
	@JoinColumn(name = "gpasKey", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE) // the gpasKey gets inserted before the call to the bank is even made so it is perfectly normal to have a gpasKey that doesn't match a row in the Response table
	ThreeDSResponse threeDSResponse;
}
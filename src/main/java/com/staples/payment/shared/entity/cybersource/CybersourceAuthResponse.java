package com.staples.payment.shared.entity.cybersource;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.http.HttpStatus;

import com.staples.payment.shared.constant.AuthRequestType;

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
@Table(name = "cybersource_auth_response")
public class CybersourceAuthResponse
{
	@Id
	String gpasKey;

	@Enumerated(EnumType.STRING)
	AuthRequestType transactionType;

	@Enumerated(EnumType.STRING)
	HttpStatus responseStatus;

	String clientReferenceCode;
	String authReferenceId;
	BigDecimal authorizedAmount;
	String currency;
	String approvalCode;
	String networkTransactionId;
	String transactionId;
	String responseCode;
	String avsCode;
	String avsCodeRaw;
	String status;
	Instant requestSentTime;
	Instant responseReceivedTime;

	String reasonDescription;
	String messageDetails;
	String errorDetails;

}

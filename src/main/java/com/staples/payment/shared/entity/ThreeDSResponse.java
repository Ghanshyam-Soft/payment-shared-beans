package com.staples.payment.shared.entity;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.staples.payment.shared.aci.constants.ThreeDSResultConstants;

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
@Table(name = "threeds_response")
public class ThreeDSResponse
{
	@Id
	String gpasKey;
	String merchantReferenceId;

	String errorComponent;

	@Column(name = "acs_trans_id")
	String acsTransID;

	String encodedCReq;
	String errorCode;
	String eci;

	@Column(name = "acs_url")
	String acsURL;

	String dsReferenceNumber;
	String acsReferenceNumber;
	String messageType;

	@Column(name = "ds_trans_id")
	String dsTransID;

	@Column(name = "acs_operator_id")
	String acsOperatorID;

	String errorDetail;
	String messageVersion;
	String authenticationType;
	String acsChallengeMandated;
	String transStatus;

	@Column(name = "three_ds_server_trans_id")
	String threeDSServerTransID;

	LocalDate createDate;

	Integer responseCode;
	String responseDescription;

	String authCharInd;

	@Enumerated(EnumType.STRING)
	ThreeDSResultConstants threedsAuthResponse;

	Instant requestSentToStpayDatetime;
	Instant respRcvdFromStpayDatetime;
}
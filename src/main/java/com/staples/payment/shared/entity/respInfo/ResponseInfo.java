package com.staples.payment.shared.entity.respInfo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.staples.payment.shared.constant.Bank;

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
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Immutable
@Table(name = "response_info")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "respType",
		discriminatorType = javax.persistence.DiscriminatorType.STRING)
public abstract class ResponseInfo
{
	@Id
	@Column(name = "resp_info_id")
	int id;

	@Enumerated(EnumType.STRING)
	Bank bankId;

	String bankCode;
	String bankDesc;
	String gpasCodeDesc;
	String channel;

	// The gpasCode is in each of the concrete child classes
}

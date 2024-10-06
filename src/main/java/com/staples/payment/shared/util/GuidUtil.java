package com.staples.payment.shared.util;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.staples.payment.shared.entity.BusinessMaster;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GuidUtil
{
	private DateTimeFormatter guidDatetimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSS").withZone(ZoneId.systemDefault());

	public String createGuid(BusinessMaster businessMaster)
	{
		final String division = businessMaster.getBusinessDivision();
		final String guidDateTime = guidDatetimeFormatter.format(Instant.now());
		final String randomNumber = generateRandomNumber();

		log.debug("Before Adding Zeros: Division {} DateTime : {} RandomNumber : {}", division, guidDateTime, randomNumber);

		final String guid = division + guidDateTime + randomNumber;
		final int neededAdditionalLength = 32 - guid.length();

		log.debug("{}   Zeros Added : {}", guid.length(), neededAdditionalLength);

		final String padding = neededAdditionalLength > 0 ? "0".repeat(neededAdditionalLength) : "";

		final String generatedGuid = (division + padding + guidDateTime + randomNumber).substring(0, 32);
		return generatedGuid;
	}

	private static String generateRandomNumber()
	{
		SecureRandom randomGenerator = new SecureRandom();
		int randomInt = randomGenerator.nextInt(1000000);
		return "" + randomInt;
	}
}
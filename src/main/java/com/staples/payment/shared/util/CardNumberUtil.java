package com.staples.payment.shared.util;

import java.util.zip.CRC32;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.staples.payment.shared.constant.TokenType;

@Service
public class CardNumberUtil
{
	private final String[] crcCharArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

	public @Nullable TokenType getTokenFormat(@Nullable String data)
	{
		if(data == null)
		{
			return null;
		}
		else if(isAliasNumber(data))
		{
			return TokenType.AN;
		}
		else if(isFPE(data))
		{
			return TokenType.FPE;
		}
		else if(isLosNumber(data))
		{
			return TokenType.LOS;
		}
		return null;
	}

	public boolean isCardLengthValid(String data)
	{
		boolean isValid = true;
		if(data == null || data.length() < 13 || data.length() > 19)
		{
			isValid = false;
		}
		return isValid;
	}

	/**
	 * Determines if the given string represents a Loss of Service number
	 */
	private boolean isLosNumber(String data)
	{
		if(data == null || data.length() == 0)
		{
			return false;
		}

		if(isFPE(data))
		{
			return false;
		}

		return Character.isLowerCase(data.charAt(0));
	}

	private boolean isFPE(String data)
	{
		if(data != null && data.startsWith("#"))
		{
			return true;
		}
		return false;
	}

	private boolean isAliasNumber(String data)
	{
		boolean status = false;
		if(data != null && data.length() == 16)
		{
			status = isValidANPrefix(data) && isValidANSuffix(data) && isCRCPassed(data);
		}
		return status;
	}

	/**
	 * The first letter needs to be a capital letter (A-Z)
	 */
	private boolean isValidANPrefix(String paramString)
	{
		int i = paramString.charAt(0);
		return (i >= 65) && (i <= 90); // TODO: Could use Character is capital letter method
	}

	/**
	 * The last 4 characters must be numbers (0-9)
	 */
	private boolean isValidANSuffix(String paramString)
	{
		int length = paramString.length();

		if(length < 4)
		{
			return false;
		}

		for(int j = length - 4; j < length; j++)
		{
			int k = paramString.charAt(j);
			if((k < 48) || (k > 57))
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Does it pass a Cyclic redundancy check?
	 * 
	 * This checks the integrity of the message (in other words is there any data corruption). This algorithm is not suitable for security purposes.
	 */
	private boolean isCRCPassed(String token)
	{
		if(token.length() != 16)
		{
			return false;
		}

		String myChar = String.valueOf(token.charAt(11));

		StringBuilder sb = new StringBuilder(token);
		String output = sb.deleteCharAt(11).toString();

		String myReturnedCRC = getCRCChkChar(output);

		if(myChar != null && myChar.equals(myReturnedCRC))
		{
			return true;
		}

		return false;
	}

	private String getCRCChkChar(String tokenValue)
	{
		int checkSum = -1;
		String returnCRC = null;
		CRC32 crc = new CRC32();
		long chkSum = -1L;

		crc.reset();
		crc.update(tokenValue.getBytes());
		chkSum = crc.getValue();

		checkSum = (int) chkSum % 62;

		if(Math.abs(checkSum) > 9)
		{
			/* The original code here was
						returnCRC = (new Character(aliasNumberCharArray[Math.abs(checkSum)])).toString();
			 	but the toString method of Character is literally just a call to String.valueOf passing in the char so there's no point to creating the Character object with a depreciated constructor.
			 	And its possible to just make the array an array of String characters
			 	*/
			returnCRC = crcCharArray[Math.abs(checkSum)];
		}
		else
		{
			returnCRC = String.valueOf(Math.abs(checkSum));
		}
		return returnCRC;
	}

	/*	public boolean isNumeric(String str)
		{
			if(str == null || str.length() == 0)
				return false;
			NumberFormat formatter = NumberFormat.getInstance();
			ParsePosition pos = new ParsePosition(0);
			formatter.parse(str, pos);
			return str.length() == pos.getIndex();
		}*/
}
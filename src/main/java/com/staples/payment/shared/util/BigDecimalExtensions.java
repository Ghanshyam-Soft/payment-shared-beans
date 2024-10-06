package com.staples.payment.shared.util;

import java.math.BigDecimal;

/**
 * @author McaNi002
 *
 * This class is used to create extension methods for the BigDecimal class. Extension methods are methods that appear to be part of the class but are not actually so.
 * 
 * In order to use them, annotation the class you are calling from with @ExtensionMethod({BigDecimalExtensions.class})
 * 
 * Please note, Project Lombok's @ExtensionMethod feature may not work with all IDEs for autocomplete, search, and other such functionalities. 
 * There are also some limitations on what extension methods can be created, they must be static and their parameters can't be lambdas unless you use some extremely ugly syntax. 
 * 
 * If you wish to switch back to not using extension methods, simply replace the calls like so:
 * 		value.isNegative()     becomes      BigDecimalExtensions.isNegative(value)
 * Also if you are going to not use extension methods, I recommend renaming this class to BigDecimalUtil and statically importing the methods in other classes.
 */
public class BigDecimalExtensions
{
	public static boolean isZero(final BigDecimal value)
	{
		return value.signum() == 0;
	}

	public static boolean isNegative(final BigDecimal value)
	{
		return value.signum() < 0;
	}

	public static boolean isPositive(BigDecimal value)
	{
		return value.signum() > 0;
	}

	public static boolean hasOppositeSignTo(BigDecimal value1, BigDecimal value2)
	{
		return (isPositive(value1) && isNegative(value2)) || (isNegative(value1) && isPositive(value2));
	}

	public static boolean hasSameSignAs(BigDecimal value1, BigDecimal value2)
	{
		return (isPositive(value1) && isPositive(value2)) || (isNegative(value1) && isNegative(value2));
	}

	public static boolean isLessThan(BigDecimal value1, BigDecimal value2)
	{
		return value1.compareTo(value2) < 0;
	}

	public static boolean isLessThanOrEqualTo(BigDecimal value1, BigDecimal value2)
	{
		return value1.compareTo(value2) <= 0;
	}

	public static boolean isGreaterThan(BigDecimal value1, BigDecimal value2)
	{
		return value1.compareTo(value2) > 0;
	}

	public static boolean isGreaterThanOrEqualTo(BigDecimal value1, BigDecimal value2)
	{
		return value1.compareTo(value2) >= 0;
	}

	public static boolean isEqualTo(BigDecimal value1, BigDecimal value2)
	{
		return value1.compareTo(value2) == 0;
	}
}

package dev.anderson.emprestimoapi.types;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MembershipTest {

    @Test
    @DisplayName("Test if getEndValue returns the correct value for Bronze membership")
    void getEndValueWithBronze() {
        BigDecimal startValue = BigDecimal.valueOf(1000);
        Integer numberOfLoans = 0;
        BigDecimal actual = Membership.Bronze.getEndValue(startValue, numberOfLoans);

        BigDecimal expected = BigDecimal.valueOf(1800.0);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if getEndValue returns the correct value for Silver membership")
    void getEndValueWithSilver() {
        BigDecimal startValue = BigDecimal.valueOf(1000);
        Integer numberOfLoans = 0;
        BigDecimal actual = Membership.Silver.getEndValue(startValue, numberOfLoans);

        BigDecimal expected = BigDecimal.valueOf(1600.0);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if getEndValue returns the correct value for Silver membership and salary greater than 5000")
    void getEndValueWithSilverAndSalaryGreaterThan5000() {
        BigDecimal startValue = BigDecimal.valueOf(6000);
        Integer numberOfLoans = 0;
        BigDecimal actual = Membership.Silver.getEndValue(startValue, numberOfLoans);

        BigDecimal expected = BigDecimal.valueOf(9000.0);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if getEndValue returns the correct value for Silver membership and salary less than 5000")
    void getEndValueWithSilverAndSalaryLessThan5000() {
        BigDecimal startValue = BigDecimal.valueOf(4000);
        Integer numberOfLoans = 0;
        BigDecimal actual = Membership.Silver.getEndValue(startValue, numberOfLoans);

        BigDecimal expected = BigDecimal.valueOf(6400.0);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if getEndValue returns the correct value for Gold membership")
    void getEndValueWithGoldAndOneLoan() {
        BigDecimal startValue = BigDecimal.valueOf(1000);
        Integer numberOfLoans = 1;
        BigDecimal actual = Membership.Gold.getEndValue(startValue, numberOfLoans);

        BigDecimal expected = BigDecimal.valueOf(1200.0);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if getEndValue returns the correct value for Gold membership and Client with two loans")
    void getEndValueWithGoldAndTwoLoans() {
        BigDecimal startValue = BigDecimal.valueOf(1000);
        Integer numberOfLoans = 2;
        BigDecimal actual = Membership.Gold.getEndValue(startValue, numberOfLoans);

        BigDecimal expected = BigDecimal.valueOf(1300.0);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if fromString returns the correct value for Gold membership")
    void fromStringWithGoldValue() {
        String value = "Gold";
        Membership actual = Membership.fromString(value);

        Membership expected = Membership.Gold;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if fromString returns the correct value for Silver membership")
    void fromStringWithSilverValue() {
        String value = "Silver";
        Membership actual = Membership.fromString(value);

        Membership expected = Membership.Silver;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if fromString returns the correct value for Bronze membership")
    void fromStringWithBronzeValue() {
        String value = "Bronze";
        Membership actual = Membership.fromString(value);

        Membership expected = Membership.Bronze;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if fromString returns null for null value")
    void fromStringWithNullValue() {
        String value = null;
        Membership actual = Membership.fromString(value);

        Membership expected = null;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if fromString returns the correct value for Gold membership with uppercase value")
    void fromUpperCaseStringWithGoldValue() {
        String value = "GOLD";
        Membership actual = Membership.fromString(value);

        Membership expected = Membership.Gold;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if fromString returns the correct value for Silver membership with uppercase value")
    void fromUpperCaseStringWithSilverValue() {
        String value = "SILVER";
        Membership actual = Membership.fromString(value);

        Membership expected = Membership.Silver;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if fromString returns the correct value for Bronze membership with uppercase value")
    void fromUpperCaseStringWithBronzeValue() {
        String value = "BRONZE";
        Membership actual = Membership.fromString(value);

        Membership expected = Membership.Bronze;

        assertEquals(expected, actual);
    }
}
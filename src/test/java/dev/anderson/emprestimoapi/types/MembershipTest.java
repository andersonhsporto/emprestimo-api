package dev.anderson.emprestimoapi.types;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MembershipTest {


    @DisplayName("Test if getEndValue returns the correct value for Bronze membership")
    @ParameterizedTest
    @EnumSource(
            value = Membership.class,
            names = {"BRONZE"}
    )
    void getEndValueWithBronze(Membership membership) {
        BigDecimal startValue = BigDecimal.valueOf(1000);
        Integer numberOfLoans = 0;
        BigDecimal actual = membership.getEndValue(startValue, numberOfLoans);

        BigDecimal expected = BigDecimal.valueOf(1800.0);

        assertEquals(expected, actual);
    }

    @DisplayName("Test if getEndValue returns the correct value for Silver membership")
    @ParameterizedTest
    @EnumSource(
            value = Membership.class,
            names = {"SILVER"}
    )
    void getEndValueWithSilver(Membership membership) {
        BigDecimal startValue = BigDecimal.valueOf(1000);
        Integer numberOfLoans = 0;
        BigDecimal actual = membership.getEndValue(startValue, numberOfLoans);

        BigDecimal expected = BigDecimal.valueOf(1600.0);

        assertEquals(expected, actual);
    }

    @DisplayName("Test if getEndValue returns the correct value for Silver membership and salary greater than 5000")
    @ParameterizedTest
    @EnumSource(
            value = Membership.class,
            names = {"SILVER"}
    )
    void getEndValueWithSilverAndSalaryGreaterThan5000(Membership membership) {
        BigDecimal startValue = BigDecimal.valueOf(6000);
        Integer numberOfLoans = 0;
        BigDecimal actual = membership.getEndValue(startValue, numberOfLoans);

        BigDecimal expected = BigDecimal.valueOf(9000.0);

        assertEquals(expected, actual);
    }

    @DisplayName("Test if getEndValue returns the correct value for Silver membership and salary less than 5000")
    @ParameterizedTest
    @EnumSource(
            value = Membership.class,
            names = {"SILVER"}
    )
    void getEndValueWithSilverAndSalaryLessThan5000(Membership membership) {
        BigDecimal startValue = BigDecimal.valueOf(4000);
        Integer numberOfLoans = 0;
        BigDecimal actual = membership.getEndValue(startValue, numberOfLoans);

        BigDecimal expected = BigDecimal.valueOf(6400.0);

        assertEquals(expected, actual);
    }

    @DisplayName("Test if getEndValue returns the correct value for Gold membership")
    @ParameterizedTest
    @EnumSource(
            value = Membership.class,
            names = {"GOLD"}
    )
    void getEndValueWithGoldAndOneLoan(Membership membership) {
        BigDecimal startValue = BigDecimal.valueOf(1000);
        Integer numberOfLoans = 1;
        BigDecimal actual = membership.getEndValue(startValue, numberOfLoans);

        BigDecimal expected = BigDecimal.valueOf(1200.0);

        assertEquals(expected, actual);
    }

    @DisplayName("Test if getEndValue returns the correct value for Gold membership and Client with two loans")
    @ParameterizedTest
    @EnumSource(
            value = Membership.class,
            names = {"GOLD"}
    )
    void getEndValueWithGoldAndTwoLoans(Membership membership) {
        BigDecimal startValue = BigDecimal.valueOf(1000);
        Integer numberOfLoans = 2;
        BigDecimal actual = membership.getEndValue(startValue, numberOfLoans);

        BigDecimal expected = BigDecimal.valueOf(1300.0);

        assertEquals(expected, actual);
    }

    @DisplayName("Test if fromString returns the correct value for Gold membership")
    @ParameterizedTest
    @EnumSource(
            value = Membership.class,
            names = {"GOLD"}
    )
    void fromStringWithGoldValue(Membership membership) {
        String value = "Gold";
        Membership actual = Membership.fromString(value);

        assertEquals(membership, actual);
    }

    @DisplayName("Test if fromString returns the correct value for Silver membership")
    @ParameterizedTest
    @EnumSource(
            value = Membership.class,
            names = {"SILVER"}
    )
    void fromStringWithSilverValue(Membership membership) {
        String value = "Silver";
        Membership actual = Membership.fromString(value);

        assertEquals(membership, actual);
    }

    @DisplayName("Test if fromString returns the correct value for Bronze membership")
    @ParameterizedTest
    @EnumSource(
            value = Membership.class,
            names = {"BRONZE"}
    )
    void fromStringWithBronzeValue(Membership membership) {
        String value = "Bronze";
        Membership actual = Membership.fromString(value);

        assertEquals(membership, actual);
    }

    @Test
    @DisplayName("Test if fromString returns null for null value")
    void fromStringWithNullValue() {
        String value = "null";
        Membership actual = Membership.fromString(value);

        assertNull(actual);
    }

    @DisplayName("Test if fromString returns the correct value for Gold membership with uppercase value")
    @ParameterizedTest
    @EnumSource(
            value = Membership.class,
            names = {"GOLD"}
    )
    void fromUpperCaseStringWithGoldValue(Membership membership) {
        String value = "GOLD";
        Membership actual = Membership.fromString(value);

        assertEquals(membership, actual);
    }

    @DisplayName("Test if fromString returns the correct value for Silver membership with uppercase value")
    @ParameterizedTest
    @EnumSource(
            value = Membership.class,
            names = {"SILVER"}
    )
    void fromUpperCaseStringWithSilverValue(Membership membership) {
        String value = "SILVER";
        Membership actual = Membership.fromString(value);

        assertEquals(membership, actual);
    }

    @DisplayName("Test if fromString returns the correct value for Bronze membership with uppercase value")
    @ParameterizedTest
    @EnumSource(
            value = Membership.class,
            names = {"BRONZE"}
    )
    void fromUpperCaseStringWithBronzeValue(Membership membership) {
        String value = "BRONZE";
        Membership actual = Membership.fromString(value);

        assertEquals(membership, actual);
    }
}
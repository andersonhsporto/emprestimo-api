package dev.anderson.emprestimoapi.types;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;

public enum Membership {

    BRONZE() {
        @Override
        public BigDecimal getEndValue(BigDecimal startValue, Integer numberOfLoans) {
            return startValue.multiply(bronzeTax);
        }
    },

    SILVER() {
        @Override
        public BigDecimal getEndValue(BigDecimal startValue, Integer numberOfLoans) {
            if (startValue.compareTo(BigDecimal.valueOf(5000)) > 0) {
                return startValue.multiply(silverTaxForMoreThan5000);
            } else {
                return startValue.multiply(silverTax);
            }
        }
    },

    GOLD() {
        @Override
        public BigDecimal getEndValue(BigDecimal startValue, Integer numberOfLoans) {
            if (numberOfLoans < 2) {
                return startValue.multiply(goldTax);
            } else {
                return startValue.multiply(goldTaxForMoreThan2Loans);
            }
        }
    };

    private static final BigDecimal bronzeTax = BigDecimal.valueOf(1.8);
    private static final BigDecimal silverTax = BigDecimal.valueOf(1.6);
    private static final BigDecimal silverTaxForMoreThan5000 = BigDecimal.valueOf(1.5);
    private static final BigDecimal goldTax = BigDecimal.valueOf(1.2);
    private static final BigDecimal goldTaxForMoreThan2Loans = BigDecimal.valueOf(1.3);

    Membership() {
    }

    @JsonCreator
    public static Membership fromString(String value) {
        if (value != null) {
            for (Membership membership : Membership.values()) {
                if (value.equalsIgnoreCase(membership.name()) || value.equalsIgnoreCase(membership.translate())) {
                    return membership;
                }
            }
        }
        return null;
    }

    public abstract BigDecimal getEndValue(BigDecimal salary, Integer numberOfLoans);

    private String translate() {
        switch (this) {
            case BRONZE:
                return "Bronze";
            case SILVER:
                return "Prata";
            case GOLD:
                return "Ouro";
        }
        return null;
    }

}

package dev.anderson.emprestimoapi.common.types;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;

public enum Membership {

    BRONZE() {
        @Override
        public BigDecimal getEndValue(BigDecimal startValue, Integer numberOfLoans) {
            return startValue.multiply(BRONZE_TAX);
        }
    },

    SILVER() {
        @Override
        public BigDecimal getEndValue(BigDecimal startValue, Integer numberOfLoans) {
            if (startValue.compareTo(BigDecimal.valueOf(5000)) > 0) {
                return startValue.multiply(SILVER_TAX_FOR_MORE_THAN_5000);
            } else {
                return startValue.multiply(SILVER_TAX);
            }
        }
    },

    GOLD() {
        @Override
        public BigDecimal getEndValue(BigDecimal startValue, Integer numberOfLoans) {
            if (numberOfLoans < 2) {
                return startValue.multiply(GOLD_TAX);
            } else {
                return startValue.multiply(GOLD_TAX_FOR_MORE_THAN_2_LOANS);
            }
        }
    };

    private static final BigDecimal BRONZE_TAX = BigDecimal.valueOf(1.8);
    private static final BigDecimal SILVER_TAX = BigDecimal.valueOf(1.6);
    private static final BigDecimal SILVER_TAX_FOR_MORE_THAN_5000 = BigDecimal.valueOf(1.5);
    private static final BigDecimal GOLD_TAX = BigDecimal.valueOf(1.2);
    private static final BigDecimal GOLD_TAX_FOR_MORE_THAN_2_LOANS = BigDecimal.valueOf(1.3);

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

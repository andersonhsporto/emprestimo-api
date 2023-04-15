package dev.anderson.emprestimoapi.types;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;

public enum Membership {

    Bronze() {
        @Override
        public BigDecimal getEndValue(BigDecimal startValue, Integer numberOfLoans) {
            return startValue.multiply(BigDecimal.valueOf(1.8));
        }
    },
    Silver() {
        @Override
        public BigDecimal getEndValue(BigDecimal startValue, Integer numberOfLoans) {
            if (startValue.compareTo(BigDecimal.valueOf(5000)) > 0) {
                return startValue.multiply(BigDecimal.valueOf(1.5));
            } else {
                return startValue.multiply(BigDecimal.valueOf(1.6));
            }
        }
    },
    Gold() {
        @Override
        public BigDecimal getEndValue(BigDecimal startValue, Integer numberOfLoans) {
            if (numberOfLoans < 2) {
                return startValue.multiply(BigDecimal.valueOf(1.2));
            } else {
                return startValue.multiply(BigDecimal.valueOf(1.3));
            }
        }
    };

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
            case Bronze:
                return "Bronze";
            case Silver:
                return "Prata";
            case Gold:
                return "Ouro";
        }
        return null;
    }

}

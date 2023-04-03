package dev.anderson.emprestimoapi.types;

import java.math.BigDecimal;

public enum Membership {

    Bronze(1) {
        @Override
        public BigDecimal getMembershipStatus(BigDecimal startValue, Integer numberOfLoans) {
            return startValue.multiply(BigDecimal.valueOf(1.8));
        }
    },
    Silver(2) {
        @Override
        public BigDecimal getMembershipStatus(BigDecimal startValue, Integer numberOfLoans) {
            if (startValue.compareTo(BigDecimal.valueOf(5000)) > 0) {
                return startValue.multiply(BigDecimal.valueOf(1.5));
            } else {
                return startValue.multiply(BigDecimal.valueOf(1.6));
            }
        }
    },
    Gold(3) {
        @Override
        public BigDecimal getMembershipStatus(BigDecimal startValue, Integer numberOfLoans) {
            if (numberOfLoans == 1) {
                return startValue.multiply(BigDecimal.valueOf(1.2));
            } else {
                return startValue.multiply(BigDecimal.valueOf(1.3));
            }
        }
    };

    private Membership(int code) {
    }

    public abstract BigDecimal getMembershipStatus(BigDecimal salary, Integer numberOfLoans);

}

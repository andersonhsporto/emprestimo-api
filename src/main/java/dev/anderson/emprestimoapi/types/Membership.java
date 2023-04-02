package dev.anderson.emprestimoapi.types;

import java.math.BigDecimal;

public enum Membership {

    Bronze(1) {
        @Override
        public BigDecimal getMembershipStatus(BigDecimal salary) {
            return salary.multiply(BigDecimal.valueOf(0.2));
        }
    },
    Silver(2) {
        @Override
        public BigDecimal getMembershipStatus(BigDecimal salary) {
            return salary.multiply(BigDecimal.valueOf(0.4));
        }
    },
    Gold(3) {
        @Override
        public BigDecimal getMembershipStatus(BigDecimal salary) {
            return salary.multiply(BigDecimal.valueOf(0.6));
        }
    };

    private int code;
    private Membership(int code) {
        this.code = code;
    }

    public abstract BigDecimal getMembershipStatus(BigDecimal salary);

}

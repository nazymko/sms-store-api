package org.nazymko.moneygraph.analytics.model.support;

import java.math.BigDecimal;

/**
 * Created by a.nazimko on 16.03.2017.
 */
public class Money {
    private BigDecimal value;
    private Currency currency;
    private MoneyType type;

    public Money(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public void setType(MoneyType type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;
        if (value != null && money.value != null) {
            if (value.compareTo(money.getValue()) != 0) {
                return false;
            }
        }
        return currency == money.currency;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                ", currency=" + currency +
                ", type=" + type +
                '}';
    }


}

package org.nazymko.moneygraph.analytics.model.support;

/**
 * Created by a.nazimko on 16.03.2017.
 */
public enum Currency {
    UAH, USD, EUR, RUB,

    UNKNOWN



    ;

    public static Currency parse(String str){
        for (Currency currency : values()) {
            if(currency.name().equalsIgnoreCase(str)){
                return currency;
            }
        }

        return UNKNOWN;
    }
}

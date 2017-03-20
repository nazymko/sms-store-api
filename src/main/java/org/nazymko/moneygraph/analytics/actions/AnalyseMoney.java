package org.nazymko.moneygraph.analytics.actions;

import org.nazymko.moneygraph.analytics.model.Argument;
import org.nazymko.moneygraph.analytics.model.Processed;
import org.nazymko.moneygraph.analytics.model.Sms;
import org.nazymko.moneygraph.analytics.model.support.AnalyseType;
import org.nazymko.moneygraph.analytics.model.support.Currency;
import org.nazymko.moneygraph.analytics.model.support.Element;
import org.nazymko.moneygraph.analytics.model.support.Money;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Created by a.nazimko on 17.03.2017.
 */
public class AnalyseMoney implements Analyse {
    @Override
    public void apply(Sms sms, Processed.Metadata metadata) {
        final Set<Element> currency = metadata.meta(Element.Type.CURRENCY);
        final Set<Element> digits = metadata.meta(Element.Type.DECIMAL);

        if (digits == null) {
            return;
        }
        for (Element digitMeta : digits) {
            final int digitIndex = digitMeta.getIndex();

            for (Element currencyMeta : currency) {
                final int currencyIndex = currencyMeta.getIndex();
                if (isCurrencyAfterDigits(digitIndex, currencyIndex)) {
                    metadata.putMeta(Element.Type.MONEY,
                            new Element(
                                    digitIndex,
                                    Element.Type.MONEY,
                                    new Money(
                                            (BigDecimal) digitMeta.getValue(),
                                            Currency.parse(currencyMeta.getRaw())
                                    ),
                                    digitMeta.getRaw()
                            )
                    );
                }
            }
        }

    }

    private boolean isCurrencyAfterDigits(int digitIndex, int currencyIndex) {
        return currencyIndex == digitIndex + 1;
    }

    @Override
    public void apply(List<Processed> sms) {

    }

    @Override
    public AnalyseType type() {
        return AnalyseType.SINGLE;
    }

    @Override
    public String name() {
        return "Find money in sms by using currency and decimal";
    }

    @Override
    public List<Argument> arguments() {
        return null;
    }
}

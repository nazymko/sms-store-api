package org.nazymko.moneygraph.analytics.actions;

import org.nazymko.moneygraph.analytics.model.Argument;
import org.nazymko.moneygraph.analytics.model.Processed;
import org.nazymko.moneygraph.analytics.model.Sms;
import org.nazymko.moneygraph.analytics.model.support.AnalyseType;
import org.nazymko.moneygraph.analytics.model.support.Element;

import java.util.Arrays;
import java.util.List;

/**
 * Created by a.nazimko on 16.03.2017.
 */
public class AnalyseCurrency implements Analyse {
    @Override
    public void apply(Sms sms, Processed.Metadata metadata) {
        final String[] split = split(sms);
        int index = 0;
        for (String word : split) {
            for (String currency : knownCurrencies()) {
                if (word.equalsIgnoreCase(currency)) {
                    metadata.putMeta(Element.Type.CURRENCY,
                            new Element(
                                    index,
                                    Element.Type.CURRENCY,
                                    currency,
                                    word
                            )
                    );
                }
            }

            index++;

        }
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
        return "Currency derivation";
    }

    @Override
    public List<Argument> arguments() {
        return Arrays.asList(
                new Argument("Currency", Argument.Type.STRING)
        );
    }

    private String[] knownCurrencies() {
        return new String[]{"UAH", "EUR", "USD", "RUB"};
    }
}

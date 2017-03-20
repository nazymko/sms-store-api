package org.nazymko.moneygraph.analytics.model;

import org.nazymko.moneygraph.analytics.model.support.Element;
import org.nazymko.moneygraph.analytics.model.support.Money;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by a.nazimko on 20.03.2017.
 */
public class Rule {
    int order;
    MoneyType type;

    public Rule(int order, MoneyType type) {
        this.order = order;
        this.type = type;
    }

    public BigDecimal execute(Processed processed) {
        final List<Element> elements = processed.getMetadata().sortedMeta(Element.Type.MONEY);
        if (elements.size() <= order) {
            return BigDecimal.ZERO;
        }
        return ((Money) elements.get(order).getValue()).getValue();
    }

}

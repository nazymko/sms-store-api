package org.nazymko.moneygraph.analytics.actions;

import org.nazymko.moneygraph.analytics.model.Argument;
import org.nazymko.moneygraph.analytics.model.Processed;
import org.nazymko.moneygraph.analytics.model.Sms;
import org.nazymko.moneygraph.analytics.model.support.AnalyseType;
import org.nazymko.moneygraph.analytics.model.support.Element;
import org.nazymko.moneygraph.analytics.utils.NumberUtil;

import java.util.List;

/**
 * Created by a.nazimko on 17.03.2017.
 */
public class AnalyseBalance implements Analyse {
    @Override
    public void apply(Sms sms, Processed.Metadata metadata) {
        final String[] words = split(sms);

        int index = 0;
        for (String word : words) {
            if (NumberUtil.isDecimal(word)) {
                metadata.putMeta(Element.Type.DECIMAL,
                        new Element(index,
                                Element.Type.DECIMAL,
                                NumberUtil.parse(word),
                                word
                        )
                );
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
        return "Find all decimal numbers";
    }

    @Override
    public List<Argument> arguments() {
        return null;
    }
}

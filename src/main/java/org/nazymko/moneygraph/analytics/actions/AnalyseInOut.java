package org.nazymko.moneygraph.analytics.actions;

import org.nazymko.moneygraph.analytics.model.Argument;
import org.nazymko.moneygraph.analytics.model.Processed;
import org.nazymko.moneygraph.analytics.model.Sms;
import org.nazymko.moneygraph.analytics.model.support.AnalyseType;
import org.nazymko.moneygraph.analytics.model.support.Element;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by a.nazimko on 21.03.2017.
 */
public class AnalyseInOut implements Analyse {

    @Override
    public void apply(Sms sms, Processed.Metadata metadata) {

    }

    @Override
    public void apply(List<Processed> sms) {
        Collections.sort(sms, Comparator.comparing(o -> o.getSms().getTime()));

        for (int main_ = 0; main_ < sms.size(); main_++) {
            final Processed main = sms.get(main_);
            if (main.getMetadata().meta(Element.Type.MONEY) == null) {
                continue;
            }

            for (int second_ = main_ + 1; second_ < sms.size(); second_++) {
                final Processed second = sms.get(second_);
                if (second.getMetadata().meta(Element.Type.MONEY) == null
                        || second.getMetadata().meta(Element.Type.MONEY).size() == 1) {
                    continue;
                }

//                main.getMetadata().sortedMeta()


            }
        }

    }

    @Override
    public AnalyseType type() {
        return AnalyseType.BULK;
    }

    @Override
    public String name() {
        return "Discriminate sms by money flow (in and out)";
    }

    @Override
    public List<Argument> arguments() {
        return null;
    }
}

package org.nazymko.moneygraph.analytics;

import org.nazymko.moneygraph.analytics.actions.*;
import org.nazymko.moneygraph.analytics.model.Processed;
import org.nazymko.moneygraph.analytics.model.Sms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by a.nazimko on 17.03.2017.
 */
public class Executor {

    public List<Processed> execute(List<Analyse> rules, List<Sms> sms) {

        List<Processed> processeds = convert(sms);

        for (Analyse rule : rules) {
            switch (rule.type()) {
                case SINGLE:
                    for (Processed processed : processeds) {
                        rule.apply(processed.getSms(), processed.getMetadata());
                        processed.getMetadata().executed(rule.getClass());
                    }
                    break;
                case BULK:
                    rule.apply(processeds);
                    for (Processed processed : processeds) {
                        processed.getMetadata().executed(rule.getClass());
                    }
                    break;
            }

        }

        return processeds;
    }

    private List<Processed> convert(List<Sms> sms) {

        List<Processed> processeds = new ArrayList<>();
        for (Sms sm : sms) {
            processeds.add(new Processed(sm));
        }
        return processeds;
    }


    public List<Processed> balance(List<Sms> sms) {
        return execute(rulesForBalance(), sms);
    }

    public List<Analyse> rulesForBalance() {
        return Arrays.asList(
                new AnalyseBalance(),
                new AnalyseCurrency(),
                new AnalyseMoney(),
                new AnalyseHoldDebit()
        );
    }
}

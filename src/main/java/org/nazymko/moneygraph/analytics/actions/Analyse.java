package org.nazymko.moneygraph.analytics.actions;

import org.nazymko.moneygraph.analytics.model.Argument;
import org.nazymko.moneygraph.analytics.model.Processed;
import org.nazymko.moneygraph.analytics.model.Sms;
import org.nazymko.moneygraph.analytics.model.support.AnalyseType;

import java.util.List;

/**
 * Created by a.nazimko on 16.03.2017.
 */
public interface Analyse {

    void apply(Sms sms, Processed.Metadata metadata);

    void apply(List<Processed> sms);

    public AnalyseType type();

    String name();

    List<Argument> arguments();


    default String[] split(Sms sms) {
        return sms.getBody().split(" ");
    }

}

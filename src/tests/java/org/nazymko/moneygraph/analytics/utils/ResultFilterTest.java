package org.nazymko.moneygraph.analytics.utils;

import org.junit.Test;
import org.nazymko.moneygraph.analytics.Executor;
import org.nazymko.moneygraph.analytics.PipeReport;
import org.nazymko.moneygraph.analytics.model.MoneyType;
import org.nazymko.moneygraph.analytics.model.Processed;
import org.nazymko.moneygraph.analytics.model.Rule;
import org.nazymko.moneygraph.testing.SmsConverter;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by a.nazimko on 20.03.2017.
 */
public class ResultFilterTest {
    @Test
    public void convertIntoPipeByWeek() throws Exception {

        final List<Processed> balance = new Executor().balance(new SmsConverter().open());
        final PipeReport pipeReport = ResultFilter.convertIntoPipeByWeek(balance, new Rule(1, MoneyType.OUTCOME));
        System.out.println("pipeReport = " + pipeReport);
    }

}
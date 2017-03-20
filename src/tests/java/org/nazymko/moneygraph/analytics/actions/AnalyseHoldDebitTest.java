package org.nazymko.moneygraph.analytics.actions;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.nazymko.moneygraph.analytics.Executor;
import org.nazymko.moneygraph.analytics.model.Processed;
import org.nazymko.moneygraph.testing.SmsConverter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by a.nazimko on 17.03.2017.
 */
public class AnalyseHoldDebitTest {
    @Test
    public void apply() throws Exception {
        final Executor executor = new Executor();


        final List<Processed> list = executor.execute(
                Arrays.asList(
                        new AnalyseBalance(),
                        new AnalyseCurrency(),
                        new AnalyseMoney()
                ),
                new SmsConverter().open());
        new AnalyseHoldDebit().apply(list);
//        System.out.println("list = " + list);
    }

    @Test
    public void testBetween() throws Exception {
        final LocalDateTime from = LocalDateTime.of(2017, 11, 1, 0, 0);
        final LocalDateTime to = LocalDateTime.of(2017, 11, 10, 0, 0);

        final LocalDateTime no = LocalDateTime.of(2017, 12, 10, 0, 0);

        final boolean yes = new AnalyseHoldDebit().meetDateRestrictions(20, from, to);

        final boolean _no = new AnalyseHoldDebit().meetDateRestrictions(20, from, no);
        Assertions.assertThat(yes).isTrue();
        Assertions.assertThat(_no).isFalse();
    }
}
package org.nazymko.moneygraph.analytics;

import org.junit.Before;
import org.junit.Test;
import org.nazymko.moneygraph.analytics.actions.AnalyseBalance;
import org.nazymko.moneygraph.analytics.actions.AnalyseCurrency;
import org.nazymko.moneygraph.analytics.actions.AnalyseHoldDebit;
import org.nazymko.moneygraph.analytics.actions.AnalyseMoney;
import org.nazymko.moneygraph.analytics.model.Processed;
import org.nazymko.moneygraph.analytics.model.Sms;
import org.nazymko.moneygraph.analytics.model.support.Element;
import org.nazymko.moneygraph.analytics.model.support.Order;
import org.nazymko.moneygraph.testing.SmsConverter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by a.nazimko on 17.03.2017.
 */
public class ExecutorTest {
    List<Sms> open;

    @Before
    public void setUp() throws Exception {
        open = new SmsConverter().open();

    }

    @Test
    public void testLite() throws Exception {
        final Executor executor = new Executor();

        String sms1 = "PROCREDIT GSM CODE 190365804 *0173 2015-12-30 19:56:45 *HOLD 390.00 UAH BIGUDI25KJ6R8D8PG200 KIYEV UA *BALANCE 77061.43 UAH";
        String sms2 = "PROCREDIT GSM CODE 190365804 *0173 2015-12-30 19:56:45 *DEBIT 390.00 UAH BIGUDI25KJ6R8D8PG200 KIYEV UA *BALANCE 77061.43 UAH";


        final List<Processed> prodessed = executor.execute(
                Arrays.asList(
                        new AnalyseBalance(),
                        new AnalyseCurrency(),
                        new AnalyseMoney(),
                        new AnalyseHoldDebit()
                ),
                Arrays.asList(
                        new Sms(sms1, LocalDateTime.of(2015, 12, 30, 19, 56)),
                        new Sms(sms2, LocalDateTime.of(2015, 12, 30, 19, 57))

                ));
        System.out.println("prodessed = " + prodessed);
    }

    @Test
    public void execute() throws Exception {

        final Executor executor = new Executor();

        final List<Processed> prodessed = executor.execute(
                Arrays.asList(
                        new AnalyseBalance(),
                        new AnalyseCurrency(),
                        new AnalyseMoney(),
                        new AnalyseHoldDebit()
                ),
                open);


        printMasterSlaveOrder(prodessed);

//        System.out.println("prodessed = " + prodessed);
    }

    private void printMasterSlaveOrder(List<Processed> prodessed) {

        int slave = 0;
        int master = 0;
        int single = 0;
        for (Processed processed : prodessed) {
            final Set<Element> meta = processed.getMetadata().meta(Element.Type.MASTER);
            if (meta == null) {
                single++;
                System.err.println(processed.getSms().getBody());
                continue;
            }
            System.out.println(processed.getSms().getBody());
            for (Element element : meta) {
                final Order value = (Order) element.getValue();
                switch (value.getPosition()) {
                    case SLAVE:
                        master++;
                        System.out.println("\tslave\t: " + value.getRefference().getSms().getBody());
                        break;
                    case MASTER:
                        slave++;
                        break;
                }
            }
        }

        System.out.println("single = " + single);
        System.out.println("master = " + master);
        System.out.println("slave = " + slave);
        System.out.println("total = " + prodessed.size());
    }
}
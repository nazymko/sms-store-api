package org.nazymko.moneygraph.analytics.utils;

import org.nazymko.moneygraph.analytics.LineReport;
import org.nazymko.moneygraph.analytics.PipeReport;
import org.nazymko.moneygraph.analytics.Report;
import org.nazymko.moneygraph.analytics.model.Processed;
import org.nazymko.moneygraph.analytics.model.Rule;
import org.nazymko.moneygraph.analytics.model.support.*;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.*;

/**
 * Created by a.nazimko on 17.03.2017.
 */
public class ResultFilter {

    public static List<Processed> removeSingleSms(List<Processed> processeds) {
        List<Processed> res = new ArrayList<>();

        for (Processed processed : processeds) {

            if (processed.getMetadata().meta(Element.Type.MASTER) != null) {
                res.add(processed);
            }

        }

        return res;
    }

    public static Report convertIntoLegend(List<Processed> elements, int moneyIndex) {
        List<Report.Legend> legends = new ArrayList<>();
        for (Processed element : elements) {
            final Report.Legend e = _convertToLegend(element, moneyIndex);
            if (e != null) {
                legends.add(e);
            }
        }

        return new LineReport("Line money1", legends);

    }

    private static Report.Legend _convertToLegend(Processed element, int index) {

        final Set<Element> moneyMeta = element.getMetadata().meta(Element.Type.MONEY);

        List<Element> elements = new ArrayList<>(moneyMeta);
        elements.sort((o1, o2) -> o1.getIndex() == o2.getIndex() ? 0 : o1.getIndex() > o2.getIndex() ? 1 : -1);


        if (elements.size() <= index) {
            return null;
        }

        return new Report.Legend((Money) elements.get(index).getValue(),
                "",
                element.getSms().getTime(),
                index);
    }

    public static PipeReport convertIntoPipeByWeek(List<Processed> elements, Rule rules) {
        HashMap<DayOfWeek, BigDecimal> days = new HashMap();

        for (Processed sms : elements) {
            final Set<Element> meta = sms.getMetadata().meta(Element.Type.MASTER);
            if (meta != null) {

                final DayOfWeek dayOfWeek = sms.getSms().getTime().getDayOfWeek();
                if (!days.containsKey(dayOfWeek)) {
                    days.put(dayOfWeek, rules.execute(sms));
                } else {
                    final BigDecimal value = rules.execute(sms);
                    days.put(dayOfWeek, days.get(dayOfWeek).add(value));
                }

            }
        }


        List<Report.Legend> legend = new ArrayList<>();

        for (Map.Entry<DayOfWeek, BigDecimal> dayOfWeek : days.entrySet()) {
            legend.add(new Report.Legend(new Money(dayOfWeek.getValue(), org.nazymko.moneygraph.analytics.model.support.Currency.valueOf("UAH")),
                    dayOfWeek.getKey().name(), null, dayOfWeek.getKey().ordinal()));
        }

        return new PipeReport("Pipe report", legend);
    }
}

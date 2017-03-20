package org.nazymko.moneygraph.analytics.actions;

import org.nazymko.moneygraph.analytics.model.Argument;
import org.nazymko.moneygraph.analytics.model.Processed;
import org.nazymko.moneygraph.analytics.model.Sms;
import org.nazymko.moneygraph.analytics.model.support.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by a.nazimko on 17.03.2017.
 */
public class AnalyseHoldDebit implements Analyse {
    @Override
    public void apply(Sms sms, Processed.Metadata metadata) {

    }

    @Override
    public void apply(List<Processed> sms) {
        sortByDate(sms);

        int maxDays = 30;

        for (int i = 0; i < sms.size(); i++) {
            for (int j = i + 1; j < sms.size(); j++) {
                final LocalDateTime zeroDate = sms.get(i).getSms().getTime();
                final LocalDateTime currentDate = sms.get(j).getSms().getTime();

                if ((sms.get(i).getMetadata().meta(Element.Type.MASTER) == null
                        && sms.get(i).getMetadata().meta(Element.Type.SLAVE) == null)
                        && (sms.get(j).getMetadata().meta(Element.Type.MASTER) == null
                        && sms.get(j).getMetadata().meta(Element.Type.SLAVE) == null)
                        && meetDateRestrictions(maxDays, zeroDate, currentDate)) {
                    final Processed current = sms.get(i);
                    final Processed sub = sms.get(j);

                    if (hasSameMoney(current, sub)) {
                        current.getMetadata().putMeta(Element.Type.MASTER,
                                new Element(-1, Element.Type.MASTER, new Order(sub, Order.Position.SLAVE), ""));
                        sub.getMetadata().putMeta(Element.Type.SLAVE,
                                new Element(-1, Element.Type.SLAVE, new Order(current, Order.Position.MASTER), ""));
                        break;
                    }


                } else {
                    break;
                }
            }

        }


    }

    private boolean hasSameMoney(Processed current, Processed sub) {
        final Set<Element> subMoney = sub.getMetadata().meta(Element.Type.MONEY);
        final Set<Element> currentMoney = current.getMetadata().meta(Element.Type.MONEY);

        if (subMoney == null || currentMoney == null) {
            return false;
        }

        for (Element cMoney : currentMoney) {
            for (Element sMoney : subMoney) {

                if (cMoney.getValue().equals(sMoney.getValue())) {
                    return true;
                }


            }

        }


        return false;
    }

    protected boolean meetDateRestrictions(int maxDays, LocalDateTime zeroDate, LocalDateTime currentDate) {
        return DAYS.between(zeroDate, currentDate) < maxDays;
    }

    private void sortByDate(List<Processed> sms) {
        sms.sort(new Comparator<Processed>() {
            @Override
            public int compare(Processed o1, Processed o2) {
                return o1.getSms().getTime().compareTo(o2.getSms().getTime());
            }
        });
    }

    @Override
    public AnalyseType type() {
        return AnalyseType.BULK;
    }

    @Override
    public String name() {
        return "Separate Hold and Debit notification";
    }

    @Override
    public List<Argument> arguments() {
        return null;
    }
}

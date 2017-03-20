package org.nazymko.moneygraph.analytics;

import org.nazymko.moneygraph.analytics.model.support.Money;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by a.nazimko on 16.03.2017.
 */
public abstract class Report {
    private String name;
    private List<Legend> legend;

    public Report(String name, List<Legend> legend) {
        this.name = name;
        this.legend = legend;
    }

    public String getName() {
        return name;
    }

    public List<Legend> getLegend() {
        return legend;
    }

    public static class Legend {
        private Money money;
        private String title;
        private LocalDateTime time;
        private int index;

        public Legend(Money money, String title, LocalDateTime time, int index) {
            this.money = money;
            this.title = title;
            this.time = time;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Legend{" +
                    "money=" + money +
                    ", title='" + title + '\'' +
                    ", time=" + time +
                    ", index=" + index +
                    '}';
        }

        public LocalDateTime getTime() {
            return time;
        }

        public int getIndex() {
            return index;
        }

        public Money getMoney() {
            return money;
        }

        public String getTitle() {
            return title;
        }
    }

}

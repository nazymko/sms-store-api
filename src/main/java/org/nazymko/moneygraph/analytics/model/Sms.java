package org.nazymko.moneygraph.analytics.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by a.nazimko on 16.03.2017.
 */
public class Sms {

    private String body;
    private LocalDateTime time;

    public Sms(String body, LocalDateTime time) {
        this.body = body;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Sms[...]";
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getTime() {
        return time;
    }
}

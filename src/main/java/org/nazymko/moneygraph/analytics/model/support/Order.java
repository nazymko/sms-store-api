package org.nazymko.moneygraph.analytics.model.support;

import org.nazymko.moneygraph.analytics.model.Processed;
import org.nazymko.moneygraph.analytics.model.Sms;

/**
 * Created by a.nazimko on 17.03.2017.
 */
public class Order {
    Processed refference;
    Position position;

    public Order(Processed refference, Position position) {
        this.refference = refference;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Order{" +
                "refference=" + refference.getSms().getBody() +
                ", position=" + position +
                '}';
    }

    public Processed getRefference() {
        return refference;
    }

    public Position getPosition() {
        return position;
    }

    public enum Position {
        SLAVE, MASTER
    }
}

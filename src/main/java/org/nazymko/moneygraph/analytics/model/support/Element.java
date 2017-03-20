package org.nazymko.moneygraph.analytics.model.support;

/**
 * Created by a.nazimko on 17.03.2017.
 */
public class Element<T> {
    int index;
    Type type;
    T value;
    String raw;

    public Element(int index, Type type, T value, String raw) {
        this.index = index;
        this.type = type;
        this.value = value;
        this.raw = raw;
    }

    public String getRaw() {
        return raw;
    }

    public int getIndex() {
        return index;
    }

    public Type getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Element{" +
                "index=" + index +
                ", type=" + type +
                ", value=" + value +
                ", raw='" + raw + '\'' +
                '}';
    }

    public enum Type {
        CURRENCY,
        MONEY,
        DATE,
        STRING,
        OPERATION,
        DECIMAL,
        MASTER,
        SLAVE,
        INCOME,
        OUTCOME,
        BALANCE,
        FLOW
    }
}

package org.nazymko.moneygraph.analytics.model;

/**
 * Created by a.nazimko on 16.03.2017.
 */
public class Argument {
    String name;
    Type type;
    Object value;

    public Argument(String name, Type type) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public enum Type {
        STRING, NUMBER
    }

}

/*
 * This file is generated by jOOQ.
*/
package org.nazymko.moneygraph.storage.dao.autodao.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ParsedsmsDetails implements Serializable {

    private static final long serialVersionUID = 177132798;

    private Long   id;
    private String key;
    private String value;
    private String valueType;
    private Long   parsedsmsId;

    public ParsedsmsDetails() {}

    public ParsedsmsDetails(ParsedsmsDetails value) {
        this.id = value.id;
        this.key = value.key;
        this.value = value.value;
        this.valueType = value.valueType;
        this.parsedsmsId = value.parsedsmsId;
    }

    public ParsedsmsDetails(
        Long   id,
        String key,
        String value,
        String valueType,
        Long   parsedsmsId
    ) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.valueType = valueType;
        this.parsedsmsId = parsedsmsId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueType() {
        return this.valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public Long getParsedsmsId() {
        return this.parsedsmsId;
    }

    public void setParsedsmsId(Long parsedsmsId) {
        this.parsedsmsId = parsedsmsId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ParsedsmsDetails (");

        sb.append(id);
        sb.append(", ").append(key);
        sb.append(", ").append(value);
        sb.append(", ").append(valueType);
        sb.append(", ").append(parsedsmsId);

        sb.append(")");
        return sb.toString();
    }
}
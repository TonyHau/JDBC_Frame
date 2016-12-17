package com.az.ww.sqlUtil;

public class QueryObject {
    private String prepend;
    private String field;
    private String condition;
    private Object value;

    public QueryObject() {
    }

    public QueryObject(String prepend, String field, String condition, Object value) {
        this.prepend = prepend;
        this.field = field;
        this.condition = condition;
        this.value = value;
    }

    public String getPrepend() {
        return this.prepend;
    }

    public void setPrepend(String prepend) {
        this.prepend = prepend;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static boolean isValidate(QueryObject obj) {
        if (obj.getPrepend().isEmpty())
            return false;
        if (obj.getField().isEmpty())
            return false;
        if (obj.getCondition().isEmpty())
            return false;
        if (obj.getValue() == null) {
            return false;
        }
        return true;
    }
}


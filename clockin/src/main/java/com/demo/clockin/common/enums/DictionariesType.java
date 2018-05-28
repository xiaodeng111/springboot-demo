package com.demo.clockin.common.enums;

/**
 * Created by Administrator on 2017/10/13.
 */
public enum  DictionariesType implements IndexEnum<DictionariesType> {

    IDENTITY(1,"身份"),INTENT_COUNTRY(2,"意向留学国家"),BELONG_EXAM(3,"所属考试"),COMPLEXITY(4,"难易度");

    private Integer ordinal;

    private String name;

    private DictionariesType(Integer ordinal, String name) {
        this.ordinal = ordinal;
        this.name = name;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

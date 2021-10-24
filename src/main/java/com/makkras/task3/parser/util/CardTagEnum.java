package com.makkras.task3.parser.util;

public enum CardTagEnum {
    COMPACT_POST_CARD ("res:compact-post-card"),
    LOCAL_COMPACT_POST_CARD("compact-post-card"),
    LOCAL_EXTENDED_POST_CARD ("extended-post-card"),
    EXTENDED_POST_CARD ("res:extended-post-card"),
    THEME ("theme"),
    ID ("id"),
    WAS_SENT ("was-sent"),
    YEAR ("year"),
    VALUABLE ("valuable"),
    CARD_TYPE ("card-type"),
    COUNTRY ("country"),
    AUTHOR ("author"),
    COMPACT_POST_CODE ("compact-post-code");
    private String value;
    CardTagEnum(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}

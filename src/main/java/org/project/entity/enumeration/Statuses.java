package org.project.entity.enumeration;

public enum Statuses {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    CONFIRMED("Confirmed"),
    UNCONFIRMED("Unconfirmed"),
    AWAITING_CONFIRMATION("awaiting confirmation");
    private final String toString;

    private Statuses(String toString) {
        this.toString = toString;
    }


    public String toString(){
        return toString;
    }
    }


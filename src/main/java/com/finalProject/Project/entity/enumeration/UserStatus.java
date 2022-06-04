package com.finalProject.Project.entity.enumeration;

public enum UserStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    CONFIRMED("Confirmed"),
    UNCONFIRMED("Unconfirmed"),
    AWAITING_CONFIRMATION_EMAIL("awaiting confirmation email"),
    AWAITING_CONFIRMATION("awaiting confirmation");
    private final String toString;

    private UserStatus(String toString) {
        this.toString = toString;
    }


    public String toString(){
        return toString;
    }
    public Enum<UserStatus> find(String name){
        return this.find(name);
    }
    }


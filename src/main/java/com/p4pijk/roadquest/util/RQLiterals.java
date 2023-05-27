package com.p4pijk.roadquest.util;

public enum RQLiterals {

//    ADMIN PAGE
    REDIRECT_ADMIN("redirect:/admin"),
    ADMIN_PAGE("admin/admin"),
    CAR_PAGE("admin/car-page"),
    CREATE_MANAGER_PAGE("admin/manager-page"),


//    MANAGER PAGE
    REDIRECT_MANAGER("redirect:/manager"),


//    USER PAGE
    REDIRECT_USER("redirect:/profile");


    private final String value;

    RQLiterals(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}

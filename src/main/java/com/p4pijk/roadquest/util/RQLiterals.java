package com.p4pijk.roadquest.util;

public enum RQLiterals {

//    ADMIN PAGE
    REDIRECT_ADMIN("redirect:/admin"),
    ADMIN_PAGE("admin/admin"),
    CAR_PAGE("admin/car-page"),
    CREATE_MANAGER_PAGE("admin/manager-page"),


//    MANAGER PAGE
    REDIRECT_MANAGER("redirect:/manager"),
    MANAGER_PAGE("manager/manager"),
    INSPECT_ORDER("manager/inspect-order"),


//    USER PAGE
    REDIRECT_USER("redirect:/profile"),
    USER_PROFILE("user/profile"),
    RENT_PAGE("user/rent-page"),
    FILL_APPLICATION_PAGE("user/fill-application"),
    ABOUT_PAGE("user/about-page");


    private final String value;

    RQLiterals(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}

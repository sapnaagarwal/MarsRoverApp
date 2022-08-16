package com.example.MarsRoverApp.model;

/**
 * Enum for direction
 *
 * @author Sapna Agarwal
 */
public enum DirectionEnum {

    N("North"),
    S("South"),
    E("East"),
    W("West");

    public final String value;

    private DirectionEnum(String value){
        this.value = value;
    }
}

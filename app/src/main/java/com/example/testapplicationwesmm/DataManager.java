package com.example.testapplicationwesmm;

public class DataManager {
    private static boolean organic = false;
    private static boolean valueIsSet = false;

    public static void setOrganic(boolean v){
        organic = v;
        valueIsSet = true;
    }
    public static boolean isOrganic(){
        return organic;
    }
    public static boolean isValueIsSet(){
        return valueIsSet;
    }
}

package com.zhang.coo.utils;

/**
 * Created by aa on 2018/10/21.
 */
public class TimeUtils {

    public static long getVersion(){

        return System.currentTimeMillis();
    }

    public static int getCurrentTime(){
        return (int)(System.currentTimeMillis() / 1000);
    }


    public static void main(String[] args){

        System.out.println( getVersion() );
        System.out.println( getCurrentTime() );
    }
}

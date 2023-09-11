package com.example.travelplanner.data;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPrefs  {
    private static final String PREF_NAME = "MyPrefs";

    public static boolean getIntroCompletedStatus(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean("intro_completed", false);
    }

    public static void setIntroCompletedStatus(Context context, boolean completed) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("intro_completed", completed);
        editor.apply();
    }
    public static void setSignUpCompletedStatus(Context context, boolean completed) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("signup_completed", completed);
        editor.apply();
    }
    public static boolean getSignUpCompletedStatus(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean("signup_completed", false);
    }
    public static void setPassword(Context context,String userPassword){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userPassword", userPassword); // userPassword is the password provided by the user
        editor.apply();
    }
    public static String getPassword(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", context.MODE_PRIVATE);
        String savedPassword = sharedPreferences.getString("userPassword", null);
        return savedPassword;
    }
    public static void deletePassword(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("userPassword");
        editor.apply();
    }
}
//yes no
//yes yes
//no no
//

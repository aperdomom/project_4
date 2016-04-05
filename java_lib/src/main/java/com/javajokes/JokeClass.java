package com.javajokes;

import java.util.Random;

public class JokeClass {
    public static String[] jokes = {
            "What do you do with epileptic lettuce?, You make a seizure salad!",
            "Why did the police officer smell?, Because he was on duty",
            "Why did the skeleton go to the party alone?, He had no body to go with him!"};

    public static String getJoke(){
        Random rand = new Random();

        int  n = rand.nextInt(3);
        return jokes[n];
    }
}

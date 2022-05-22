package com.academy.techcenture.end2end;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CommonUtils {



    public static String randomZipCode(){
        return new Faker().address().zipCode().substring(0, 5);
    }

    public static String randomPhoneNumber(){
        return String.format("(%03d) %03d-%04d",
                (int) Math.floor(999*Math.random()),
                (int) Math.floor(999*Math.random()),
                (int) Math.floor(9999*Math.random()));
    }

    public static String generateRandomString(int limit){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toLowerCase();
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < limit) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }


    public static String randomCompanyName(){
        return new Faker().company().name();
    }

    public static String randomState(){
        return new Faker().address().state();
    }

    public static String randomCity(){
        return new Faker().address().cityName();
    }

    public static String randomStrAddress(){
        return new Faker().address().streetAddress();
    }

    public static int randomNumber(int from, int to){
        return (int)(Math.random() * (to - from  + 1 ) + from);
    }

    public static String randomEmail(){
        Faker faker = new Faker();
        String lastName = faker.name().lastName(); //random lastname
        String firstName = faker.name().firstName();
        String[] domain = {"gmail","yahoo","icloud","hotmail"};
        String email = lastName + "." + firstName + "@" + domain[(int) (Math.random() * (4))] + ".com";
        return email.toLowerCase(Locale.ROOT);
    }

    public static String randomDOB18OrAbove(){
        LocalDate startDate = LocalDate.of(1950, 1, 1); //start date
        long start = startDate.toEpochDay();
        LocalDate endDate = LocalDate.of(LocalDate.now().getYear()-18, 1, 1); //end date
        long end = endDate.toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
        return LocalDate.ofEpochDay(randomEpochDay).toString();
    }


    public static void main(String[] args) {


        System.out.println( new Faker().address().zipCode().substring(0, 5));
    }

}

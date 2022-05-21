package com.academy.techcenture.end2end;

import com.github.javafaker.Faker;

import java.util.Locale;

public class CommonUtils {





    public static String randomEmail(){
        Faker faker = new Faker();
        String lastName = faker.name().lastName(); //random lastname
        String firstName = faker.name().firstName();
        String[] domain = {"gmail","yahoo","icloud","hotmail"};
        String email = lastName + "." + firstName + "@" + domain[(int) (Math.random() * (4))] + ".com";
        return email.toLowerCase(Locale.ROOT);
    }

    public static void main(String[] args) {


        String email = randomEmail();

        int atSign = email.indexOf("@");
        String[] fullName = email.substring(0, atSign).split("\\.");
        String firstName = fullName[1].substring(0,1).toUpperCase() + fullName[1].substring(1);
        String lastName = fullName[0].substring(0,1).toUpperCase() + fullName[0].substring(1);

        System.out.println(firstName);
        System.out.println(lastName);


    }

}

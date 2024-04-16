package sample;

import com.salvadormontiel.openai.Client;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
            System.out.println(Client.getGreeting("John " + i));
        }
    }
}

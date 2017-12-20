package com.example.edmardiaz.scuoladeibambini;

import java.util.ArrayList;

/**
 * Created by EdmarDiaz on 12/21/2017.
 */

public class Phrases {
    ArrayList<String> it_phrases = new ArrayList<>();
    ArrayList<String> en_phrases = new ArrayList<>();

    public Phrases() {
        en_phrases.add("Good Morning");
        en_phrases.add("Good Afternoon");
        en_phrases.add("How are you?");
        en_phrases.add("What is your name?");
        en_phrases.add("Nice to meet you");
        en_phrases.add("Thank you");
        en_phrases.add("You're welcome");
        en_phrases.add("My name is...");
        en_phrases.add("How old are you?");
        en_phrases.add("See you later");

        it_phrases.add("Buongiorno");
        it_phrases.add("Buon Pomeriggio");
        it_phrases.add("Come stai?");
        it_phrases.add("Come ti chiami?");
        it_phrases.add("Piacere di conoscerti");
        it_phrases.add("Grazie");
        it_phrases.add("Prego");
        it_phrases.add("Mi chiamo");
        it_phrases.add("Quanti anni hai?");
        it_phrases.add("Arrivederci");
    }

    public ArrayList<String> getIt_phrases() {
        return it_phrases;
    }

    public ArrayList<String> getEn_phrases() {
        return en_phrases;
    }
}

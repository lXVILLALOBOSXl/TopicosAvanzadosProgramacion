package run;

import utilities.Thread1;
import utilities.Thread2;

public class Main {
    public static void main(String[] args) {
        Thread1 mayus = new Thread1();
        Thread2 minus = new Thread2();
        mayus.start();
        minus.start();
    }
}
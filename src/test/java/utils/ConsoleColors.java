package utils;

public class ConsoleColors {

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";


    public static void printColorGreenMessage(String message) {
        System.out.println(GREEN + message + GREEN);
    }

    public static void printColorRedMessage(String message){
        System.out.println(RED + message + RED);
    }

    public static void printColorBlackMessage(String message){
        System.out.println(BLACK + message + BLACK);
    }

    public static void printColorResetMessage(String message){
        System.out.println(RESET + message + RESET);
    }

}

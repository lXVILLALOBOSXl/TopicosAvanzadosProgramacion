import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to my program " + input("Type your name: "));
    }

    private static String input(String phrase){
        System.out.println(phrase);
        return scanner.nextLine();
    }
}

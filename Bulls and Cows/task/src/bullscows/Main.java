package bullscows;
import java.util.*;

public class Main {

    static ArrayList<String> randString;
    static String secretCode;
    static int cows;
    static int bulls;
    static int codeLength;
    static int numOfSymbols;
    static String[] codeChoices = new String[]{"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    static String[] codeAfterChoice;

    public static void symbolAltering(int numOfSymbols) {
        codeAfterChoice = new String[numOfSymbols];
        for (int i = 0; i < numOfSymbols; i++) {
            codeAfterChoice[i] = codeChoices[i];
//                System.out.println(codeAfterChoice[i]);
        }
    }

    public static void generatingCode(int guess) {
        randString = new ArrayList<>();
        Random rand = new Random();
        while (randString.size() != guess) {
            int randomIndex = rand.nextInt(codeAfterChoice.length);
            if (!randString.contains(codeAfterChoice[randomIndex])) randString.add(codeAfterChoice[randomIndex]);
        }
        for (int i = 0; i < randString.size(); i++) {
            if (Objects.equals(randString.get(0), "0")) {
                Collections.reverse(randString);
            }
//                System.out.print(randString.get(i));
        }

    }



    public static void convertListToString2() {
        StringBuilder sb = new StringBuilder();
        for (int i = randString.size() - 1; i >= 0; i--) {
            String cod = randString.get(i);
            sb.append(cod);
        }
        sb.reverse();
        secretCode = sb.toString();
    }

    public static void letsPlay(String guess) {
        convertListToString2();
        String[] secretCodeSplit = secretCode.split("");
        String[] guessSplit = guess.split("");
        cows = 0;
        bulls = 0;

        for (int i = 0; i < codeLength; i++) {
            if (secretCodeSplit[i].equals(guessSplit[i])) {
                bulls++;
            } else if (secretCode.contains(guessSplit[i])) {
                cows++;
            }
        }
        if (bulls > 0 && cows > 0) {
            System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s)."); //  + "The secret code is " + secretCode
        } else if (bulls > 0) {
            System.out.println("Grade: " + bulls + " bull(s). "); //  + "The secret code is " + secretCode
        } else if (cows > 0) {
            System.out.println("Grade: " + cows + " cow(s). "); //  + "The secret code is " + secretCode
        } else
            System.out.println("Grade: None. "); //+ "The secret code is " + secretCode
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the secret code's length: ");
        try {
            codeLength = input.nextInt();
        } catch (RuntimeException e) {
            System.out.println("Error. Letters used when only nums can be entered");
            System.exit(1);
        }
        if (codeLength <= 0) {
            System.out.println("Error. Code length must be greater than 0");
            System.exit(1);
//            codeLength = input.nextInt();
        }
        input.nextLine();
        int limit = 36;
        System.out.println("Input the possible number of symbols for the code: ");
        numOfSymbols = input.nextInt();
        input.nextLine();
        if (numOfSymbols < codeLength) {
            System.out.println("Error. Must enter number equal or greater than " + codeLength);
            System.exit(1);
//            numOfSymbols = input.nextInt();
        }
        System.out.println();
        try {
            symbolAltering(numOfSymbols);
        } catch (RuntimeException e) {
            System.out.println("Error. Index out of bounds.");
            System.exit(1);
        }
        if (codeLength > limit) {
            System.out.println("Error: can't generate a secret number with a length of " + codeLength + " because there aren't enough unique digits.");
        } else {
            generatingCode(codeLength);
        }
        System.out.println();
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < codeLength; k++) {
            sb.append("*");
        }

        if (codeAfterChoice.length <= 10) {
            System.out.println("The secret is prepared: " + sb +  " (0-" + codeAfterChoice[codeAfterChoice.length - 1] + ")");
        } else {
            System.out.println("The secret is prepared: " + sb + " (0-9, a-" + codeAfterChoice[codeAfterChoice.length - 1] + ")");
        }
        System.out.println("Ok let's start the game! ");
        int turn = 1;
        while (bulls != codeLength) {
            System.out.println("Turn " + turn);
            turn++;
            System.out.println("Enter your guess: ");
            String guessing1 = input.nextLine();
            if (guessing1.length() != codeLength) {
                System.out.println("Error! Guess not the same length try again.");
                guessing1 = input.nextLine();
            }
            System.out.println(guessing1);
            letsPlay(guessing1);
        }
    }
}

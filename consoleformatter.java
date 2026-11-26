package util;

public class consoleformatter {
    // screen width for centering output
    public static int boxwidth = 80;

    // adds spaces on the left so text sits in the middle
    public static String center(String text) {
        int spaces = (boxwidth - text.length()) / 2;
        if (spaces <= 0) {
            return text;
        }
        String leftpad = "";
        for (int i = 0; i < spaces; i++) {
            leftpad = leftpad + " ";
        }
        return leftpad + text;
    }

    // pads a word with spaces on the right so columns line up
    public static String pad(String text, int size) {
        String out = text;
        while (out.length() < size) {
            out = out + " ";
        }
        return out;
    }

    public static void line() {
        System.out.println(center("+------------------------------------------------------------------+"));
    }

    public static void doubleline() {
        System.out.println(center("===================================================================="));
    }

    public static void header(String heading) {
        System.out.println();
        doubleline();
        System.out.println(center(heading.toUpperCase()));
        doubleline();
    }

    public static void title(String text) {
        System.out.println();
        line();
        System.out.println(center(text.toUpperCase()));
        line();
    }

    public static void printcentered(String text) {
        System.out.println(center(text));
    }

    public static void prompt(String text) {
        System.out.print("                    " + text);
    }

    public static void success(String msg) {
        System.out.println(center("[OK] " + msg));
    }

    public static void error(String msg) {
        System.out.println(center("[!] " + msg));
    }
}

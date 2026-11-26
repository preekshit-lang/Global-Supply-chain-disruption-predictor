package util;

public class consoleformatter {

    public static String pad(String text, int size) {
        String out = text;
        while (out.length() < size) {
            out = out + " ";
        }
        return out;
    }

    public static void line() {
        System.out.println("+--------------------------------------------------------------------+");
    }

    public static void doubleline() {
        System.out.println("======================================================================");
    }

    public static void header(String heading) {
        System.out.println();
        doubleline();
        System.out.println("|  " + pad(heading.toUpperCase(), 64) + "  |");
        doubleline();
    }

    public static void title(String text) {
        System.out.println();
        line();
        System.out.println("|  " + pad(text.toUpperCase(), 64) + "  |");
        line();
    }

    public static void row(String text) {
        System.out.println("|  " + pad(text, 64) + "  |");
    }

    public static void prompt(String text) {
        System.out.print("  " + text);
    }

    public static void success(String msg) {
        System.out.println("  [OK] " + msg);
    }

    public static void error(String msg) {
        System.out.println("  [!]  " + msg);
    }
}

package util;

public class impactcalculator {

    // calculates extra delay cost (5% of base route cost per delayed day)
    public static double calculateextracost(double basecost, int delaydays) {
        return basecost * 0.05 * delaydays;
    }

    // calculates how many factory units are lost during the delay
    public static int calculatelostunits(int dailyoutput, int delaydays) {
        return dailyoutput * delaydays;
    }
}

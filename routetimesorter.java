package service;

import model.shippingroute;
import java.util.Comparator;

public class routetimesorter implements Comparator<shippingroute> {
    public int compare(shippingroute r1, shippingroute r2) {
        if (r1.getdays() > r2.getdays()) {
            return 1;
        } else if (r1.getdays() < r2.getdays()) {
            return -1;
        } else {
            return 0;
        }
    }
}

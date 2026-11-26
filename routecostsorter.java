package service;

import model.shippingroute;
import java.util.Comparator;

public class routecostsorter implements Comparator<shippingroute> {
    public int compare(shippingroute r1, shippingroute r2) {
        if (r1.getcost() > r2.getcost()) {
            return 1;
        } else if (r1.getcost() < r2.getcost()) {
            return -1;
        } else {
            return 0;
        }
    }
}

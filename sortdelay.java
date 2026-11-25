package service;

import model.shipment;
import java.util.Comparator;

public class sortdelay implements Comparator<shipment> {
    public int compare(shipment s1, shipment s2) {
        if (s2.getdelaydays() > s1.getdelaydays()) {
            return 1;
        } else if (s2.getdelaydays() < s1.getdelaydays()) {
            return -1;
        } else {
            return 0;
        }
    }
}

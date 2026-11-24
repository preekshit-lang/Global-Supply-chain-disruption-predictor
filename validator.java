package util;

import model.shipment;

public class validator {
    public static boolean isnumber(String text) {
        if (text == null || text.length() == 0) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean isvalidstatus(String status) {
        for (int i = 0; i < shipment.statuslist.length; i++) {
            if (shipment.statuslist[i].equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }
}

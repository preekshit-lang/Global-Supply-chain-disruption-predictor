package service;

import model.shippingroute;
import java.util.ArrayList;
import java.util.Collections;

public class routeplanner {
    private ArrayList<shippingroute> routes = new ArrayList<>();

    public void add(shippingroute r) {
        routes.add(r);
    }

    public shippingroute get(String id) {
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).getid().equalsIgnoreCase(id)) {
                return routes.get(i);
            }
        }
        return null;
    }

    public void showsorted(boolean bycost) {
        ArrayList<shippingroute> copy = new ArrayList<>(routes);
        if (bycost) {
            Collections.sort(copy, new routecostsorter());
        } else {
            Collections.sort(copy, new routetimesorter());
        }
        for (int i = 0; i < copy.size(); i++) {
            System.out.println(copy.get(i));
        }
    }
}

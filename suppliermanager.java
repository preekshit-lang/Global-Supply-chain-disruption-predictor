package service;

import model.supplier;
import java.util.HashMap;

public class suppliermanager {
    private HashMap<String, supplier> suppliers = new HashMap<>();

    public void add(supplier s) {
        suppliers.put(s.getid(), s);
    }

    public supplier get(String id) {
        return suppliers.get(id);
    }

    public void showall() {
        for (supplier s : suppliers.values()) {
            System.out.println(s);
        }
    }
}

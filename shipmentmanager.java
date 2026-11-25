package service;

import model.shipment;
import model.supplier;
import java.util.ArrayList;
import java.util.Collections;

public class shipmentmanager {
    private ArrayList<shipment> shipments = new ArrayList<>();

    public void add(shipment s, suppliermanager supmgr, boolean printmsg) {
        if (s.getquantity() <= 0) {
            System.out.println("error: quantity must be greater than 0");
            return;
        }
        supplier sup = supmgr.get(s.getsupplierid());
        if (sup == null) {
            System.out.println("error: supplier id not found");
            return;
        }
        if (!sup.isactive()) {
            System.out.println("error: supplier is inactive");
            return;
        }
        shipments.add(s);
        if (printmsg) {
            System.out.println("shipment added successfully");
        }
    }

    public shipment find(String id) {
        for (int i = 0; i < shipments.size(); i++) {
            if (shipments.get(i).getid().equalsIgnoreCase(id)) {
                return shipments.get(i);
            }
        }
        return null;
    }

    public ArrayList<shipment> getall() {
        return shipments;
    }

    public void showall() {
        for (int i = 0; i < shipments.size(); i++) {
            System.out.println(shipments.get(i));
        }
    }

    public void showbydelay() {
        ArrayList<shipment> copy = new ArrayList<>(shipments);
        Collections.sort(copy, new sortdelay());
        for (int i = 0; i < copy.size(); i++) {
            System.out.println(copy.get(i));
        }
    }
}

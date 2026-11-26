package service;

import model.*;
import util.consoleformatter;
import util.impactcalculator;
import java.util.ArrayList;

public class disruptionsimulator {

    public void runsenario(disruptionevent event, ArrayList<shipment> shipments,
                           ArrayList<finishedproduct> products, ArrayList<customerorder> orders,
                           routeplanner planner, ArrayList<delayalert> alerts,
                           ArrayList<delaycostrecord> costrecords) {
        consoleformatter.title("simulation results");
        boolean anydelayed = false;

        for (shipment s : shipments) {
            boolean matchid = s.getid().equalsIgnoreCase(event.gettargetid());
            boolean matchroute = s.getrouteid().equalsIgnoreCase(event.gettargetid());
            boolean matchsup = s.getsupplierid().equalsIgnoreCase(event.gettargetid());

            if (matchid || matchroute || matchsup) {
                s.adddelay(event.getextradays(), event.getname());
            }

            if (s.getstatus().equals(shipment.delayed)) {
                anydelayed = true;
                System.out.println("  " + s);

                shippingroute r = planner.get(s.getrouteid());
                double basecost = 100000;
                if (r != null) {
                    basecost = r.getcost();
                }

                double extra = impactcalculator.calculateextracost(basecost, s.getdelaydays());

                costrecords.add(new delaycostrecord("cst-" + s.getid(), "delay penalty", s.getid(), basecost, extra));
                alerts.add(new delayalert("alt-" + s.getid(), event.getname(), s.getid(),
                        "delayed by " + s.getdelaydays() + " days | extra cost: rs. " + extra));

                System.out.println("  -> extra delay cost : rs. " + extra);
                checkaffectedproducts(products, orders, s.getname(), s.getdelaydays());
                consoleformatter.line();
            }
        }

        if (!anydelayed) {
            System.out.println("  no shipments were affected by this event");
            consoleformatter.line();
        }
    }

    private void checkaffectedproducts(ArrayList<finishedproduct> products, ArrayList<customerorder> orders,
                                       String material, int delaydays) {
        for (finishedproduct p : products) {
            if (p.getrequiredmaterial().equalsIgnoreCase(material)) {
                int lost = impactcalculator.calculatelostunits(p.getdailyoutput(), delaydays);
                System.out.println("  -> affected product : " + p.getname() + " (loss: " + lost + " units)");

                for (customerorder o : orders) {
                    if (o.getproductid().equalsIgnoreCase(p.getid())) {
                        System.out.println("  -> at-risk order    : " + o.getname() + " (" + o.getunits() + " units)");
                    }
                }
            }
        }
    }
}

import model.*;
import service.*;
import util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class main {
    // service objects
    private static supplierdirectory directory = new supplierdirectory();
    private static shipmenttracker tracker = new shipmenttracker();
    private static routeplanner planner = new routeplanner();
    private static disruptionsimulator simulator = new disruptionsimulator();

    // lists to hold records in memory
    private static ArrayList<rawmaterial> materials = new ArrayList<>();
    private static ArrayList<warehouse> warehouses = new ArrayList<>();
    private static ArrayList<shippingport> ports = new ArrayList<>();
    private static ArrayList<cargocarrier> carriers = new ArrayList<>();
    private static ArrayList<finishedproduct> products = new ArrayList<>();
    private static ArrayList<customerorder> orders = new ArrayList<>();
    private static ArrayList<warehousestock> stocks = new ArrayList<>();
    private static ArrayList<delayalert> alerts = new ArrayList<>();
    private static ArrayList<delaycostrecord> costrecords = new ArrayList<>();

    public static void main(String[] args) {
        loaddata();
        Scanner sc = new Scanner(System.in);

        while (true) {
            consoleformatter.header("global supply chain disruption predictor");
            consoleformatter.printcentered("[1]  view all shipments                       ");
            consoleformatter.printcentered("[2]  register new shipment                    ");
            consoleformatter.printcentered("[3]  update shipment status                   ");
            consoleformatter.printcentered("[4]  simulate disruption event                ");
            consoleformatter.printcentered("[5]  compare shipping routes                  ");
            consoleformatter.printcentered("[6]  view delay alerts & extra costs          ");
            consoleformatter.printcentered("[7]  view suppliers, ports, warehouses & stock");
            consoleformatter.printcentered("[0]  exit program                             ");
            consoleformatter.line();
            consoleformatter.prompt("select option > ");

            String choice = sc.nextLine();

            if (choice.equals("1")) {
                consoleformatter.title("active shipments overview");
                showshipments();

            } else if (choice.equals("2")) {
                consoleformatter.title("register new shipment");
                consoleformatter.prompt("shipment id (e.g. shp-103)          : ");
                String id = sc.nextLine();
                consoleformatter.prompt("material name                       : ");
                String mat = sc.nextLine();
                consoleformatter.prompt("supplier id (sup-01 or sup-02)      : ");
                String supid = sc.nextLine();
                consoleformatter.prompt("quantity                            : ");
                String qtystr = sc.nextLine();
                consoleformatter.prompt("route id (rte-a, rte-b, or rte-c)   : ");
                String rteid = sc.nextLine();

                if (!inputvalidator.isnumber(qtystr)) {
                    consoleformatter.error("quantity must be a valid number");
                } else {
                    int qty = Integer.parseInt(qtystr);
                    int oldsize = tracker.getall().size();
                    tracker.add(new shipment(id, mat, supid, qty, rteid, 15), directory, false);

                    if (tracker.getall().size() > oldsize) {
                        consoleformatter.success("shipment " + id + " registered");
                    }
                }

            } else if (choice.equals("3")) {
                consoleformatter.title("update shipment status");
                consoleformatter.prompt("enter shipment id                   : ");
                String id = sc.nextLine();
                shipment s = tracker.find(id);

                if (s == null) {
                    consoleformatter.error("shipment not found");
                } else {
                    consoleformatter.prompt("new status (ordered/in_transit/delayed/delivered): ");
                    String st = sc.nextLine();
                    if (inputvalidator.isvalidstatus(st)) {
                        s.updatestatus(st);
                        consoleformatter.success("status updated to " + st.toLowerCase());
                    } else {
                        consoleformatter.error("invalid status");
                    }
                }

            } else if (choice.equals("4")) {
                consoleformatter.title("disruption impact simulator");
                consoleformatter.prompt("disruption reason (e.g. storm)      : ");
                String reason = sc.nextLine();
                consoleformatter.prompt("target id (shp-101 / rte-a / sup-01): ");
                String target = sc.nextLine();
                consoleformatter.prompt("delay days                          : ");
                String daystr = sc.nextLine();

                if (!inputvalidator.isnumber(daystr)) {
                    consoleformatter.error("delay days must be a valid number");
                } else {
                    int days = Integer.parseInt(daystr);
                    disruptionevent event = new disruptionevent("dis-01", reason, target, days);
                    simulator.runsenario(event, tracker.getall(), products, orders, planner, alerts, costrecords);
                }

            } else if (choice.equals("5")) {
                consoleformatter.title("compare shipping routes");
                consoleformatter.prompt("sort by (1 = fastest days, 2 = lowest cost): ");
                String opt = sc.nextLine();
                System.out.println();
                showroutes(opt.equals("2"));

            } else if (choice.equals("6")) {
                consoleformatter.title("alerts & delay cost records");
                if (alerts.size() == 0) {
                    consoleformatter.printcentered("no alerts yet - run option 4 to test a delay");
                } else {
                    for (delayalert a : alerts) {
                        consoleformatter.printcentered(a.toString());
                    }
                    consoleformatter.line();
                    for (delaycostrecord c : costrecords) {
                        consoleformatter.printcentered(c.toString());
                    }
                }

            } else if (choice.equals("7")) {
                consoleformatter.title("suppliers, ports, carriers & stock");
                consoleformatter.printcentered(directory.get("sup-01").toString());
                consoleformatter.printcentered(directory.get("sup-02").toString());
                consoleformatter.line();
                for (shippingport p : ports) {
                    consoleformatter.printcentered(p.toString());
                }
                for (cargocarrier c : carriers) {
                    consoleformatter.printcentered(c.toString());
                }
                consoleformatter.line();
                for (warehouse w : warehouses) {
                    consoleformatter.printcentered(w.toString());
                }
                for (warehousestock ws : stocks) {
                    consoleformatter.printcentered(ws.toString());
                }

            } else if (choice.equals("0")) {
                consoleformatter.doubleline();
                consoleformatter.printcentered("program closed. goodbye!");
                consoleformatter.doubleline();
                break;

            } else {
                consoleformatter.error("invalid choice, enter 0 to 7");
            }
        }
    }

    // prints shipments in neat columns
    private static void showshipments() {
        String header = consoleformatter.pad("id", 9) + "| "
                + consoleformatter.pad("material", 13) + "| "
                + consoleformatter.pad("qty", 6) + "| "
                + consoleformatter.pad("route", 7) + "| "
                + consoleformatter.pad("status", 11) + "| total days";
        consoleformatter.printcentered(header);
        consoleformatter.line();

        for (shipment s : tracker.getall()) {
            String row = consoleformatter.pad(s.getid(), 9) + "| "
                    + consoleformatter.pad(s.getname(), 13) + "| "
                    + consoleformatter.pad("" + s.getquantity(), 6) + "| "
                    + consoleformatter.pad(s.getrouteid(), 7) + "| "
                    + consoleformatter.pad(s.getstatus(), 11) + "| "
                    + s.gettotaldays() + " (+" + s.getdelaydays() + " delay)";
            consoleformatter.printcentered(row);
        }
    }

    // sorts and prints routes by days or cost
    private static void showroutes(boolean sortbycost) {
        ArrayList<shippingroute> list = new ArrayList<>();
        list.add(planner.get("rte-a"));
        list.add(planner.get("rte-b"));
        list.add(planner.get("rte-c"));

        if (sortbycost) {
            Collections.sort(list, new routecostsorter());
        } else {
            Collections.sort(list, new routetimesorter());
        }

        String header = consoleformatter.pad("id", 8) + "| "
                + consoleformatter.pad("route name", 20) + "| "
                + consoleformatter.pad("path", 17) + "| "
                + consoleformatter.pad("days", 6) + "| cost";
        consoleformatter.printcentered(header);
        consoleformatter.line();

        for (shippingroute r : list) {
            String path = r.getorigin() + " -> " + r.getdestination();
            String row = consoleformatter.pad(r.getid(), 8) + "| "
                    + consoleformatter.pad(r.getname(), 20) + "| "
                    + consoleformatter.pad(path, 17) + "| "
                    + consoleformatter.pad("" + r.getdays(), 6) + "| rs. " + (int) r.getcost();
            consoleformatter.printcentered(row);
        }
    }

    // loads sample records when the app starts
    private static void loaddata() {
        directory.add(new supplier("sup-01", "nippon motors", "japan", true));
        directory.add(new supplier("sup-02", "shenzhen chips", "china", true));

        materials.add(new rawmaterial("mat-01", "car engines", "auto", 85000));
        materials.add(new rawmaterial("mat-02", "microchips", "electronics", 4500));

        warehouses.add(new warehouse("wh-01", "london central hub", "london", 10000));
        ports.add(new shippingport("prt-01", "port of tokyo", "japan", "open"));
        ports.add(new shippingport("prt-02", "port of felixstowe", "uk", "busy"));
        carriers.add(new cargocarrier("car-01", "pacific cargo line", "sea"));

        stocks.add(new warehousestock("stk-01", "car engines", "wh-01", 120));
        stocks.add(new warehousestock("stk-02", "microchips", "wh-01", 450));

        products.add(new finishedproduct("prd-01", "apex sedan car", "car engines", 20));
        products.add(new finishedproduct("prd-02", "apex electric car", "microchips", 35));

        orders.add(new customerorder("ord-01", "uk dealer batch", "prd-01", 150, 25));
        orders.add(new customerorder("ord-02", "eu fleet batch", "prd-02", 300, 30));

        planner.add(new shippingroute("rte-a", "suez sea route", "tokyo", "london", 15, 200000));
        planner.add(new shippingroute("rte-b", "cape freight route", "tokyo", "london", 19, 150000));
        planner.add(new shippingroute("rte-c", "air express route", "tokyo", "london", 12, 280000));

        tracker.add(new shipment("shp-101", "car engines", "sup-01", 500, "rte-a", 15), directory, false);
        tracker.add(new shipment("shp-102", "microchips", "sup-02", 2000, "rte-b", 19), directory, false);
    }
}

package model;

public class shippingroute extends baseentity implements Comparable<shippingroute> {
    private String origin;
    private String destination;
    private int days;
    private double cost;

    public shippingroute(String id, String name, String origin, String destination, int days, double cost) {
        super(id, name);
        this.origin = origin;
        this.destination = destination;
        this.days = days;
        this.cost = cost;
    }

    public String getorigin() { return origin; }
    public String getdestination() { return destination; }
    public int getdays() { return days; }
    public double getcost() { return cost; }

    public int compareTo(shippingroute other) {
        if (this.days > other.days) {
            return 1;
        } else if (this.days < other.days) {
            return -1;
        } else {
            return 0;
        }
    }

    public String toString() {
        return "[" + id + "] " + name + " (" + origin + " to " + destination + ") | "
                + days + " days | rs. " + cost;
    }
}

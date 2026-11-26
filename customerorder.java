package model;

public class customerorder extends baseentity {
    private String productid;
    private int units;
    private int duedays;

    public customerorder(String id, String name, String productid, int units, int duedays) {
        super(id, name);
        this.productid = productid;
        this.units = units;
        this.duedays = duedays;
    }

    public String getproductid() {
        return productid;
    }

    public int getunits() {
        return units;
    }

    public int getduedays() {
        return duedays;
    }

    public String toString() {
        return "[" + id + "] " + name + " | product: " + productid + " | units: " + units + " | due in: " + duedays + " days";
    }
}

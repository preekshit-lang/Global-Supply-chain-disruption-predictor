package model;

public class warehousestock extends baseentity {
    private String warehouseid;
    private int availableunits;

    public warehousestock(String id, String name, String warehouseid, int availableunits) {
        super(id, name);
        this.warehouseid = warehouseid;
        this.availableunits = availableunits;
    }

    public String getwarehouseid() {
        return warehouseid;
    }

    public int getavailableunits() {
        return availableunits;
    }

    public String toString() {
        return "[" + id + "] " + name + " | warehouse: " + warehouseid + " | stock: " + availableunits + " units";
    }
}

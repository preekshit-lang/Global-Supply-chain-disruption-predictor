package model;

public class material extends item {
    private String category;
    private double unitcost;

    public material(String id, String name, String category, double unitcost) {
        super(id, name);
        this.category = category;
        this.unitcost = unitcost;
    }

    public String getcategory() {
        return category;
    }

    public double getunitcost() {
        return unitcost;
    }

    public String toString() {
        return "[" + id + "] " + name + " (" + category + ") | rs. " + unitcost;
    }
}

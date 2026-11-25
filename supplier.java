package model;

public class supplier extends item {
    private String country;
    private boolean active;

    public supplier(String id, String name, String country, boolean active) {
        super(id, name);
        this.country = country;
        this.active = active;
    }

    public String getcountry() {
        return country;
    }

    public boolean isactive() {
        return active;
    }

    public String toString() {
        return "[" + id + "] " + name + " (" + country + ") | active: " + active;
    }
}

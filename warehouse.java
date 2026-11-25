package model;

public class warehouse extends item {
    private String city;
    private int capacity;

    public warehouse(String id, String name, String city, int capacity) {
        super(id, name);
        this.city = city;
        this.capacity = capacity;
    }

    public String getcity() {
        return city;
    }

    public int getcapacity() {
        return capacity;
    }

    public String toString() {
        return "[" + id + "] " + name + " - " + city + " (capacity: " + capacity + ")";
    }
}

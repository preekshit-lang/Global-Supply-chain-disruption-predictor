package model;

public class port extends item {
    private String country;
    private String status;

    public port(String id, String name, String country, String status) {
        super(id, name);
        this.country = country;
        this.status = status;
    }

    public String getcountry() {
        return country;
    }

    public String getstatus() {
        return status;
    }

    public String toString() {
        return "[" + id + "] " + name + " (" + country + ") | status: " + status;
    }
}

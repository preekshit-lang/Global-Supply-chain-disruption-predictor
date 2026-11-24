package model;

public class alert extends item {
    private String shipmentid;
    private String message;

    public alert(String id, String name, String shipmentid, String message) {
        super(id, name);
        this.shipmentid = shipmentid;
        this.message = message;
    }

    public String getshipmentid() { return shipmentid; }
    public String getmessage() { return message; }

    public String toString() {
        return "[alert: " + id + "] shipment " + shipmentid + " -> " + message;
    }
}

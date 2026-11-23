package model;

public class shipment extends item {
    public static final String ordered = "ordered";
    public static final String in_transit = "in_transit";
    public static final String delayed = "delayed";
    public static final String delivered = "delivered";
    public static final String[] statuslist = {ordered, in_transit, delayed, delivered};

    private String supplierid;
    private int quantity;
    private String routeid;
    private String status;
    private int expecteddays;
    private int delaydays;
    private String delayreason;

    public shipment(String id, String material, String supplierid, int quantity, String routeid, int expecteddays) {
        super(id, material);
        this.supplierid = supplierid;
        this.quantity = quantity;
        this.routeid = routeid;
        this.status = ordered;
        this.expecteddays = expecteddays;
        this.delaydays = 0;
        this.delayreason = "none";
    }

    public void updatestatus(String newstatus) {
        this.status = newstatus.toLowerCase();
    }

    public void adddelay(int extradays, String reason) {
        this.delaydays = this.delaydays + extradays;
        this.status = delayed;
        this.delayreason = reason;
    }

    public String getsupplierid() { return supplierid; }
    public int getquantity() { return quantity; }
    public String getrouteid() { return routeid; }
    public String getstatus() { return status; }
    public int getexpecteddays() { return expecteddays; }
    public int getdelaydays() { return delaydays; }
    public String getdelayreason() { return delayreason; }
    public int gettotaldays() { return expecteddays + delaydays; }

    public String toString() {
        return "[" + id + "] material: " + name + " | qty: " + quantity
                + " | route: " + routeid + " | status: " + status
                + " | total days: " + gettotaldays()
                + " (delay: " + delaydays + " days, reason: " + delayreason + ")";
    }
}

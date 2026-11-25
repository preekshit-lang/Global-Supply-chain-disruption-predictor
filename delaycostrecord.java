package model;

public class delaycostrecord extends baseentity {
    private String shipmentid;
    private double basecost;
    private double extracost;

    public delaycostrecord(String id, String name, String shipmentid, double basecost, double extracost) {
        super(id, name);
        this.shipmentid = shipmentid;
        this.basecost = basecost;
        this.extracost = extracost;
    }

    public String getshipmentid() {
         return shipmentid; 
        }

    public double getbasecost() {
         return basecost; 
        }

    public double getextracost() { 
        return extracost; 
        }
        

    public String toString() {
        return "[" + id + "] shipment: " + shipmentid + " | base: rs. " + basecost + " | extra: rs. " + extracost;
    }
}

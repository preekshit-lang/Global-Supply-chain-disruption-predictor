package model;

public class disruptionevent extends baseentity {
    private String targetid;
    private int extradays;

    public disruptionevent(String id, String name, String targetid, int extradays) {
        super(id, name);
        this.targetid = targetid;
        this.extradays = extradays;
    }

    public String gettargetid() {
        return targetid;
    }

    public int getextradays() {
        return extradays;
    }

    public String toString() {
        return "[" + id + "] event: " + name + " | target: " + targetid + " | delay: +" + extradays + " days";
    }
}

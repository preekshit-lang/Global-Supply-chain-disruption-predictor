package model;

public class finishedproduct extends baseentity {
    private String requiredmaterial;
    private int dailyoutput;

    public finishedproduct(String id, String name, String requiredmaterial, int dailyoutput) {
        super(id, name);
        this.requiredmaterial = requiredmaterial;
        this.dailyoutput = dailyoutput;
    }

    public String getrequiredmaterial() {
        return requiredmaterial;
    }

    public int getdailyoutput() {
        return dailyoutput;
    }

    public String toString() {
        return "[" + id + "] " + name + " | needs: " + requiredmaterial + " | daily output: " + dailyoutput + " units";
    }
}

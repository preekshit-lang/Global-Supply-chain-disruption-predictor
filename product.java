package model;

public class product extends item {
    private String material;
    private int dailyoutput;

    public product(String id, String name, String material, int dailyoutput) {
        super(id, name);
        this.material = material;
        this.dailyoutput = dailyoutput;
    }

    public String getmaterial() { return material; }
    public int getdailyoutput() { return dailyoutput; }
}

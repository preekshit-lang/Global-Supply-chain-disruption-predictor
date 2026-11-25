package model;

public class item {
    protected String id;
    protected String name;

    public item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getid() {
        return id;
    }

    public String getname() {
        return name;
    }
}

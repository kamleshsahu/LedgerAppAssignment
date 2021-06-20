package entity;

public enum Operation {
    LOAN("LOAN"),
    PAYMENT("PAYMENT"),
    BALANCE("BALANCE");

    String name;

    Operation(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

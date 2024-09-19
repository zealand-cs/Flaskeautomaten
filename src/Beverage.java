public class Beverage {
    public String id;
    public BeverageType type;

    public Beverage(String id, BeverageType type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return STR."\{type}: \{id}";
    }
}


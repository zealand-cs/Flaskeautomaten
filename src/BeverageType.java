public enum BeverageType {
    Soda,
    Beer;

    public static BeverageType fromNumber(int i) throws Exception {
        return switch (i) {
            case 0 -> BeverageType.Soda;
            case 1 -> BeverageType.Beer;
            default -> throw new Exception("Invalid beverage number");
        };

    }
}

import java.util.function.Function;

public class BeverageSplitter extends BeverageConsumer {
    private final Buffer<Beverage> positive;
    private final Buffer<Beverage> negative;

    private final Function<Beverage, Boolean> comparison;

    public BeverageSplitter(Buffer<Beverage> input, Buffer<Beverage> positiveOutput, Buffer<Beverage> negativeOutput, Function<Beverage, Boolean> comparison) {
        super(input);
        this.positive = positiveOutput;
        this.negative = negativeOutput;
        this.comparison = comparison;
    }

    @Override
    protected void handleConsume(Beverage beverage) {
        if (this.comparison.apply(beverage)) {
            if (positive.isFull()) {
                try {
                    positive.waitIsNotFull();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            positive.add(beverage);
        } else {
            if (negative.isFull()) {
                try {
                    negative.waitIsNotFull();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            negative.add(beverage);
        }

        System.out.println(STR."Splitted \{beverage}");
    }
}

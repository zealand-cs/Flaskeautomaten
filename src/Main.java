import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        var beverageBuffer = new Buffer<Beverage>(16);

        var sodaBuffer = new Buffer<Beverage>(4);
        var beerBuffer = new Buffer<Beverage>(4);

        BeverageProducer producer = new BeverageProducer(beverageBuffer);
        BeverageSplitter splitter = new BeverageSplitter(beverageBuffer, sodaBuffer, beerBuffer, beverage -> beverage.type == BeverageType.Soda);
        BeverageConsumer sodaConsumer = new BeverageConsumer(sodaBuffer);
        BeverageConsumer beerConsumer = new BeverageConsumer(beerBuffer);

        producer.start();
        splitter.start();
        sodaConsumer.start();
        beerConsumer.start();
    }
}


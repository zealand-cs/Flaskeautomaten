import java.util.Random;

public class BeverageConsumer extends Thread {
    private final Buffer<Beverage> bufferReference;
    private final Random random = new Random();

    public BeverageConsumer(Buffer<Beverage> buffer) {
        this.bufferReference = buffer;
    }

    @Override
    public void run() {
        while (true) {
            if (bufferReference.isEmpty()) {
                try {
                    bufferReference.waitIsNotEmpty();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                sleep(random.nextInt(100, 1501));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            var beverage = bufferReference.poll();
            handleConsume(beverage);
        }
    }

    protected void handleConsume(Beverage beverage) {
        System.out.println(STR."Consumed \{beverage}");
    }
}

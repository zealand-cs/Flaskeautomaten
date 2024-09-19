import java.util.Random;

public class BeverageProducer extends Thread {
    private final Buffer<Beverage> bufferReference;
    private final Random random = new Random();

    public BeverageProducer(Buffer<Beverage> buffer) {
        this.bufferReference = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(random.nextInt(100, 1501));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            produce();
        }
    }

    public void produce() {
        if (bufferReference.isFull()) {
            try {
                bufferReference.waitIsNotFull();
            } catch (InterruptedException e) {
                System.out.println("Error while waiting to produce beverages.");
            }
        }

        var beverage = generateBeverage();
        bufferReference.add(beverage);

        System.out.println(STR."Produced \{beverage}");
    }

    public static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    public Beverage generateBeverage() {
        // Random between 0 and 1. 2 is given because it's exclusive.
        var rand = random.nextInt(0, 2);
        var id = generateString(random, "abcdefghijklmnopqrstuvwqyz", 12);

        try {
            return new Beverage(id, BeverageType.fromNumber(rand));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


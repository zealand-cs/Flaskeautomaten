import java.util.LinkedList;
import java.util.Queue;

public class Buffer<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int maxSize;

    private final Object IS_NOT_FULL = new Object();
    private final Object IS_NOT_EMPTY = new Object();

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean isFull() {
        return queue.size() >= maxSize;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void waitIsNotFull() throws InterruptedException {
        synchronized (IS_NOT_FULL) {
            IS_NOT_FULL.wait();
        }
    }

    private void notifyIsNotFull() {
        synchronized (IS_NOT_FULL) {
            IS_NOT_FULL.notify();
        }
    }

    public void waitIsNotEmpty() throws InterruptedException {
        synchronized (IS_NOT_EMPTY) {
            IS_NOT_EMPTY.wait();
        }
    }

    public void notifyIsNotEmpty() {
        synchronized (IS_NOT_EMPTY) {
            IS_NOT_EMPTY.notify();
        }
    }

    public void add(T message) {
        queue.add(message);
        notifyIsNotEmpty();
    }

    public T poll() {
        T elem = queue.poll();
        notifyIsNotFull();
        return elem;
    }

    public int getSize() {
        return queue.size();
    }
}
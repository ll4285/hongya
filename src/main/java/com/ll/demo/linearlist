class ArrayCircleQueue<T> {

    private int maxSize;
    private int front;
    private int rear;
    private T[] arr;

    ArrayCircleQueue(int length) {
        this.maxSize = length;
        this.arr = (T[]) new Object[length];
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public void add(T t) {
        if (isFull()) {
            System.out.println("满了！！！");
            return;
        }
        arr[rear] = t;
        rear = (rear + 1) % maxSize;
    }

    public T get() {
        if (isEmpty()) {
            throw new RuntimeException("空的！！！");
        }
        T t = arr[front];
        front = (front + 1) % maxSize;
        return t;
    }

    public void show() {
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=", i % maxSize);
            System.out.println(arr[i % maxSize]);
        }
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public T header() {
        return arr[front];
    }
}

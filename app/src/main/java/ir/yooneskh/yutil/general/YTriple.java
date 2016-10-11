package ir.yooneskh.yutil.general;

/**
 * Created by Yoones on 05/08/2016.
 */
public class YTriple <T, K, P> {

    private T first;
    private K second;
    private P third;

    public YTriple(T first, K second, P third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }

    public P getThird() {
        return third;
    }

}

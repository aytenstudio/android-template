package ir.yooneskh.yutil.general;

/**
 * Created by Yoones on 05/08/2016.
 */
public class YTuple <T, K> {

    private T first ;
    private K second;

    public YTuple(T first, K second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }

}

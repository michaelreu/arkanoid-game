package listiners;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
public class Counter {

    private int counter;

    /**
     * constructor.
     *
     * @param num num
     */
    public Counter(int num) {
        this.counter = num;
    }

    /**
     * add number to current count.
     *
     * @param number num
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number num
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * get current count.
     *
     * @return the counter.
     */
    public int getValue() {
        return this.counter;
    }

    /**
     *
     * @param num num
     */
    public void setValue(int num) {
        this.counter = num;
    }
}

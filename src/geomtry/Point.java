package geomtry;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 03.
 */
public class Point {

    // constructor.
    private double x;
    private final double y;

    /**
     * constructor of point object.
     *
     * @param x of point
     * @param y of point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other another point to compare to
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        // distance of this point to the other point.
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other another point to compare to
     * @return true if the points are equals, otherwise: false
     */
    public boolean equals(Point other) {
        return (Double.compare(this.x, other.getX()) == 0)
                && (Double.compare(this.y, other.getY()) == 0);
    }

    /**
     *
     * @return the x values of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y values of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * change the x location.
     *
     * @param newX of point
     */
    public void setX(double newX) {
        this.x = newX;
    }
}

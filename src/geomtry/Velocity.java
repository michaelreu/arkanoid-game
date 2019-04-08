package geomtry;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 03.
 */
public class Velocity {
    // constructor

    private double dx;
    private double dy;

    /**
     * constructor.
     *
     * @param dx change of x
     * @param dy change of y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return dx value
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return dy value
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * set the speed of the ball.
     *
     * @param newDy the speed of the ball
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * set the angle of the ball.
     *
     * @param newDx the angle of the ball
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * @param x of point
     * @return x change value
     */
    public double getXChange(double x) {
        return (Math.cos(Math.PI / 180 * this.dx) * this.dy);
    }

    /**
     * @param y of point
     * @return y change value
     */
    public double getYChange(double y) {
        return (Math.sin(Math.PI / 180 * this.dx) * this.dy);
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    /**
     * p is a point with position (x,y).
     *
     * @param p point to change location
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point((p.getX() + this.getXChange(p.getX())),
                ((p.getY() + this.getYChange(p.getY()))));
        return (newPoint);
    }

    /**
     * @param angle of ball
     * @param speed of ball
     * @return change dx, dy to be angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = angle;
        double dy = speed;
        return new Velocity(dx, dy);
    }
}

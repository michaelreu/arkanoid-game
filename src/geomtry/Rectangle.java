package geomtry;

import java.util.List;
import java.util.ArrayList;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class Rectangle {

    // constructor.
    private final Point upperLeft;
    private final double width;
    private final double height;
    private Line upperLine;
    private Line leftLine;
    private Line downtLine;
    private Line rightLine;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft of rec
     * @param width of rec
     * @param height of rec
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.rectangleLines();
    }

    /**
     * Create a new rectangle lines limit.
     */
    public void rectangleLines() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point downerleft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point downerRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        this.upperLine = new Line(this.upperLeft, upperRight);
        this.leftLine = new Line(this.upperLeft, downerleft);
        this.downtLine = new Line(downerleft, downerRight);
        this.rightLine = new Line(downerRight, upperRight);
    }

    /**
     *
     * @return the upper line of the rectangle
     */
    public Line getUpperLine() {
        return this.upperLine;
    }

    /**
     *
     * @return the leftLine of the rectangle
     */
    public Line getLeftLine() {
        return this.leftLine;
    }

    /**
     * @return the downtLine of the rectangle
     */
    public Line getDowntLine() {
        return this.downtLine;
    }

    /**
     *
     * @return the rightLine of the rectangle
     */
    public Line getRightLine() {
        return this.rightLine;
    }

    /**
     *
     * @return list with the 4 line of the rectangle
     */
    public List<Line> getRectangleLines() {
        java.util.List<Line> rectangleLines = new ArrayList<>();
        // upperLine
        rectangleLines.add(this.getUpperLine());
        // leftLine
        rectangleLines.add(this.getLeftLine());
        //downtLine
        rectangleLines.add(this.getDowntLine());
        //RightLine
        rectangleLines.add(this.getRightLine());
        return rectangleLines;
    }

    /**
     * @param line to check intercetion with.
     * @return a (possibly empty) List of intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        Point intersectionpoint;
        List<Point> intersection = new ArrayList<>();
        List<Line> rectangleLines;
        rectangleLines = this.getRectangleLines();
        for (int i = 0; i <= 3; i++) {
            if (line.isIntersecting(rectangleLines.get(i))) {
                intersectionpoint = line.intersectionWith(rectangleLines.get(i));
                intersection.add(intersectionpoint);
            }
        }
        return intersection;
    }

    /**
     *
     * @param ball the center of the ball
     * @param intersection list of potential intersection point
     * @return closet intersection
     */
    public Point closetIntersectionPoints(Point ball, List<Point> intersection) {
        Point closetPoint = intersection.get(0);
        double mindistance;
        mindistance = ball.distance(intersection.get(0));
        for (int i = 1; i < intersection.size(); i++) {
            if (mindistance > ball.distance(intersection.get(i))) {
                mindistance = ball.distance(intersection.get(i));
                closetPoint = intersection.get(i);
            }
        }
        return closetPoint;
    }

    /**
     *
     * @return the width and height of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     *
     * @return the height and height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}

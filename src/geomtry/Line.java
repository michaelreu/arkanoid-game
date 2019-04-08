package geomtry;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 03.
 */
public class Line {
    // constructors.

    private Point start;
    private Point end;

    /**
     * constructor of line with two points.
     *
     * @param start the first point of line
     * @param end the end of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor of line with four values.
     *
     * @param x1 the x value of the first point
     * @param y1 the y value of the first point
     * @param x2 the x value of the second point
     * @param y2 the y value of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     *
     * @param line1 first line
     * @param line2 second line
     * @return true if the first line is bigger otherwise return false.
     */
    public boolean islonger(Line line1, Line line2) {
        return line1.length() > line2.length();
    }

    /**
     *
     * @return the middle point of the line
     */
    public Point middle() {
        Point middlePoint = new Point(((this.start.getX() + this.end.getX()) / 2),
                ((this.start.getY() + this.end.getY()) / 2));
        return middlePoint;
    }

    /**
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * @param other another point to compare to
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return !(intersectionWith(other) == null);
    }

    /**
     * check if Point inline with Line.
     *
     * @param startOfLine of line
     * @param p point
     * @param endOfLine of line
     * @return true if it is otherwise false
     */
    public boolean inLine(Point startOfLine, Point p, Point endOfLine) {
        // if AC is horizontal
        if (startOfLine.getX() == endOfLine.getX()) {
            return ((p.getY() > startOfLine.getY() && p.getY() < endOfLine.getY())
                    || (p.getY() < startOfLine.getY()) && (p.getY()) > endOfLine.getY());
        }
        // if AC is vertical.
        if (startOfLine.getY() == endOfLine.getY()) {
            return ((p.getX() > startOfLine.getX() && p.getX() < endOfLine.getX())
                    || (p.getX() < startOfLine.getX()) && (p.getX()) > endOfLine.getX());
        }
        // match the gradients
        return (startOfLine.getX() - endOfLine.getX()) * (startOfLine.getY() - endOfLine.getY())
                == (endOfLine.getX() - p.getX()) * (endOfLine.getY() - p.getY());
    }

    /**
     *
     * @param other another point to compare to
     * @return the intersection point if the lines intersect, and null
     * otherwise.
     */
    public Point intersectionWith(Line other) {

        double x;
        double y;
        double slope1;
        double slope2;
        double c1;
        double c2;
        // if the other line is a point
        if (equelsPoint(other.start(), other.end)) {
            if (this.inLine(this.start, other.start(), this.end)) {
                return other.start();
            } else {
                return null;
            }
        }
        // if the lines are equals
        if (this.equals(other)) {
            return null;
        }
        //equations of the form x=c (two vertical lines) with the same x
        if (this.start.getX() == this.end.getX()
                && other.start.getX() == other.end.getX()
                && this.start.getX() == other.start.getX()) {
            return null;
        }

        //equations of the form y=c (two horizontal lines) with the same y
        if (this.start.getY() == this.end.getY()
                && other.start.getY() == other.end.getY()
                && this.start.getY() == other.start.getY()) {
            return null;
        }

        //equations of the form x=c (two vertical lines) with diffrent x
        if (this.start.getX() == this.end.getX()
                && other.start.getX() == other.end.getX()) {
            return null;
        }

        //equations of the form y=c (two horizontal lines) with diffrent y
        if (this.start.getY() == this.end.getY()
                && other.start.getY() == other.end.getY()) {
            return null;
        }

        //lineA is vertical x1 = x2 and slope will be infinity
        if (this.start.getX() == this.end.getX()) {
            //compute slope of line 2 (slope2) and c2
            slope2 = (other.end.getY() - other.start.getY())
                    / (other.end.getX() - other.start.getX());
            c2 = -slope2 * other.start.getX() + other.start.getY();
            //equation of vertical line is x = c
            //if line 1 and 2 intersect then x1=c1=x
            //subsitute x=x1 in (4) => -slope2x1 + y = c2
            // => y = c2 + slope2x1
            x = this.start.getX();
            y = c2 + slope2 * this.start.getX();
            //lineB is vertical x3 = x4 and slope will be infinity
        } else if (other.start.getX() == other.end.getX()) {
            //compute slope of line 1 (slope1) and c2
            slope1 = (this.end.getY() - this.start.getY())
                    / (this.end.getX() - this.start.getX());
            c1 = -slope1 * this.start.getX() + this.start.getY();
            //equation of vertical line is x = c
            //if line 1 and 2 intersect then x3=c3=x
            //subsitute x=x3 in (3) => -slope1x3 + y = c1
            // => y = c1 + slope1x3
            x = other.start.getX();
            y = c1 + slope1 * other.start.getX();
            //lineA & lineB are not vertical
        } else {
            //compute slope of line 1 (slope1) and c2
            slope1 = (this.end.getY() - this.start.getY())
                    / (this.end.getX() - this.start.getX());
            c1 = -slope1 * this.start.getX() + this.start.getY();
            //compute slope of line 2 (slope2) and c2
            slope2 = (other.end.getY() - other.start.getY())
                    / (other.end.getX() - other.start.getX());
            c2 = -slope2 * other.start.getX() + other.start.getY();
            //solving equations (3) & (4) => x = (c1-c2)/(slope2-slope1)
            //plugging x value in equation (4) => y = c2 + slope2 * x
            x = (c1 - c2) / (slope2 - slope1);
            y = c2 + slope2 * x;
        }

        //x,y can intersect outside the line segment since line is infinitely long
        //so finally check if x, y is within both the line segments
        if (((x >= this.start.getX() && x <= this.end.getX())
                || (x >= this.end.getX() && x <= this.start.getX()))
                && ((y >= this.start.getY() && y <= this.end.getY())
                || (y >= this.end.getY() && y <= this.start.getY()))
                && ((x >= other.start.getX() && x <= other.end.getX())
                || (x >= other.end.getX() && x <= other.start.getX()))
                && ((y >= other.start.getY() && y <= other.end.getY())
                || (y >= other.end.getY() && y <= other.start.getY()))) {
            return new Point(x, y);
        } else {
            return null;
        }
    }

    /**
     * @param other another point to compare to
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (equelsPoint(this.start, other.start) && equelsPoint(this.end, other.end)
                && (equelsPoint(this.start, other.end) && equelsPoint(this.end, other.start)));
    }

    //
    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the
     * line.
     *
     * @param rect of checking
     * @return true or false
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionpoints = new ArrayList<>();
        intersectionpoints = rect.intersectionPoints(new Line(this.start, this.end));
        if (intersectionpoints == null) {
            return null;
        } else {
            Line closetIntersection = new Line(this.start, intersectionpoints.get(0));
            for (int i = intersectionpoints.size() - 1; i >= 0; i--) {
                if (islonger(closetIntersection, new Line(this.start, intersectionpoints.get(i)))) {
                    closetIntersection = new Line(this.start, intersectionpoints.get(i));
                }
            }
            return closetIntersection.end;
        }
    }

    /**
     * check if two points are equals.
     *
     * @param point1 point
     * @param point2 point
     * @return true if they are equals otherwise return false
     */
    public boolean equelsPoint(Point point1, Point point2) {
        return point1.getX() == point2.getX() && point1.getY() == point2.getY();
    }
}

package exercise;

// BEGIN
class Segment {

    private Point point1;
    private Point point2;
    public Segment(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }
    public Point getBeginPoint() {
        return this.point1;
    }
    public Point getEndPoint() {
        return this.point2;
    }
    public Point getMidPoint() {
        return new Point((point1.getX() / 2) + (point2.getX() / 2), (point1.getY() / 2) + (point2.getY() / 2));
    }

}
// END

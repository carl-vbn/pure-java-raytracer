package carlvbn.raytracing.math;

public class Line {
    public Vector3 pointA;
    public Vector3 pointB;

    public  Line(Vector3 pointA, Vector3 pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Ray asRay() {
        return new Ray(pointA, pointB.subtract(pointA).normalize());
    }
}

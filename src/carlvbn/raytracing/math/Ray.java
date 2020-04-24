package carlvbn.raytracing.math;

public class Ray {
    private Vector3 origin;
    private Vector3 direction;

    public  Ray(Vector3 origin, Vector3 direction) {
        this.origin = origin;

        if (direction.length() != 1) {
            direction = direction.normalize();
        }
        this.direction = direction;
    }

    public Line asLine(float length) {
        return new Line(origin, origin.add(direction.multiply(length)));
    }

    public Vector3 getOrigin() {
        return origin;
    }

    public Vector3 getDirection() {
        return direction;
    }
}

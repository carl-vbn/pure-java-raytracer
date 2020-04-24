package carlvbn.raytracing.solids;

import carlvbn.raytracing.pixeldata.Color;
import carlvbn.raytracing.math.*;

public class Sphere extends Solid {
    private float radius;

    public Sphere(Vector3 position, float radius, Color color, float reflectivity, float emission) {
        super(position, color, reflectivity, emission);
        this.radius = radius;
    }

    @Override
    public Vector3 calculateIntersection(Ray ray) {
        float t = Vector3.dot(position.subtract(ray.getOrigin()), ray.getDirection());
        Vector3 p =  ray.getOrigin().add(ray.getDirection().multiply(t));

        float y = position.subtract(p).length();
        if (y < radius) {
            float x = (float) Math.sqrt(radius*radius - y*y);
            float t1 = t-x;
            if (t1 > 0) return ray.getOrigin().add(ray.getDirection().multiply(t1));
            else return null;
        } else {
            return null;
        }
    }

    @Override
    public Vector3 getNormalAt(Vector3 point) {
        return point.subtract(position).normalize();
    }
}

package carlvbn.raytracing.solids;

import carlvbn.raytracing.pixeldata.Color;
import carlvbn.raytracing.math.Ray;
import carlvbn.raytracing.math.Vector3;

// Adapted from:
// http://ray-tracing-conept.blogspot.com/2015/01/ray-box-intersection-and-normal.html
public class Box extends Solid {
    private Vector3 min, max;

    public Box(Vector3 position, Vector3 scale, Color color, float reflectivity, float emission) {
        super(position, color, reflectivity, emission);
        this.max = position.add(scale.multiply(0.5F));
        this.min = position.subtract(scale.multiply(0.5F));
    }

    @Override
    public Vector3 calculateIntersection(Ray ray) {
        float t1,t2,tnear = Float.NEGATIVE_INFINITY,tfar = Float.POSITIVE_INFINITY,temp;
        boolean intersectFlag = true;
        float[] rayDirection = ray.getDirection().toArray();
        float[] rayOrigin = ray.getOrigin().toArray();
        float[] b1 = min.toArray();
        float[] b2 = max.toArray();

        for(int i =0 ;i < 3; i++){
            if(rayDirection[i] == 0){
                if(rayOrigin[i] < b1[i] || rayOrigin[i] > b2[i])
                    intersectFlag = false;
            }
            else{
                t1 = (b1[i] - rayOrigin[i])/rayDirection[i];
                t2 = (b2[i] - rayOrigin[i])/rayDirection[i];
                if(t1 > t2){
                    temp = t1;
                    t1 = t2;
                    t2 = temp;
                }
                if(t1 > tnear)
                    tnear = t1;
                if(t2 < tfar)
                    tfar = t2;
                if(tnear > tfar)
                    intersectFlag = false;
                if(tfar < 0)
                    intersectFlag = false;
            }
        }
        if(intersectFlag)
            return ray.getOrigin().add(ray.getDirection().multiply(tnear));
        else
            return null;
    }


    public boolean contains(Vector3 point) {
        return point.getX() >= min.getX() && point.getY() >= min.getY() && point.getZ() >= min.getZ() && point.getX() <= max.getX() && point.getY() <= max.getY() && point.getZ() <= max.getZ();
    }

    @Override
    public Vector3 getNormalAt(Vector3 point) {
        float[] direction = point.subtract(position).toArray();
        float biggestValue = Float.NaN;

        for (int i = 0; i<3; i++) {
            if (Float.isNaN(biggestValue) || biggestValue < Math.abs(direction[i])) {
                biggestValue = Math.abs(direction[i]);
            }
        }

        if (biggestValue == 0) {
            return new Vector3(0, 0, 0);
        } else {
            for (int i = 0; i<3; i++) {
                if (Math.abs(direction[i]) == biggestValue) {
                    float[] normal = new float[] {0,0,0};
                    normal[i] = direction[i] > 0 ? 1 : -1;

                    return new Vector3(normal[0], normal[1], normal[2]);
                }
            }
        }

        return new Vector3(0, 0, 0);
    }
}

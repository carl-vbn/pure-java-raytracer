package carlvbn.raytracing.rendering;

import java.util.concurrent.CopyOnWriteArrayList;

import carlvbn.raytracing.math.Ray;
import carlvbn.raytracing.math.RayHit;
import carlvbn.raytracing.math.Vector3;
import carlvbn.raytracing.solids.Solid;

public class Scene {
    private Camera camera;
    private Light light;
    private CopyOnWriteArrayList<Solid> solids;
    private Skybox skybox;

    public Scene() {
        this.solids = new CopyOnWriteArrayList<Solid>();
        this.camera = new Camera();
        this.light = new Light(new Vector3(-1, 2, -1));
        this.skybox = new Skybox("Sky.jpg");
    }

    public void addSolid(Solid solid) {
        this.solids.add(solid);
    }

    public void clearSolids() {
        solids.clear();
    }

    public RayHit raycast(Ray ray) {
        RayHit closestHit = null;
        for (Solid solid : solids) {
            if (solid == null)
                continue;

            Vector3 hitPos = solid.calculateIntersection(ray);
            if (hitPos != null && (closestHit == null || Vector3.distance(closestHit.getPosition(), ray.getOrigin()) > Vector3.distance(hitPos, ray.getOrigin()))) {
                closestHit = new RayHit(ray, solid, hitPos);
            }
        }
        return closestHit;
    }

    public Camera getCamera() {
        return camera;
    }

    public Light getLight() {
        return light;
    }

    public Skybox getSkybox() {
        return skybox;
    }
}

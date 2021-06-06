package carlvbn.raytracing.rendering;

import carlvbn.raytracing.math.Vector3;

public class Camera {
    private Vector3 position;
    private float yaw;
    private float pitch;
    private float fieldOfVision;

    public Camera() {
        this.position = new Vector3(0,0,0);
        this.yaw = 0;
        this.pitch = 0;
        this.fieldOfVision = 60;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void translate(Vector3 vec) {
        this.position.translate(vec);
    }

    public void setFOV(float fieldOfVision) {
        this.fieldOfVision = fieldOfVision;
    }

    public float getFOV() {
        return fieldOfVision;
    }
}

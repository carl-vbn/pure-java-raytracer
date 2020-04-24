package carlvbn.raytracing.rendering;

import carlvbn.raytracing.math.Vector3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AnimationRenderer {
    private static Vector3 firstPosition;
    private static float firstYaw, firstPitch;

    private static Vector3 secondPosition;
    private static float secondYaw, secondPitch;

    private static int outputWidth = 1920, outputHeight = 1080;

    public static void captureFirstPosition(Camera camera) {
        firstPosition = camera.getPosition().clone();
        firstYaw = camera.getYaw();
        firstPitch = camera.getPitch();
    }

    public static void captureSecondPosition(Camera camera) {
        secondPosition = camera.getPosition().clone();
        secondYaw = camera.getYaw();
        secondPitch = camera.getPitch();
    }


    public static void renderImageSequence(Scene scene, File outputDirectory, int frameCount, float resolution) throws IOException {
        for (int frame = 0; frame<frameCount; frame++) {
            float t = (float)frame/(frameCount-1);
            Vector3 position = Vector3.lerp(firstPosition, secondPosition, t);
            float yaw = firstYaw + (secondYaw-firstYaw)*t;
            float pitch = firstPitch + (secondPitch-firstPitch)*t;

            Camera cam = scene.getCamera();
            cam.setPosition(position);
            cam.setYaw(yaw);
            cam.setPitch(pitch);
            BufferedImage frameBuffer = new BufferedImage(outputWidth, outputHeight, BufferedImage.TYPE_INT_RGB);
            Renderer.renderScenePostProcessed(scene, frameBuffer.getGraphics(), outputWidth, outputHeight, resolution);

            ImageIO.write(frameBuffer, "PNG", new File(outputDirectory, frame+".png"));

            System.out.println("Rendered frame "+frame+"/"+(frameCount-1));
        }

        Desktop.getDesktop().open(outputDirectory);
    }
}

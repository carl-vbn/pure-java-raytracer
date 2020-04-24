package carlvbn.raytracing.pixeldata;

import java.util.Collection;
import java.util.List;

public class Color {
    private float red;
    private float green;
    private float blue;

    public Color(float red, float green, float blue) {
        if (red > 1F || green > 1F || blue > 1F)
            throw new IllegalArgumentException("Color parameter(s) outside of expected range");

        if (Float.isNaN(red) || Float.isNaN(green) || Float.isNaN(blue))
            throw new IllegalArgumentException("One or more color parameters are NaN");

        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public Color multiply(Color other) {
        return new Color(red*other.red, green*other.green, blue*other.blue);
    }

    public Color multiply(float brightness) {
        brightness = Math.min(1, brightness);
        return new Color(red * brightness, green * brightness, blue * brightness);
    }

    public Color add(Color other) {
        return new Color(Math.min(1, this.red+other.red), Math.min(1, this.green+other.green), Math.min(1, this.blue+other.blue));
    }

    public void addSelf(Color other) {
        this.red = Math.min(1, this.red+other.red);
        this.green = Math.min(1, this.green+other.green);
        this.blue = Math.min(1, this.blue+other.blue);
    }

    public Color add(float brightness) {
        return new Color(Math.min(1, red+brightness), Math.min(1, green+brightness), Math.min(1, blue+brightness));
    }

    public int getRGB() {
        int redPart = (int)(red*255);
        int greenPart = (int)(green*255);
        int bluePart = (int)(blue*255);

        // Shift bits to right place
        redPart = (redPart << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        greenPart = (greenPart << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        bluePart = bluePart & 0x000000FF; //Mask out anything not blue.

        return 0xFF000000 | redPart | greenPart | bluePart; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }

    // https://en.wikipedia.org/wiki/Grayscale#Luma_coding_in_video_systems
    public float getLuminance() {
        return red*0.2126F + green*0.7152F + blue*0.0722F;
    }

    public static Color fromInt(int argb) {
        int b = (argb)&0xFF;
        int g = (argb>>8)&0xFF;
        int r = (argb>>16)&0xFF;

        return new Color(r/255F, g/255F, b/255F);
    }

    public java.awt.Color toAWTColor() {
        return new java.awt.Color(red, green, blue);
    }

    public static Color average(Collection<Color> colors) {
        float rSum = 0;
        float gSum = 0;
        float bSum = 0;

        for (Color col : colors) {
            rSum += col.getRed();
            gSum += col.getGreen();
            bSum += col.getBlue();
        }


        int colorCount = colors.size();
        return new Color(rSum/colorCount, gSum/colorCount, bSum/colorCount);
    }

    public static Color average(List<Color> colors, List<Float> weights) {
        if (colors.size() != weights.size())  {
            throw new IllegalArgumentException("Specified color count does not match weight count.");
        }

        float rSum = 0;
        float gSum = 0;
        float bSum = 0;
        float weightSum = 0;

        for (int i = 0; i<colors.size(); i++) {
            Color col = colors.get(i);
            float weight = weights.get(i);
            rSum += col.getRed() * weight;
            gSum += col.getGreen() * weight;
            bSum += col.getBlue() * weight;
            weightSum+=weight;
        }

        return new Color(rSum/weightSum, gSum/weightSum, bSum/weightSum);
    }

    public static Color average(Color... colors) {
        float rSum = 0;
        float gSum = 0;
        float bSum = 0;

        for (Color col : colors) {
            rSum += col.getRed();
            gSum += col.getGreen();
            bSum += col.getBlue();
        }

        int colorCount = colors.length;
        return new Color(rSum/colorCount, gSum/colorCount, bSum/colorCount);
    }

    private static float lerp(float a, float b, float t) {
        return a + t * (b - a);
    }

    public static Color lerp(Color a, Color b, float t) {
        return new Color(lerp(a.getRed(), b.getRed(), t), lerp(a.getGreen(), b.getGreen(), t), lerp(a.getBlue(), b.getBlue(), t));
    }

    public static final Color BLACK = new Color(0F,0F,0F);
    public static final Color WHITE = new Color(1F, 1F, 1F);
    public static final Color RED = new Color(1F, 0F, 0F);
    public static final Color GREEN = new Color(0F, 1F, 0F);
    public static final Color BLUE = new Color(0F, 0F, 1F);
    public static final Color MAGENTA = new Color(1.0F, 0.0F, 1.0F);
    public static final Color GRAY = new Color(0.5F, 0.5F, 0.5F);
    public static final Color DARK_GRAY = new Color(0.2F, 0.2F, 0.2F);
}

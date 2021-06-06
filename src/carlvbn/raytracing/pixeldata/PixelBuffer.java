package carlvbn.raytracing.pixeldata;

/** The PixelBuffer is an easy way to store PixelData. It holds color, depth and emission information about every pixel */
public class PixelBuffer {
    private PixelData[][] pixels;
    private int width, height;

    public PixelBuffer(int width, int height) {
        this.width = width;
        this.height = height;

        this.pixels = new PixelData[width][height];
    }

    public void setPixel(int x, int y, PixelData pixelData) {
        pixels[x][y] = pixelData;
    }

    public PixelData getPixel(int x, int y) {
        return pixels[x][y];
    }

    public void filterByEmission(float minEmission) {
        for (int i = 0; i<pixels.length; i++) {
            for (int j = 0; j<pixels[i].length; j++) {
                PixelData pxl = pixels[i][j];
                if (pxl != null && pxl.getEmission() < minEmission) {
                    pixels[i][j] = new PixelData(Color.BLACK, pxl.getDepth(), pxl.getEmission());
                }
            }
        }
    }

    /** Changes will be applied to the buffer itself */
    public PixelBuffer add(PixelBuffer other) {
        for (int i = 0; i<pixels.length; i++) {
            for (int j = 0; j<pixels[i].length; j++) {
                PixelData pxl = pixels[i][j];
                PixelData otherPxl = other.pixels[i][j];
                if (pxl != null && otherPxl != null) {
                    //float brightnessB4 = pixels[i][j].getColor().getLuminance();
                    pixels[i][j].add(otherPxl);
                }
            }
        }

        return this;
    }

    /** Changes will be applied to the buffer itself */
    public PixelBuffer multiply(float brightness) {
        for (int i = 0; i<pixels.length; i++) {
            for (int j = 0; j<pixels[i].length; j++) {
                pixels[i][j].multiply(brightness);
            }
        }

        return this;
    }

    public PixelBuffer resize(int newWidth, int newHeight, boolean linear) { // Linear resizing isn't actually implemented yet.
        PixelBuffer copy = new PixelBuffer(newWidth, newHeight);
        for (int i = 0; i<newWidth; i++) {
            for (int j = 0; j<newHeight; j++) {
                copy.pixels[i][j] = pixels[(int)((float)i/newWidth*width)][(int)((float)j/newHeight*height)];
            }
        }
        return copy;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void countEmptyPixels() {
        int emptyPixels = 0;
        for (int i = 0; i < pixels.length; i++) {
            if (pixels[i] == null) emptyPixels++;
        }
        System.out.println("Found "+emptyPixels+" empty pixels.");
    }

    @Override
    public PixelBuffer clone() {
        PixelBuffer clone = new PixelBuffer(width, height);
        for (int i = 0; i<pixels.length; i++) {
            System.arraycopy(pixels[i], 0, clone.pixels[i], 0, pixels[i].length);
        }
        return clone;
    }
}

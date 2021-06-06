package carlvbn.raytracing.pixeldata;

public class GaussianBlur {
    private float[] kernel;
    private PixelBuffer pixelBuffer;
    private int width, height;

    public GaussianBlur(PixelBuffer pixelBuffer) {
        this.pixelBuffer = pixelBuffer;
        this.width = pixelBuffer.getWidth();
        this.height = pixelBuffer.getHeight();

        // Kernel is currently hardcoded with the help of http://dev.theomader.com/gaussian-kernel-calculator/
        kernel = new float[]{0.0093F, 0.028002F, 0.065984F, 0.121703F, 0.175713F, 0.198596F, 0.175713F, 0.121703F, 0.065984F, 0.028002F, 0.0093F};
    }

    public void blurHorizontally(int radius) {
        PixelBuffer result = new PixelBuffer(width, height);
        for (int y = 0; y<height; y++) {
            for (int x = 0; x<width; x++) {
                Color blurredColor = new Color(0, 0, 0);
                PixelData originalPixel = pixelBuffer.getPixel(x, y);
                for (int i = -radius; i<=radius; i++) {
                    float kernelMultiplier = kernel[(int) ((i+radius)/(radius*2F)*(kernel.length-1))];
                    if (x+i>=0 && x+i<width) {
                        PixelData pixel = pixelBuffer.getPixel(x+i, y);
                        if (pixel != null)
                            blurredColor.addSelf(pixel.getColor().multiply(kernelMultiplier));
                    }
                }

                result.setPixel(x, y, new PixelData(blurredColor, originalPixel.getDepth(), originalPixel.getEmission()));
            }
        }
        this.pixelBuffer = result;
    }

    public void blurVertically(int radius) {
        PixelBuffer result = new PixelBuffer(width, height);
        for (int x = 0; x<width; x++) {
            for (int y = 0; y<height; y++) {
                Color blurredColor = new Color(0, 0, 0);
                PixelData originalPixel = pixelBuffer.getPixel(x, y);
                for (int i = -radius; i<=radius; i++) {
                    float kernelMultiplier = kernel[(int) ((i+radius)/(radius*2F)*(kernel.length-1))];
                    if (y+i>=0 && y+i<height) {
                        PixelData pixel = pixelBuffer.getPixel(x, y+i);
                        if (pixel != null)
                            blurredColor.addSelf(pixel.getColor().multiply(kernelMultiplier));
                    }
                }

                result.setPixel(x, y, new PixelData(blurredColor, originalPixel.getDepth(), originalPixel.getEmission()));
            }
        }
        this.pixelBuffer = result;
    }

    public void blur(int radius, int iterations) {
        for (int i = 0; i<iterations; i++) {
            blurHorizontally(radius);
            blurVertically(radius);
        }
    }

    public PixelBuffer getPixelBuffer() {
        return pixelBuffer;
    }

    // Currently unused due to kernel being hardcoded
    //private float gaussianDistribution(float x, float sigma) {
    //    return (float) (1/Math.sqrt(2*Math.PI*sigma*sigma)*Math.exp(-(x*x)/(2*sigma*sigma))); // https://en.wikipedia.org/wiki/Gaussian_blur
    //}

}

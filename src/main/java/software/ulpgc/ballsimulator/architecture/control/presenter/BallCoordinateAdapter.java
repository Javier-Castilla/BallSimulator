package software.ulpgc.ballsimulator.architecture.control.presenter;

public class BallCoordinateAdapter {
    private final double pixelsPerMeter;

    private BallCoordinateAdapter(double pixelsPerMeter) {
        this.pixelsPerMeter = pixelsPerMeter;
    }

    public static BallCoordinateAdapter with(double pixelsPerMeter) {
        return new BallCoordinateAdapter(pixelsPerMeter);
    }

    public int toPixels(double meters) {
        return (int) (meters * pixelsPerMeter);
    }

    public double toMeters(int pixels) {
        return pixels / pixelsPerMeter;
    }

    public int inBoundsX(int pixels) {
        return Math.max(pixels, 0);
    }

    public int inBoundsY(int pixels, double radius) {
        return Math.max(pixels, toPixels(radius) * 2);
    }
}

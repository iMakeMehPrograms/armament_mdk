package github.imakemehthings.armament.util;

public enum DragCoefficientTable {
    MACH_HALF(0.5f, 0.1, 0.1, 0.1, 0.1, 0.2, 0.1, 0.1, 0.1, 0.1, 0.1);

    private final float mach;
    private final double g1DragCoefficient;
    private final double g2DragCoefficient;
    private final double g3DragCoefficient;
    private final double g4DragCoefficient;
    private final double g5DragCoefficient;
    private final double g6DragCoefficient;
    private final double g7DragCoefficient;
    private final double g8DragCoefficient;
    private final double glDragCoefficient;
    private final double gsDragCoefficient;

    DragCoefficientTable(float m, double g1dC, double g2dC, double g3dC, double g4dC, double g5dC, double g6dC, double g7dC, double g8dC, double gldC, double gsdC) {
        this.mach = m;
        this.g1DragCoefficient = g1dC;
        this.g2DragCoefficient = g2dC;
        this.g3DragCoefficient = g3dC;
        this.g4DragCoefficient = g4dC;
        this.g5DragCoefficient = g5dC;
        this.g6DragCoefficient = g6dC;
        this.g7DragCoefficient = g7dC;
        this.g8DragCoefficient = g8dC;
        this.glDragCoefficient = gldC;
        this.gsDragCoefficient = gsdC;
    }
}

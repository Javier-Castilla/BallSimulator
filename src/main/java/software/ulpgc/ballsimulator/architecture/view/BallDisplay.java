package software.ulpgc.ballsimulator.architecture.view;

import java.util.List;

public interface BallDisplay {
    void draw(List<PaintOrder> paintOrders);

    void on(Shift shift);

    void on(Released released);

    void on(Click click);

    int width();

    int height();

    interface Shift {
        Shift NULL = offset -> {};

        void offset(int offset);
    }

    interface Released {
        Released NULL = offset -> {};

        void offset(int offset);
    }

    interface Click {
        Click NULL = (xOffset, yOffset) -> {};

        void offset(int xOffset, int yOffset);
    }

    record PaintOrder(int x, int y, int radius) {}
}

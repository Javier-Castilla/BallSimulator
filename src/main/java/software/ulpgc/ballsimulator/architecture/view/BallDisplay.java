package software.ulpgc.ballsimulator.architecture.view;

import java.util.List;

public interface BallDisplay {
    void draw(List<Circle> circle);

    void on(Shift shift);

    void on(Released released);

    interface Shift {
        Shift NULL = offset -> {};

        void offset(int offset);
    }

    interface Released {
        Released NULL = offset -> {};

        void offset(int offset);
    }

    record Circle(int x, int y, int radius) {}
}

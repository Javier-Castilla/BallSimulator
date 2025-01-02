package software.ulpgc.ballsimulator.apps.windows;

import software.ulpgc.ballsimulator.apps.windows.view.MainFrame;
import software.ulpgc.ballsimulator.architecture.control.BallPresenter;
import software.ulpgc.ballsimulator.architecture.model.Ball;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        BallPresenter presenter = new BallPresenter(frame.display());
        presenter.add(new Ball(1, 10, 10, 1, 0, -99.8, 1)).execute();
    }
}

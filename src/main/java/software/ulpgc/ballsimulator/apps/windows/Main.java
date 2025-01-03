package software.ulpgc.ballsimulator.apps.windows;

import software.ulpgc.ballsimulator.apps.windows.view.MainFrame;
import software.ulpgc.ballsimulator.architecture.control.presenter.BallPresenter;
import software.ulpgc.ballsimulator.architecture.control.ClearCommand;
import software.ulpgc.ballsimulator.architecture.control.StartCommand;
import software.ulpgc.ballsimulator.architecture.control.StopCommand;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        BallPresenter presenter = new BallPresenter(frame.display(), frame.dialog());
        presenter.execute();
        frame.add("clear", new ClearCommand(presenter))
                .add("stop", new StopCommand(presenter))
                .add("start", new StartCommand(presenter))
                .setVisible(true);
    }
}

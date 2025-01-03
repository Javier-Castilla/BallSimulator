package software.ulpgc.ballsimulator.architecture.control;

import software.ulpgc.ballsimulator.architecture.control.presenter.BallPresenter;

public class StartCommand implements Command {
    private final BallPresenter presenter;

    public StartCommand(BallPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.execute();
    }
}

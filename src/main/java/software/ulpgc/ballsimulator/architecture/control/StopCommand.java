package software.ulpgc.ballsimulator.architecture.control;

import software.ulpgc.ballsimulator.architecture.control.presenter.BallPresenter;

public class StopCommand implements Command {
    private final BallPresenter presenter;

    public StopCommand(BallPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.stopExecution();
    }
}

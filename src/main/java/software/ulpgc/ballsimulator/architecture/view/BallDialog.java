package software.ulpgc.ballsimulator.architecture.view;

import software.ulpgc.ballsimulator.architecture.model.Ball;

public interface BallDialog {
    Ball get();

    double radius();

    double velocity();

    double gravity();

    double cr();
}

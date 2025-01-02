package software.ulpgc.ballsimulator.architecture.control;

import software.ulpgc.ballsimulator.architecture.model.Ball;
import software.ulpgc.ballsimulator.architecture.view.BallDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BallPresenter {
    private static final double PIXELS_PER_METER = 2 / 0.1;
    private final BallDisplay display;
    private final BallSimulator simulator;
    private final List<Ball> balls;
    private final static double DT = 0.001;
    private final static int PERIOD = (int) (1000 * DT);

    public BallPresenter(BallDisplay display) {
        this.display = display;
        this.simulator = new BallSimulator(DT);
        this.balls = new ArrayList<>();
    }

    public void execute() {
        new Timer().schedule(task(), PERIOD, PERIOD);
    }

    private TimerTask task() {
        return new TimerTask() {
            @Override
            public void run() {
                simulate();
                draw();
            }
        };
    }

    private void draw() {
        display.draw(toCircles(balls));
    }

    private List<BallDisplay.Circle> toCircles(List<Ball> balls) {
        return balls.stream()
                .map(this::toCircle)
                .toList();
    }

    private BallDisplay.Circle toCircle(Ball ball) {
        return new BallDisplay.Circle(0, toPixels(ball.height()), toPixels(ball.radius()));
    }

    private int toPixels(double meters) {
        return (int) (meters * PIXELS_PER_METER);
    }

    private void simulate() {
        List<Ball> newBalls = balls.stream().map(simulator::simulate).toList();
        balls.clear();
        balls.addAll(newBalls);
    }
}

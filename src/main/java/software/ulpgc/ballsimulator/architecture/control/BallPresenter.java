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
        this.display.on(shift());
        this.display.on(released());
        this.display.on(click());
    }

    private BallDisplay.Click click() {
        return (xOffset, yOffset) -> {
            add(new Ball(
                    10,
                    toMeters(xOffset),
                    toMeters(yOffset),
                    1,
                    0,
                    -9.8,
                    1
            ));
        };
    }

    private BallDisplay.Shift shift() {
        return offset -> {};
    }

    private BallDisplay.Released released() {
        return offset -> {};
    }

    public BallPresenter add(Ball ball) {
        synchronized (balls) {
            this.balls.add(ball);
        }
        return this;
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
        display.draw(getPaintOrders(balls));
    }

    private List<BallDisplay.PaintOrder> getPaintOrders(List<Ball> balls) {
        return balls.stream()
                .map(this::toPaintOrder)
                .toList();
    }

    private BallDisplay.PaintOrder toPaintOrder(Ball ball) {
        return new BallDisplay.PaintOrder(toPixels(ball.x()), toPixels(ball.y()), toPixels(ball.radius()));
    }

    private int toPixels(double meters) {
        return (int) (meters * PIXELS_PER_METER);
    }

    private double toMeters(double pixels) {
        return pixels / PIXELS_PER_METER;
    }

    private void simulate() {
        List<Ball> newBalls = balls.stream().map(simulator::simulate).toList();
        balls.clear();
        balls.addAll(newBalls);
    }
}

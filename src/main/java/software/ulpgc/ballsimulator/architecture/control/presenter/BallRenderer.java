package software.ulpgc.ballsimulator.architecture.control.presenter;

import software.ulpgc.ballsimulator.architecture.model.Ball;
import software.ulpgc.ballsimulator.architecture.view.BallDisplay;

import java.util.List;
import java.util.Set;

public class BallRenderer {
    private final BallDisplay display;
    private final BallCoordinateAdapter adapter;

    public BallRenderer(BallDisplay display, BallCoordinateAdapter converter) {
        this.display = display;
        this.adapter = converter;
    }

    public void render(Set<Ball> balls) {
        List<BallDisplay.PaintOrder> paintOrders = balls.stream().map(this::toPaintOrder).toList();
        display.draw(paintOrders);
    }

    private BallDisplay.PaintOrder toPaintOrder(Ball ball) {
        return new BallDisplay.PaintOrder(
                adapter.inBoundsX(adapter.toPixels(ball.x() - ball.radius())),
                adapter.inBoundsY(adapter.toPixels(ball.y() + ball.radius()), ball.radius()),
                adapter.toPixels(ball.radius())
        );
    }
}


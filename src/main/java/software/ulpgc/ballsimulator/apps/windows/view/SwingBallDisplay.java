package software.ulpgc.ballsimulator.apps.windows.view;

import software.ulpgc.ballsimulator.architecture.view.BallDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class SwingBallDisplay extends JPanel implements BallDisplay {
    private Shift shift;
    private Released released;
    private Click click;
    private final List<PaintOrder> paintOrders;
    private final static int WIDTH_GAP = 20;
    private final static int HEIGHT_GAP = 20;

    public SwingBallDisplay() {
        this.shift = Shift.NULL;
        this.released = Released.NULL;
        this.click = Click.NULL;
        this.paintOrders = new ArrayList<>();
        this.addMouseListener(createMouseListener());
    }

    private MouseListener createMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                click.offset(e.getX() - WIDTH_GAP * 2, height() - e.getY());
            }
        };
    }

    @Override
    public void paint(Graphics g) {
        clearCanvas(g);
        synchronized (paintOrders) {
            paintOrders.forEach(p -> drawPaintOrder(g, p));
        }
    }

    private void clearCanvas(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(xCenterFor(width()), yCenterFor(height()), width(), height());
    }

    private int yCenterFor(int height) {
        return (getHeight() - height()) / 2;
    }

    private int xCenterFor(int width) {
        return (getWidth() - width()) / 2;
    }

    private void drawPaintOrder(Graphics g, PaintOrder p) {
        g.setColor(Color.BLUE);
        g.fillOval(
                p.x() + 2 * p.radius(),
                (height() - HEIGHT_GAP * 2) - p.y() + 2 * p.radius(),
                p.radius() * 2,
                p.radius() * 2
        );
    }

    @Override
    public void draw(List<PaintOrder> paintOrders) {
        synchronized (this.paintOrders) {
            this.paintOrders.clear();
            this.paintOrders.addAll(paintOrders);
        }
        repaint();
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }

    @Override
    public void on(Released released) {
        this.released = released;
    }

    @Override
    public void on(Click click) {
        this.click = click;
    }

    @Override
    public int width() {
        return getWidth() - WIDTH_GAP;
    }

    @Override
    public int height() {
        return getHeight() - HEIGHT_GAP;
    }
}

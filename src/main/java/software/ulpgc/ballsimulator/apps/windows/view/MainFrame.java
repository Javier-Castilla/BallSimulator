package software.ulpgc.ballsimulator.apps.windows.view;

import software.ulpgc.ballsimulator.architecture.view.BallDisplay;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final SwingBallDisplay display;

    public MainFrame() throws HeadlessException {
        setTitle("BallSimulator");
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(BorderLayout.CENTER, this.display = createBallDisplay());
    }

    private SwingBallDisplay createBallDisplay() {
        return new SwingBallDisplay();
    }

    public BallDisplay display() {
        return display;
    }
}

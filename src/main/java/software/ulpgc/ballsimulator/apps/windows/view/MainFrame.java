package software.ulpgc.ballsimulator.apps.windows.view;

import software.ulpgc.ballsimulator.architecture.control.Command;
import software.ulpgc.ballsimulator.architecture.view.BallDialog;
import software.ulpgc.ballsimulator.architecture.view.BallDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final SwingBallDisplay display;
    private final SwingBallDialog dialog;
    private final Map<String, Command> commands;

    public MainFrame() throws HeadlessException {
        setTitle("BallSimulator");
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, createToolbar());
        add(BorderLayout.CENTER, this.display = createBallDisplay());
        add(BorderLayout.SOUTH, this.dialog = createBallDialog());
        this.commands = new HashMap<>();
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();
        panel.add(createButton("Clear"));
        panel.add(createButton("Stop"));
        panel.add(createButton("Start"));
        return panel;
    }

    private JButton createButton(String name) {
        JButton button = new JButton(name);
        button.addActionListener(e -> commands.get(name.toLowerCase()).execute());
        return button;
    }

    private SwingBallDialog createBallDialog() {
        return new SwingBallDialog();
    }

    private SwingBallDisplay createBallDisplay() {
        return new SwingBallDisplay();
    }

    public BallDisplay display() {
        return display;
    }

    public MainFrame add(String name, Command command) {
        commands.put(name, command);
        return this;
    }

    public BallDialog dialog() {
        return dialog;
    }
}

package pl.edu.pw.elka.gis.LGraph.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Represents GUI main window.
 */
public class MainWindow {

    private JPanel mainPanel;

    /**
     * Main constructor.
     *
     * @param title The title of a new window.
     */
    public MainWindow(String title) {
        JFrame frame = new JFrame(title);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

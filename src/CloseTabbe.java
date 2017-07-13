import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CloseTabbe {

    public JComponent makeUI() {
        UIManager.put("TabbedPane.tabInsets", new Insets(2, 2, 2, 50));
        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("aaaaaaaaaaaaaaaa", new JPanel());
        tabbedPane.addTab("bbbbbbbb", new JPanel());
        tabbedPane.addTab("ccc", new JPanel());

        JPanel p = new JPanel(new BorderLayout());
        //p.setBorder(BorderFactory.createLineBorder(Color.RED, 10));
        p.add(tabbedPane);
        p.add(new JButton(new AbstractAction("add tab") {
            @Override public void actionPerformed(ActionEvent e) {
                tabbedPane.addTab("test", new JScrollPane(new JTree()));
            }
        }), BorderLayout.SOUTH);

        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                JPanel gp = new CloseableTabbedPaneGlassPane(tabbedPane);
                tabbedPane.getRootPane().setGlassPane(gp);
                gp.setOpaque(false);
                gp.setVisible(true);
            }
        });

        return p;
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                createAndShowGUI();
            }
        });
    }
    public static void createAndShowGUI() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.getContentPane().add(new CloseTabbe().makeUI());
        f.setSize(320, 240);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

class CloseableTabbedPaneGlassPane extends JPanel {
    private final Point pt = new Point(-100, -100);
    private final JButton button = new JButton("x") {
        @Override public Dimension getPreferredSize() {
            return new Dimension(16, 16);
        }
    };
    private final JTabbedPane tabbedPane;
    private final Rectangle buttonRect = new Rectangle(button.getPreferredSize());

    public CloseableTabbedPaneGlassPane(JTabbedPane tabbedPane) {
        super();
        this.tabbedPane = tabbedPane;
        MouseAdapter h = new Handler();
        tabbedPane.addMouseListener(h);
        tabbedPane.addMouseMotionListener(h);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setRolloverEnabled(false);
    }
    @Override public void paintComponent(Graphics g) {
        Point glassPt = SwingUtilities.convertPoint(tabbedPane, 0, 0, this);
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            Rectangle tabRect = tabbedPane.getBoundsAt(i);
            int x = tabRect.x + tabRect.width - buttonRect.width - 2;
            int y = tabRect.y + (tabRect.height - buttonRect.height) / 2;
            buttonRect.setLocation(x, y);
            button.setForeground(buttonRect.contains(pt) ? Color.RED : Color.BLACK);
            buttonRect.translate(glassPt.x, glassPt.y);
            SwingUtilities.paintComponent(g, button, this, buttonRect);
        }
    }
    class Handler extends MouseAdapter {
        @Override public void mouseClicked(MouseEvent e) {
            pt.setLocation(e.getPoint());
            int index = tabbedPane.indexAtLocation(pt.x, pt.y);
            if (index >= 0) {
                Rectangle tabRect = tabbedPane.getBoundsAt(index);
                int x = tabRect.x + tabRect.width - buttonRect.width - 2;
                int y = tabRect.y + (tabRect.height - buttonRect.height) / 2;
                buttonRect.setLocation(x, y);
                if (buttonRect.contains(pt)) {
                    tabbedPane.removeTabAt(index);
                }
            }
            tabbedPane.repaint();
        }
        @Override public void mouseMoved(MouseEvent e) {
            pt.setLocation(e.getPoint());
            int index = tabbedPane.indexAtLocation(pt.x, pt.y);
            if (index >= 0) {
                tabbedPane.repaint(tabbedPane.getBoundsAt(index));
            } else {
                tabbedPane.repaint();
            }
        }
    }
}
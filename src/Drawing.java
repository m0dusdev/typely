import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


/**
 * Class extending JInternalframe, instances of this class are added to the JTabbedPane.
 * This class contains an instance of RSSyntaxarea witch is the editor interface the
 * user interacts with.
 *
 * @author William March -
 */

        public class Drawing extends JPanel {

            private java.util.List<java.util.List<Point>> points;

            public Drawing() {
                points = new ArrayList<>(25);
                MouseAdapter ma = new MouseAdapter() {

                    private java.util.List<Point> currentPath;

                    @Override
                    public void mousePressed(MouseEvent e) {
                        currentPath = new ArrayList<>(25);
                        currentPath.add(e.getPoint());

                        points.add(currentPath);
                    }

                    @Override
                    public void mouseDragged(MouseEvent e) {
                        Point dragPoint = e.getPoint();
                        currentPath.add(dragPoint);
                        repaint();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        currentPath = null;
                    }

                };

                addMouseListener(ma);
                addMouseMotionListener(ma);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(200, 200);
            }

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                for (List<Point> path : points) {
                    Point from = null;
                    for (Point p : path) {
                        if (from != null) {
                            g2d.drawLine(from.x, from.y, p.x, p.y);
                        }
                        from = p;
                    }
                }
                g2d.dispose();
            }
        }





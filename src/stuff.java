import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class stuff extends JPanel implements KeyListener, MouseListener {

    public JFrame screen = new JFrame();
    public Rectangle yes = new Rectangle(0, 0, 1000, 1000);

    public static void main(String[] args) {
        stuff stuff = new stuff();
    }

    public stuff() {
        createFrame();
    }

    public void createFrame() {
        screen.setUndecorated(true);
        screen.setVisible(true);
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.addKeyListener(this);
        screen.addMouseListener(this);
        screen.add(this);
        screen.setResizable(true);
        screen.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponents(g);

        g.fillRect(0,0,1000,1000);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE){
            System.exit(-1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

package sharescreenwithclient;

/**
 *
 * @author Roy
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Clients extends JFrame {

    private JPanel panel;
    private JButton btn;
    private JTextField ip;
    private Container c;
    private Socket soc;
    private JLabel ipl;
    private Font font;

    public Clients() {
        initComponents();
        
    }

    public void initComponents() {

        panel = new JPanel();
        btn = new JButton("Connect");
        font = new Font("Arial", Font.BOLD, 18);
        ip = new JTextField();
        c = this.getContentPane();
        c.setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Client Application");

        // make full screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(screenSize);
        this.setLayout(null);
        ipl = new JLabel("Enter Server IP Adderss");
        ipl.setBounds(200, 0, 250, 50);
        ipl.setFont(font);
        c.add(ipl);
        ip.setBounds(500, 0, 300, 50);
        ip.setFont(font);
        c.add(ip);

        btn.setBounds(840, 0, 150, 50);
        btn.setFont(font);
        c.add(btn);

        Toolkit kb = Toolkit.getDefaultToolkit();

        int x, y, w;
        x = (int) kb.getScreenSize().getWidth();
        y = (int) kb.getScreenSize().getHeight();

        panel.setBounds(10, 50, (x - 20), (y - 70));
        panel.setForeground(Color.RED);
        panel.setVisible(true);
        panel.setLayout(null);

        c.add(panel);
        
        class Handler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                String ipa = ip.getText();
                if (ipa.startsWith("192.") && !ipa.equals("")) 
                {

                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {

                                while (true) {
                                    try {
                                        soc = new Socket(ipa, 88);// ipaddress & port
                                        BufferedImage img = ImageIO.read(soc.getInputStream());
                                        panel.getGraphics().drawImage(img, 0, 0, panel.getWidth(), panel.getHeight(), null);

                                        soc.close();
                                    } catch (Exception e) {

                                    }

                                    // viedo stream refresh in 5 milisecond
                                    try {
                                        Thread.sleep(5);
                                    } catch (Exception e) {
                                    }

                                }

                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }

                        }
                    }).start();
                } else {
                    JOptionPane.showMessageDialog(null, "Enter Valid IP Address");
                }

            }

        }
        Handler h = new Handler();
        btn.addActionListener(h);
        ip.addActionListener(h);
    }

}

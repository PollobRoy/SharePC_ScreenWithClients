package sharescreenwithclient;

/**
 *
 * @author Roy
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Server extends JFrame {

    private JLabel server,ip;
    private Font font;
    private JButton btn;

    public Server() {
        initComponents();

    }

    private void initComponents() {

        
        font = new Font("Bahnschrift SemiLight", Font.BOLD, 60);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Server Application");

        // make full screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(screenSize);

        server = new JLabel("Server Application");
        server.setFont(font);
        server.setBounds(400, 100, 600, 200);
        this.setLayout(null);
        this.add(server);
        
        // ip address
        ip= new JLabel();
        ip.setFont(font);
        ip.setBounds(450, 400, 600, 200);
        this.add(ip);

        // Button
        btn = new JButton("Start Server");
        btn.setBounds(410, 300, 500, 150);
        btn.setFont(font);
        btn.setForeground(Color.DARK_GRAY);
        this.add(btn);
        
        

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Robot rob = new Robot();
                            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

                            while (true) {
                                try {

                                    ServerSocket soc = new ServerSocket(88);// server port
                                    Socket so = soc.accept();
                                    BufferedImage img = rob.createScreenCapture(new Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));

                                    ByteArrayOutputStream ous = new ByteArrayOutputStream();
                                    ImageIO.write(img, "png", ous);
                                    so.getOutputStream().write(ous.toByteArray());
                                    soc.close();
                                } catch (Exception e) {

                                }
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
                InetAddress inetAddress;
                
                // get server ip address
                try {
                    inetAddress = InetAddress. getLocalHost();
                    String ipAddress=inetAddress. getHostAddress();
                    ip.setText(ipAddress);
                } catch (Exception ex) {
                    
                }
                
                JOptionPane.showMessageDialog(null, "Server Started.");

            }

        }
        );

    }

}

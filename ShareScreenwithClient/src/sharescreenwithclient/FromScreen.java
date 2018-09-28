/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharescreenwithclient;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Roy
 */
public class FromScreen extends JFrame
{
    private JButton server,client;
    private Font font;
    
    public FromScreen()
    {
        initp();
    }
    public void initp()
    {
        font = new Font("Bahnschrift SemiLight", Font.BOLD, 60);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Share Screen With Clients");
        
        // make full screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(screenSize);
        
        this.setLayout(null);
        
        server=new JButton("Make Server");
        server.setBounds(200, 300, 400, 80);
        server.setFont(font);
        this.add(server);
        
        client=new JButton("Make Client");
        client.setBounds(700, 300, 400, 80);
        client.setFont(font);
        this.add(client);
        
        // Handing buttons
        class Handler implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
               if(e.getSource()==server)
               {
                     EventQueue.invokeLater(() -> {
                         new Server().setVisible(true);
                     });
                     dispose();
               }
               else if(e.getSource()==client)
               {
                    EventQueue.invokeLater(() -> {
                         new Clients().setVisible(true);
                     });
                     dispose();
               }
            }
            
        }
        
        Handler h=new Handler();
        server.addActionListener(h);
        client.addActionListener(h);
        
    }
}

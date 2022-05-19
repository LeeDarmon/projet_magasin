import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class WindowSwingSiege {
    public JMenuBar jmb;
    public JPanel jp;
    public SiegeServer ms;
    
  public WindowSwingSiege() {    
     
    jp = new JPanel();
    jmb = new JMenuBar();

    jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
    Box box1 = new Box(BoxLayout.X_AXIS);
    Box box2 = new Box(BoxLayout.X_AXIS);
    Box box3 = new Box(BoxLayout.X_AXIS);
    Box box4 = new Box(BoxLayout.X_AXIS);
    Box box5 = new Box(BoxLayout.X_AXIS);
    
    JButton stopServ = new JButton("Stop server");
    stopServ.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ms.stop();
            } catch (NoSuchObjectException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    });
    box1.add(stopServ);
    
    JButton startServ = new JButton("Start server");
    startServ.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           try {
            ms.initialize();
        } catch (AccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (AlreadyBoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        }
    });
    box1.add(startServ);
    
    JButton connectButton = new JButton("Connect to magasin");
    connectButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           try {
            if(ms.connect()) {
                JOptionPane.showMessageDialog(null, "Successfully logged to magasins");  
            } else {

                JOptionPane.showMessageDialog(null, "Failed connecting");  
            }
            
        } catch (AccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (AlreadyBoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (NotBoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        }
    });
    box1.add(connectButton);
    
    
    
    JButton getCommandes = new JButton("Get commandes");
    getCommandes.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                
                if(ms.fetch() == true) {
                    JOptionPane.showMessageDialog(null, "Commandes inserted successfully");   
                } else {
                    JOptionPane.showMessageDialog(null, "Error with commandes");   
                     }
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    });
    box1.add(getCommandes);

    
    JButton UpdateButton = new JButton("Update server price");
    UpdateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                try {
                    if(ms.update()) {
                        JOptionPane.showMessageDialog(null, "Update prices successfully");   
                    } else {
                        JOptionPane.showMessageDialog(null, "Update prices failed");   
                    }
                    
                    
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        }
    });
    box1.add(UpdateButton);
    box2.add(new JSeparator());

    box2.add(Box.createRigidArea(new Dimension(15, 0)));
    jp.add(box1);
    jp.add(box2);
    jp.add(box3);
    jp.add(box4);
  }

  public static void main(String s[]) {
      WindowSwingSiege ws = new WindowSwingSiege();
    JFrame frame = new JFrame("Siege");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(ws.jp);
    frame.setJMenuBar(ws.jmb);
    String strtoPath = System.getProperty("user.dir") + "\\res\\icon.jpg";
    Image icon = Toolkit.getDefaultToolkit().getImage(strtoPath);    
    frame.setIconImage(icon);   
    frame.setMinimumSize(new Dimension(250, 200));
    frame.pack();
    frame.setVisible(true);
    try { 
        SiegeServer ss = new SiegeServer();
        ws.ms = ss;
     } catch (Exception e) { 
        System.err.println(e.toString()); 
        e.printStackTrace(); 
     }     
  }
}
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

public class WindowSwingMagasin {
    public JMenuBar jmb;
    public JPanel jp;
    public MagasinServer ms;
    
  public WindowSwingMagasin() {    
     
    // Listener générique qui affiche l'action du menu utilisé
    ActionListener afficherMenuListener = new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        System.out.println("Elément de menu [" + event.getActionCommand()
            + "] utilisé.");
      }
    };
    jp = new JPanel();
    jmb = new JMenuBar();
    // Création du menu Fichier
    JMenu fichierMenu = new JMenu("Fichier");
    JMenuItem item = new JMenuItem("Nouveau", 'N');
    item.addActionListener(afficherMenuListener);
    fichierMenu.add(item);
    item = new JMenuItem("Ouvrir", 'O');
    item.addActionListener(afficherMenuListener);
    fichierMenu.add(item);
    item = new JMenuItem("Sauver", 'S');
    item.addActionListener(afficherMenuListener);
    fichierMenu.insertSeparator(1);
    fichierMenu.add(item);
    item = new JMenuItem("Quitter");
    item.addActionListener(afficherMenuListener);
    fichierMenu.add(item);

    // Création du menu Editer
    JMenu editerMenu = new JMenu("Editer");
    item = new JMenuItem("Copier");
    item.addActionListener(afficherMenuListener);
    item.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit()
        .getMenuShortcutKeyMask(), false));
    editerMenu.add(item);
    item = new JMenuItem("Couper");
    item.addActionListener(afficherMenuListener);
    item.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit()
        .getMenuShortcutKeyMask(), false));
    editerMenu.add(item);
    item = new JMenuItem("Coller");
    item.addActionListener(afficherMenuListener);
    item.setAccelerator(KeyStroke.getKeyStroke('V', Toolkit.getDefaultToolkit()
        .getMenuShortcutKeyMask(), false));
    editerMenu.add(item);

    // Création du menu Divers
    JMenu diversMenu = new JMenu("Divers");
    JMenu sousMenuDiver1 = new JMenu("Sous menu 1");

    item.addActionListener(afficherMenuListener);
    item = new JMenuItem("Sous menu 1 1");
    sousMenuDiver1.add(item);
    item.addActionListener(afficherMenuListener);
    JMenu sousMenuDivers2 = new JMenu("Sous menu 1 2");
    item = new JMenuItem("Sous menu 1 2 1");
    sousMenuDivers2.add(item);
    sousMenuDiver1.add(sousMenuDivers2);

    diversMenu.add(sousMenuDiver1);
    item = new JCheckBoxMenuItem("Validé");
    diversMenu.add(item);
    item.addActionListener(afficherMenuListener);
    diversMenu.addSeparator();
    ButtonGroup buttonGroup = new ButtonGroup();
    item = new JRadioButtonMenuItem("Cas 1");
    diversMenu.add(item);
    item.addActionListener(afficherMenuListener);
    buttonGroup.add(item);
    item = new JRadioButtonMenuItem("Cas 2");
    diversMenu.add(item);
    item.addActionListener(afficherMenuListener);
    buttonGroup.add(item);
    diversMenu.addSeparator();
    diversMenu.add(item = new JMenuItem("Autre",
        new ImageIcon("about_32.png")));
    item.addActionListener(afficherMenuListener);

    // ajout des menus à la barre de menus
    jmb.add(fichierMenu);
    jmb.add(editerMenu);
    jmb.add(diversMenu);

    jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
    Box box1 = new Box(BoxLayout.X_AXIS);
    Box box2 = new Box(BoxLayout.X_AXIS);
    Box box3 = new Box(BoxLayout.X_AXIS);
    Box box4 = new Box(BoxLayout.X_AXIS);
    Box box5 = new Box(BoxLayout.X_AXIS);
    
    JButton UpdateButton = new JButton("Update server price");
    UpdateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ms.Update();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    });
    box1.add(UpdateButton);
    
    JButton stopServ = new JButton("Stop server");
    stopServ.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ms.Stop();
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
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        }
    });
    box1.add(startServ);


    JButton CAButton = new JButton("See CA");
    CAButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            java.sql.Date date = null;
            JDatePickerImpl a= null;
            JPanel datePanel = getDatePanel();
            a = (JDatePickerImpl) datePanel.getComponent(0);
            int reply = JOptionPane.showConfirmDialog(null,
                    datePanel,
                    "Choose date for CA : ",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            date = (java.sql.Date) a.getModel().getValue();
            if (reply == JOptionPane.YES_OPTION) {
                try {
                    String res = "CA :" +  ms.getImplcm().getChiffreAffaire(date.toString());
                    JOptionPane.showMessageDialog(null, res + " " + date.toString());
                } catch (HeadlessException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {

            }  
        }
    });
    box1.add(CAButton);
    
    box2.add(new JSeparator());

    box2.add(Box.createRigidArea(new Dimension(15, 0)));
    JTextField searchArticleArea = new JTextField();
    box3.add(new JLabel("Search article : "));
    box3.add(searchArticleArea);
    JTextField searchFactureArea = new JTextField();
    box3.add(new JLabel("Search facture : "));
    box3.add(searchFactureArea);
    box4.add(Box.createRigidArea(new Dimension(15, 0)));
    box4.add(new JSeparator());

    
    JButton addStockButton = new JButton("Add Stock");
    addStockButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String art = searchArticleArea.getText();
            int stock = 0;
            JFormattedTextField a= null;
            JPanel inputPanel = getInputPanel();
            a = (JFormattedTextField) inputPanel.getComponent(1);
            int reply = JOptionPane.showConfirmDialog(null,
                    inputPanel,
                    "Choose stock : ",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            stock = (int) a.getValue();
            if (reply == JOptionPane.YES_OPTION) {
                try {
                    ms.getImplcm().addStock(art, stock);
                    JOptionPane.showMessageDialog(null, "Stock updated !");
                } catch (HeadlessException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {

            }  
        }
    });
    box5.add(addStockButton);
    
    JButton searchFactureButton = new JButton("Search Facture");
    searchFactureButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    });
    box5.add(searchFactureButton);
    
    jp.add(box1);
    jp.add(box2);
    jp.add(box3);
    jp.add(box4);
    jp.add(box5);
  }

  private JPanel getDatePanel() {
      JPanel panel = new JPanel();
      JLabel label = new JLabel("Choose a date");
      SqlDateModel model = new SqlDateModel();
      Properties propierties = new Properties();
      propierties.put("text.today", "Today");
      propierties.put("text.month", "Month");
      propierties.put("text.year", "Year");
      JDatePanelImpl datePanel = new JDatePanelImpl(model, propierties);
      JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,  new DateLabelFormatter());
      panel.add(datePicker);
      panel.add(label);
      
      return panel;
}
   
  private JPanel getInputPanel() {

      JPanel panel = new JPanel();
      JLabel label = new JLabel("How many stock to add ?");
      panel.add(label);
      NumberFormat format = NumberFormat.getInstance();
      NumberFormatter formatter = new NumberFormatter(format);
      formatter.setValueClass(Integer.class);
      formatter.setMinimum(0);
      formatter.setMaximum(Integer.MAX_VALUE);
      formatter.setAllowsInvalid(false);
      JFormattedTextField field = new JFormattedTextField(formatter);

      field.setColumns(10);
      panel.add(field);
      
      return panel;
      
  }
  
  protected MaskFormatter createFormatter(String s) {
      MaskFormatter formatter = null;
      try {
          formatter = new MaskFormatter(s);
      } catch (java.text.ParseException exc) {
          System.err.println("formatter is bad: " + exc.getMessage());
          System.exit(-1);
      }
      return formatter;
  }
  public class DateLabelFormatter extends AbstractFormatter {
   
      private String datePattern = "dd-MM-yyyy";
      private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
       
      @Override
      public Object stringToValue(String text) throws ParseException {
          return dateFormatter.parseObject(text);
      }
   
      @Override
      public String valueToString(Object value) throws ParseException {
          if (value != null) {
              Calendar cal = (Calendar) value;
              return dateFormatter.format(cal.getTime());
          }
           
          return "";
      }
   
  }
public static void main(String s[]) {
      WindowSwingMagasin ws = new WindowSwingMagasin();
    JFrame frame = new JFrame("Magasin");
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
        MagasinServer ss = new MagasinServer();
        ws.ms = ss;
     } catch (Exception e) { 
        System.err.println(e.toString()); 
        e.printStackTrace(); 
     }     
  }
}
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/*
 * Interface graphique pour le client.
 * Comporte quatre bouton : See cart, buy cart, Search by family, search 
 * Aucun traitement n'est fait du cote client, seulement des appels de methodes distants. 
 */
public class WindowSwingClient {
    public JMenuBar jmb;
    public JPanel jp;
    public Client ms;
    
  public WindowSwingClient() {    
    jp = new JPanel();
    jmb = new JMenuBar();
    
    jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
    Box box1 = new Box(BoxLayout.X_AXIS);
    Box box2 = new Box(BoxLayout.X_AXIS);
    Box box3 = new Box(BoxLayout.X_AXIS);
    Box box4 = new Box(BoxLayout.X_AXIS);
    Box box5 = new Box(BoxLayout.X_AXIS);
    
    JButton seeCartButton = new JButton("See cart");
    seeCartButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, ms.commandeActuel.toString());
        }
    });
    box1.add(seeCartButton);
    
    JButton buyCartButton = new JButton("Buy cart");
    buyCartButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] list = {"CB", "espèces"};
            JComboBox jcb = new JComboBox(list);
            jcb.setEditable(true);
            JOptionPane.showMessageDialog( null, 
                    jcb, 
                    "Select method of payment", 
                    JOptionPane.QUESTION_MESSAGE);
            
            
            try {
                ms.commandeActuel.setMethode_paiement(jcb.getSelectedItem().toString());
                ms.buyArticle();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, ms.commandeActuel.toString());
        }
    });
    box1.add(buyCartButton);
    
    box2.add(new JSeparator());
    
    box3.add(new JLabel("Search article"));
    JTextField searchField = new JTextField("");
    searchField.setSize(5, 5);
    box3.add(searchField);
    box4.add(new JSeparator());
    
    
    JButton searchFamilyButton = new JButton("Search by family");
    searchFamilyButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String textFieldValue = searchField.getText();
            try {
                List<Article> result = ms.stubArticle.getArticleFamille(textFieldValue);
                if(result != null) {
                    
                    JOptionPane.showMessageDialog(null, showResultsPanel(result));
                } else {

                    JOptionPane.showMessageDialog(null, "Not found");
                }
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    });
    box5.add(searchFamilyButton);
    
    JButton searchButton = new JButton("Search");
    searchButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String textFieldValue = searchField.getText();
            try {
                if(ms.stubArticle.getArticle(textFieldValue).getReference() != null) {
                        int reply = JOptionPane.showConfirmDialog(null, "Add it to cart ?", 
                                "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            ms.addArticle(textFieldValue);
                            JOptionPane.showMessageDialog(null, "Added");
                        } else {

                        }   
                //    }
                } else {

                    JOptionPane.showMessageDialog(null, "Not found");
                }
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    });
    box5.add(searchButton);
    jp.add(box1);
    jp.add(box2);
    jp.add(box3);
    jp.add(box4);
    jp.add(box5);
    jp.validate();
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

  private JPanel showResultsPanel(List<Article> res) {
      JPanel panel = new JPanel();
      JLabel label = new JLabel("Choose an article : ");
      panel.add(label);
      panel.setAlignmentX(Component.CENTER_ALIGNMENT);
      
      for(int i = 0; i < res.size(); i++) {
          String ref =res.get(i).getReference();
          JButton art = new JButton(ref);
          
          art.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  try {
                    ms.addArticle(ref);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
              }
          });
          
          panel.add(art);
          panel.setAlignmentX(Component.CENTER_ALIGNMENT);
      }
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      panel.validate();
      return panel;
  }
  
  public static void main(String s[]) throws Exception {
      WindowSwingClient ws = new WindowSwingClient();
    JFrame frame = new JFrame("Client");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(ws.jp);
    frame.setJMenuBar(ws.jmb);
    String strtoPath = System.getProperty("user.dir") + "\\res\\icon.jpg";
    Image icon = Toolkit.getDefaultToolkit().getImage(strtoPath);    
    frame.setIconImage(icon);
    ws.ms = new Client();
    ws.ms.initialize();
    frame.setMinimumSize(new Dimension(250, 200));
    frame.pack();
    frame.setVisible(true);
  }
}
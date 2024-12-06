package GUIs;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Leonardo Pontin
 */
public class MainGUI extends JFrame {

    private Container cp;
    JPanel pnCentro = new JPanel();

    private CardLayout cardLayout;

    private final JButton btUnico = new JButton("Tabelas únicas");
    private final JButton bt1Xn = new JButton("Tabelas de 1 para vários");
    private final JButton btnXm = new JButton("Tabelas de vários para vários");

    public MainGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Menu");
        
        cp.add(pnCentro, BorderLayout.CENTER);
        
        pnCentro.setBackground(Color.decode("#b068a5"));
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));
        
        pnCentro.add(btUnico);
        pnCentro.add(bt1Xn);
        pnCentro.add(btnXm);
        
   
        bt1Xn.setVisible(true);
        btUnico.setVisible(true);
        btnXm.setVisible(true);
        
        bt1Xn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UmVariosGUI umVariosGui = new UmVariosGUI();
            }
        });
        
        btUnico.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UnicasGUI unicasGui = new UnicasGUI();
            }
        });
        btnXm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VariosVariosGUI variosVariosGUI = new VariosVariosGUI();
            }
        });
        
        
        

        
        pack();
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);
    }
}

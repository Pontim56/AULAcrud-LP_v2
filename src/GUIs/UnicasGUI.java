/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Leonardo Pontin
 */
public class UnicasGUI extends JFrame{
    
    private Container cp;
    JPanel pnCentro = new JPanel();

    private CardLayout cardLayout;

    private final JButton btCargo = new JButton("CARGO");
    private final JButton btCidade = new JButton("CIDADE");
    private final JButton btMotor = new JButton("MOTOR");
    private final JButton btPrecoSemana = new JButton("PREÇO SEMANA");
     public UnicasGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Menu");
        
        cp.add(pnCentro, BorderLayout.CENTER);
        
        pnCentro.setBackground(Color.decode("#b068a5"));
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));
        
        pnCentro.add(btCargo);
        pnCentro.add(btMotor);
        pnCentro.add(btCidade);
        pnCentro.add(btPrecoSemana);
        
        btCargo.setVisible(true);
        btCidade.setVisible(true);
        btMotor.setVisible(true);
        
        btCargo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                CargoGUI cargoGui = new CargoGUI();
            }
        });
        btCidade.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                CidadeGUI cidadeGui = new CidadeGUI();
            }
        });
        btMotor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MotorGUI motorGui = new MotorGUI();
            }
        });
//        btPrecoSemana.addActionListener(new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                PrecoSemanaGUI precoSemanaGui = new PrecoSemanaGUI();
//            }
//        });

        
        
        
        pack();
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);
    }
    
}

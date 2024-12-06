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
public class UmVariosGUI extends JFrame{
    
    private Container cp;
    JPanel pnCentro = new JPanel();

    private CardLayout cardLayout;

    private final JButton btCarro = new JButton("CARRO");
    private final JButton btPessoa = new JButton("PESSSOA");
    private final JButton btLocacao = new JButton("LOCAÇÃO");
    
    public UmVariosGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Menu");
        
        cp.add(pnCentro, BorderLayout.CENTER);
        
        pnCentro.setBackground(Color.decode("#b068a5"));
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));
        
        pnCentro.add(btCarro);
        pnCentro.add(btPessoa);
        pnCentro.add(btLocacao);
        
        btCarro.setVisible(true);
        btPessoa.setVisible(true);
        btLocacao.setVisible(true);
        
        btCarro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                CarroGUI carroGui = new CarroGUI();
            }
        });
        btPessoa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PessoaGUI pessoaGui = new PessoaGUI();
            }
        });
        btLocacao.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                LocacaoGUI locacaoGUI = new LocacaoGUI();
            }
        });
        

        
        
        pack();
        setSize(400,80);
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);
    }
    
    
}

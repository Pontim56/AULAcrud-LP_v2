package GUIs;
import DAOs.DAOCidade;
import Entidades.Cidade;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.BorderFactory;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;import tools.CaixaDeFerramentas;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;
import java.util.Date;/**
 *
 * @author Leonardo Pontim
21/04/2024 - 13:27:50 */public class CidadeGUI extends JDialog{
Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");String acao = "";private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

DefaultTableCellRenderer coluna1 = new DefaultTableCellRenderer();    private CardLayout cardLayout;

//////////////////// - mutável - /////////////////////////
JLabel lbId_cidade = new JLabel("Id_cidade");
JTextField tfId_cidade = new JTextField(10);
JLabel lbNome_cidade = new JLabel("Nome_cidade");
JTextField tfNome_cidade = new JTextField(45);
JLabel[] lbVazio = new JLabel[99];DAOCidade daoCidade = new DAOCidade();
    Cidade cidade = new Cidade();String[] colunas = new String[]{"id_cidade","nome_cidade"};String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model); public CidadeGUI() {
 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas()
;        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Cidade");


tabela.setEnabled(false);
        //tamanho da tabela
        tabela.setRowHeight(20);
        
        //cabeçalho
        tabela.getTableHeader().setBackground(Color.decode("#b068a5"));
        tabela.getTableHeader().setForeground(Color.decode("#111111"));
        
        //cor da linha da tabela
        tabela.setGridColor(new Color(0, 0, 0));
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.decode("#b068a5"));
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT)); pnNorte.add(lbId_cidade);
        pnNorte.add(tfId_cidade);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btCancelar.setVisible(false);tfNome_cidade.setEditable(false);pnCentro.setLayout(new GridLayout(colunas.length-1, 2));
lbNome_cidade.setHorizontalAlignment(SwingConstants.CENTER);

for (int i = 0; i < 99; i++) {
     lbVazio[i] = new JLabel("");
        }pnCentro.add(lbNome_cidade);
pnCentro.add(tfNome_cidade); cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnVazio, "vazio");
        pnSul.add(pnAvisos, "avisos");
        pnSul.add(pnListagem, "listagem");
        tabela.setEnabled(false);


// listener Buscar
 btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");try{  cidade = daoCidade.obter(Integer.valueOf(tfId_cidade.getText())); if (cidade != null) {//achou o cidade na lista
                    //mostrar
                    btAdicionar.setVisible(false);
                    btAlterar.setVisible(true);
                    btExcluir.setVisible(true);tfNome_cidade.setText(String.valueOf(cidade.getNomeCidade()));
tfNome_cidade.setEditable(false);
 } else {//não achou na lista
                    //mostrar botão incluir
                    btAdicionar.setVisible(true);
                    btAlterar.setVisible(false);
                    btExcluir.setVisible(false);tfNome_cidade.setText("");                 
                    tfNome_cidade.setEditable(false); }} catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro, Digite Novamente!", "Erro ao buscar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// listener Adicionar
 btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {tfId_cidade.setEnabled(false);tfNome_cidade.requestFocus(); tfNome_cidade.setEditable(true); btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionar";
            }
        });

// listener Salvar
 btSalvar.addActionListener(new ActionListener() {
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("adicionar")) {
                    cidade = new Cidade();
                }
                Cidade cidadeAntigo = cidade;try{cidade.setIdCidade(Integer.valueOf(tfId_cidade.getText()));cidade.setNomeCidade(tfNome_cidade.getText());if (acao.equals("adicionar")) {
                    daoCidade.inserir(cidade);
                } else {
                    daoCidade.atualizar(cidade);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true); tfId_cidade.setEnabled(true);
                tfId_cidade.setEditable(true);
                tfId_cidade.requestFocus();
                tfId_cidade.setText("");tfId_cidade.setText("");
 tfNome_cidade.setEnabled(true);
                tfNome_cidade.setEditable(true);
                tfNome_cidade.requestFocus();
                tfNome_cidade.setText("");tfNome_cidade.setText("");
} catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro, Digite Novamente!", "Erro ao buscar", JOptionPane.PLAIN_MESSAGE);

                }
            }
        });
 
 

// listener Alterar
 btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);tfId_cidade.setEditable(false);
 tfNome_cidade.requestFocus();tfNome_cidade.setEditable(true);
 btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
tfId_cidade.setEnabled(true);                btExcluir.setVisible(false);
                acao = "alterar";

            }
        });

// listener Excluir
  btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);tfId_cidade.setEnabled(true);
tfId_cidade.setEditable(true);
tfId_cidade.requestFocus();
tfId_cidade.setText(""); tfNome_cidade.setText(""); tfNome_cidade.setEditable(false); btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoCidade.remover(cidade);
                }
            }
        });

// listener Listar
 btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Cidade> listaCidade = daoCidade.listInOrderNome();String[] colunas = new String[]{"Id_cidade","Nome_cidade"};String[][] dados = new String[listaCidade.size()][colunas.length];
String aux[];
                for (int i = 0; i < listaCidade.size(); i++) {
                    aux = listaCidade.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                } cardLayout.show(pnSul, "listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
tfNome_cidade.setEditable(false);//cor do background e da letra de cada coluna
coluna1.setBackground(Color.decode("#FFB0CF"));
coluna1.setForeground(Color.decode("#111111"));
coluna1.setHorizontalAlignment(SwingConstants.CENTER);
tabela.getColumnModel().getColumn(0).setCellRenderer(coluna1);
tabela.getColumnModel().getColumn(1).setCellRenderer(coluna1);
            }
        });

// listener Cancelar
 btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);tfId_cidade.setText("");
                tfId_cidade.requestFocus();
tfId_cidade.setEnabled(true);
tfId_cidade.setEditable(true);tfNome_cidade.setText(""); tfNome_cidade.setEditable(false); btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);

            }
        });

// listener ao fechar o programa
 addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em armazenamento permanente
                // Sai da classe
                dispose();
            }
        });

        setModal(true);
        pack();
        setSize(600,400);
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);}//fim do contrutor de GUI
} //fim da classe
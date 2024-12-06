package GUIs;
import DAOs.DAOCargo;
import Entidades.Cargo;
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
21/04/2024 - 14:25:47 */public class CargoGUI extends JDialog{
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
JLabel lbId_cargo = new JLabel("Id_cargo");
JTextField tfId_cargo = new JTextField(10);
JLabel lbNome_cargo = new JLabel("Nome_cargo");
JTextField tfNome_cargo = new JTextField(45);
JLabel lbRenda = new JLabel("Renda");
JTextField tfRenda = new JTextField(45);
JLabel[] lbVazio = new JLabel[99];DAOCargo daoCargo = new DAOCargo();
    Cargo cargo = new Cargo();String[] colunas = new String[]{"id_cargo","nome_cargo","renda"};String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model); public CargoGUI() {
 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas()
;        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Cargo");


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

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT)); pnNorte.add(lbId_cargo);
        pnNorte.add(tfId_cargo);
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
        btCancelar.setVisible(false);tfNome_cargo.setEditable(false);tfRenda.setEditable(false);pnCentro.setLayout(new GridLayout(colunas.length-1, 2));
lbNome_cargo.setHorizontalAlignment(SwingConstants.CENTER);
lbRenda.setHorizontalAlignment(SwingConstants.CENTER);

for (int i = 0; i < 99; i++) {
     lbVazio[i] = new JLabel("");
        }pnCentro.add(lbNome_cargo);
pnCentro.add(tfNome_cargo);pnCentro.add(lbRenda);
pnCentro.add(tfRenda); cardLayout = new CardLayout();
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
                cardLayout.show(pnSul, "avisos");try{  cargo = daoCargo.obter(Integer.valueOf(tfId_cargo.getText())); if (cargo != null) {//achou o cargo na lista
                    //mostrar
                    btAdicionar.setVisible(false);
                    btAlterar.setVisible(true);
                    btExcluir.setVisible(true);tfNome_cargo.setText(String.valueOf(cargo.getNomeCargo()));
tfNome_cargo.setEditable(false);
tfRenda.setText(String.valueOf(cargo.getRenda()));
tfRenda.setEditable(false);
 } else {//não achou na lista
                    //mostrar botão incluir
                    btAdicionar.setVisible(true);
                    btAlterar.setVisible(false);
                    btExcluir.setVisible(false);tfNome_cargo.setText("");                 
                    tfNome_cargo.setEditable(false); tfRenda.setText("");                 
                    tfRenda.setEditable(false); }} catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro, Digite Novamente!", "Erro ao buscar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// listener Adicionar
 btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {tfId_cargo.setEnabled(false);tfNome_cargo.requestFocus(); tfNome_cargo.setEditable(true); tfRenda.setEditable(true); btAdicionar.setVisible(false);
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
                    cargo = new Cargo();
                }
                Cargo cargoAntigo = cargo;try{cargo.setIdCargo(Integer.valueOf(tfId_cargo.getText()));cargo.setNomeCargo(tfNome_cargo.getText());cargo.setRenda(tfRenda.getText());if (acao.equals("adicionar")) {
                    daoCargo.inserir(cargo);
                } else {
                    daoCargo.atualizar(cargo);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true); tfId_cargo.setEnabled(true);
                tfId_cargo.setEditable(true);
                tfId_cargo.requestFocus();
                tfId_cargo.setText("");tfId_cargo.setText("");
 tfNome_cargo.setEnabled(true);
                tfNome_cargo.setEditable(true);
                tfNome_cargo.requestFocus();
                tfNome_cargo.setText("");tfNome_cargo.setText("");
 tfRenda.setEnabled(true);
                tfRenda.setEditable(true);
                tfRenda.requestFocus();
                tfRenda.setText("");tfRenda.setText("");
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
                btAlterar.setVisible(false);tfId_cargo.setEditable(false);
 tfNome_cargo.requestFocus();tfNome_cargo.setEditable(true);
tfRenda.setEditable(true);
 btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
tfId_cargo.setEnabled(true);                btExcluir.setVisible(false);
                acao = "alterar";

            }
        });

// listener Excluir
  btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);tfId_cargo.setEnabled(true);
tfId_cargo.setEditable(true);
tfId_cargo.requestFocus();
tfId_cargo.setText(""); tfNome_cargo.setText(""); tfNome_cargo.setEditable(false);tfRenda.setText(""); tfRenda.setEditable(false); btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoCargo.remover(cargo);
                }
            }
        });

// listener Listar
 btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Cargo> listaCargo = daoCargo.listInOrderNome();String[] colunas = new String[]{"Id_cargo","Nome_cargo","Renda"};String[][] dados = new String[listaCargo.size()][colunas.length];
String aux[];
                for (int i = 0; i < listaCargo.size(); i++) {
                    aux = listaCargo.get(i).toString().split(";");
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
tfNome_cargo.setEditable(false);tfRenda.setEditable(false);//cor do background e da letra de cada coluna
coluna1.setBackground(Color.decode("#FFB0CF"));
coluna1.setForeground(Color.decode("#111111"));
coluna1.setHorizontalAlignment(SwingConstants.CENTER);
tabela.getColumnModel().getColumn(0).setCellRenderer(coluna1);
tabela.getColumnModel().getColumn(1).setCellRenderer(coluna1);
tabela.getColumnModel().getColumn(2).setCellRenderer(coluna1);
            }
        });

// listener Cancelar
 btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);tfId_cargo.setText("");
                tfId_cargo.requestFocus();
tfId_cargo.setEnabled(true);
tfId_cargo.setEditable(true);tfNome_cargo.setText(""); tfNome_cargo.setEditable(false);tfRenda.setText(""); tfRenda.setEditable(false); btBuscar.setVisible(true);
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
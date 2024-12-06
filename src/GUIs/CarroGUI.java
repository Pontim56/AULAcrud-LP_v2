package GUIs;

import DAOs.DAOCarro;
import DAOs.DAOMotor;
import Entidades.Carro;
import Entidades.Motor;
import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import javax.swing.table.DefaultTableModel;
import tools.CaixaDeFerramentas;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;
import java.util.Date;
import tools.JanelaPesquisar;

/**
 *
 * @author Leonardo Pontim 08/10/2024 - 15:52:45
 */
public class CarroGUI extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");
    String acao = "";
    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    DefaultTableCellRenderer coluna1 = new DefaultTableCellRenderer();
    private CardLayout cardLayout;

//////////////////// - mutável - /////////////////////////
    JLabel lbIdCarro = new JLabel("IdCarro");
    JTextField tfIdCarro = new JTextField(20);
    JLabel lbNome_carro = new JLabel("Nome_carro");
    JTextField tfNome_carro = new JTextField(50);
    JLabel lbMotor_id_motor = new JLabel("Motor_id_motor");
    JTextField tfMotor_id_motor = new JTextField(20);
    JLabel[] lbVazio = new JLabel[99];
    DAOCarro daoCarro = new DAOCarro();
    Carro carro = new Carro();
    String[] colunas = new String[]{"idCarro", "nome_carro", "motor_id_motor"};
    String[][] dados = new String[0][colunas.length];

    DAOMotor daoMotor = new DAOMotor();

    Motor motor = new Motor();

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public CarroGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Carro");

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

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdCarro);
        pnNorte.add(tfIdCarro);
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
        btCancelar.setVisible(false);
        tfNome_carro.setEditable(false);
        tfMotor_id_motor.setEditable(false);
        pnCentro.setLayout(new GridLayout(colunas.length - 1, 2));
        lbNome_carro.setHorizontalAlignment(SwingConstants.CENTER);
        lbMotor_id_motor.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 99; i++) {
            lbVazio[i] = new JLabel("");
        }
        pnCentro.add(lbNome_carro);
        pnCentro.add(tfNome_carro);
        pnCentro.add(lbMotor_id_motor);
        pnCentro.add(tfMotor_id_motor);
        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnVazio, "vazio");
        pnSul.add(pnAvisos, "avisos");
        pnSul.add(pnListagem, "listagem");
        tabela.setEnabled(false);

        pnAvisos.add(new JLabel("Avisos"));
        String caminho = "Carro.csv";
        //carregar dados do HD para memória RAM

// listener Buscar
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                try {
                    carro = daoCarro.obter(Integer.valueOf(tfIdCarro.getText()));
                    if (carro != null) {//achou o carro na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNome_carro.setText(String.valueOf(carro.getNomeCarro()));
                        tfNome_carro.setEditable(false);
                        tfMotor_id_motor.setText(String.valueOf(carro.getMotorIdMotor()));
                        tfMotor_id_motor.setEditable(false);
                    } else {//não achou na lista
                        //mostrar botão incluir
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNome_carro.setText("");
                        tfNome_carro.setEditable(false);
                        tfMotor_id_motor.setText("");
                        tfMotor_id_motor.setEditable(false);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro, Digite Novamente!", "Erro ao buscar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// listener Adicionar
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdCarro.setEnabled(false);
                tfNome_carro.requestFocus();
                tfNome_carro.setEditable(true);
                tfMotor_id_motor.setEditable(true);
                btAdicionar.setVisible(false);
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
                    carro = new Carro();
                }
                Carro carroAntigo = carro;
                try {
                    carro.setIdCarro(Integer.valueOf(tfIdCarro.getText()));
                    carro.setNomeCarro(tfNome_carro.getText());
                    carro.setMotorIdMotor(daoMotor.obter(Integer.valueOf(tfMotor_id_motor.getText())));
                    if (acao.equals("adicionar")) {
                        daoCarro.inserir(carro);
                    } else {
                        daoCarro.atualizar(carro);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdCarro.setEnabled(true);
                    tfIdCarro.setEditable(true);
                    tfIdCarro.requestFocus();
                    tfIdCarro.setText("");
                    tfIdCarro.setText("");
                    tfNome_carro.setEnabled(true);
                    tfNome_carro.setEditable(true);
                    tfNome_carro.requestFocus();
                    tfNome_carro.setText("");
                    tfNome_carro.setText("");
                    tfMotor_id_motor.setEnabled(true);
                    tfMotor_id_motor.setEditable(true);
                    tfMotor_id_motor.requestFocus();
                    tfMotor_id_motor.setText("");
                    tfMotor_id_motor.setText("");
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
                btAlterar.setVisible(false);
                tfIdCarro.setEditable(false);
                tfNome_carro.requestFocus();
                tfNome_carro.setEditable(true);
                tfMotor_id_motor.setEditable(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                tfIdCarro.setEnabled(true);
                btExcluir.setVisible(false);
                acao = "alterar";

            }
        });

// listener Excluir
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfIdCarro.setEnabled(true);
                tfIdCarro.setEditable(true);
                tfIdCarro.requestFocus();
                tfIdCarro.setText("");
                tfNome_carro.setText("");
                tfNome_carro.setEditable(false);
                tfMotor_id_motor.setText("");
                tfMotor_id_motor.setEditable(false);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoCarro.remover(carro);
                }
            }
        });

// listener Listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Carro> listaCarro = daoCarro.list();
                String[] colunas = new String[]{"IdCarro", "Nome_carro", "Motor_id_motor"};
                String[][] dados = new String[listaCarro.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaCarro.size(); i++) {
                    aux = listaCarro.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(pnSul, "listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
                tfNome_carro.setEditable(false);
                tfMotor_id_motor.setEditable(false);//cor do background e da letra de cada coluna
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
                btCancelar.setVisible(false);
                tfIdCarro.setText("");
                tfIdCarro.requestFocus();
                tfIdCarro.setEnabled(true);
                tfIdCarro.setEditable(true);
                tfNome_carro.setText("");
                tfNome_carro.setEditable(false);
                tfMotor_id_motor.setText("");
                tfMotor_id_motor.setEditable(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);

            }
        });
        tfMotor_id_motor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoMotor.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            tfMotor_id_motor.getBounds().y + tfMotor_id_motor.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
//                        tfMotor_id_motor.setText(selectedItem);
                        tfMotor_id_motor.setText(aux[0]);

                        //preparar para salvar
                        motor = daoMotor.obter(Integer.valueOf(aux[0]));

                    } else {
                        tfMotor_id_motor.requestFocus();
                        tfMotor_id_motor.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
                }
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
        setSize(600, 400);
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);
    }//fim do contrutor de GUI
} //fim da classe

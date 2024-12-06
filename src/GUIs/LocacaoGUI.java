package GUIs;

import DAOs.DAOCliente;
import DAOs.DAOLocacao;
import Entidades.Cliente;
import Entidades.Locacao;
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
import javax.swing.table.DefaultTableModel;
import tools.CaixaDeFerramentas;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;
import java.util.Date;
import tools.JanelaPesquisar;

/**
 *
 * @author Leonardo Pontim 30/11/2024 - 16:36:41
 */
public class LocacaoGUI extends JDialog {

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
    Date data;
    String acao = "";
    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    DefaultTableCellRenderer coluna1 = new DefaultTableCellRenderer();
    private CardLayout cardLayout;

//////////////////// - mutável - /////////////////////////
    JLabel lbIdLocacao = new JLabel("IdLocacao");
    JTextField tfIdLocacao = new JTextField(20);
    JLabel lbDataLocacao = new JLabel("DataLocacao");
    JTextField tfDataLocacao = new JTextField(20);
    JLabel lbClientePessoaCpfPessoa = new JLabel("ClientePessoaCpfPessoa");
    JTextField tfClientePessoaCpfPessoa = new JTextField(20);
    JLabel[] lbVazio = new JLabel[99];
    DAOLocacao daoLocacao = new DAOLocacao();
    Locacao locacao = new Locacao();
    String[] colunas = new String[]{"idLocacao", "dataLocacao", "clientePessoaCpfPessoa"};
    String[][] dados = new String[0][colunas.length];
    
    DAOCliente daoCliente = new DAOCliente();
    Cliente cliente = new Cliente();

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public LocacaoGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Locacao");

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
        pnNorte.add(lbIdLocacao);
        pnNorte.add(tfIdLocacao);
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
        tfDataLocacao.setEditable(false);
        tfClientePessoaCpfPessoa.setEditable(false);
        pnCentro.setLayout(new GridLayout(colunas.length - 1, 2));
        lbDataLocacao.setHorizontalAlignment(SwingConstants.CENTER);
        lbClientePessoaCpfPessoa.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 99; i++) {
            lbVazio[i] = new JLabel("");
        }
        pnCentro.add(lbDataLocacao);
        pnCentro.add(tfDataLocacao);
        pnCentro.add(lbClientePessoaCpfPessoa);
        pnCentro.add(tfClientePessoaCpfPessoa);
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
        String caminho = "Locacao.csv";

// listener Buscar
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                try {
                    locacao = daoLocacao.obter(Integer.valueOf(tfIdLocacao.getText()));
                    if (locacao != null) {//achou o locacao na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfDataLocacao.setText(new SimpleDateFormat("dd/MM/yyyy").format(locacao.getDataLocacao()));
                        tfClientePessoaCpfPessoa.setText(String.valueOf(locacao.getClientePessoaCpfPessoa()));
                        tfClientePessoaCpfPessoa.setEditable(false);
                    } else {//não achou na lista
                        //mostrar botão incluir
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfDataLocacao.setText("");
                        tfDataLocacao.setEditable(false);
                        tfClientePessoaCpfPessoa.setText("");
                        tfClientePessoaCpfPessoa.setEditable(false);
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
                tfIdLocacao.setEnabled(false);
                tfDataLocacao.requestFocus();
                tfDataLocacao.setEditable(true);
                tfClientePessoaCpfPessoa.setEditable(true);
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
                    locacao = new Locacao();
                }
                Locacao locacaoAntigo = locacao;
                try {
                    locacao.setIdLocacao(Integer.valueOf(tfIdLocacao.getText()));
                    sdf.setLenient(false);
                    data = sdf.parse(tfDataLocacao.getText());
                    locacao.setDataLocacao(data);
                    locacao.setDataLocacao(cf.converteDeStringParaDate(tfDataLocacao.getText()));
                    locacao.setClientePessoaCpfPessoa(daoCliente.obter( tfClientePessoaCpfPessoa.getText()));
                    if (acao.equals("adicionar")) {
                        daoLocacao.inserir(locacao);
                    } else {
                        daoLocacao.atualizar(locacao);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdLocacao.setEnabled(true);
                    tfIdLocacao.setEditable(true);
                    tfIdLocacao.requestFocus();
                    tfIdLocacao.setText("");
                    tfIdLocacao.setText("");
                    tfDataLocacao.setEnabled(true);
                    tfDataLocacao.setEditable(true);
                    tfDataLocacao.requestFocus();
                    tfDataLocacao.setText("");
                    tfDataLocacao.setText("");
                    tfClientePessoaCpfPessoa.setEnabled(true);
                    tfClientePessoaCpfPessoa.setEditable(true);
                    tfClientePessoaCpfPessoa.requestFocus();
                    tfClientePessoaCpfPessoa.setText("");
                    tfClientePessoaCpfPessoa.setText("");
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
                tfIdLocacao.setEditable(false);
                tfDataLocacao.requestFocus();
                tfDataLocacao.setEditable(true);
                tfClientePessoaCpfPessoa.setEditable(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                tfIdLocacao.setEnabled(true);
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
                tfIdLocacao.setEnabled(true);
                tfIdLocacao.setEditable(true);
                tfIdLocacao.requestFocus();
                tfIdLocacao.setText("");
                tfDataLocacao.setText("");
                tfDataLocacao.setEditable(false);
                tfClientePessoaCpfPessoa.setText("");
                tfClientePessoaCpfPessoa.setEditable(false);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoLocacao.remover(locacao);
                }
            }
        });

// listener Listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Locacao> listaLocacao = daoLocacao.list();
                String[] colunas = new String[]{"IdLocacao", "DataLocacao", "ClientePessoaCpfPessoa"};
                String[][] dados = new String[listaLocacao.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaLocacao.size(); i++) {
                    aux = listaLocacao.get(i).toString().split(";");
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
                tfDataLocacao.setEditable(false);
                tfClientePessoaCpfPessoa.setEditable(false);//cor do background e da letra de cada coluna
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
                tfIdLocacao.setText("");
                tfIdLocacao.requestFocus();
                tfIdLocacao.setEnabled(true);
                tfIdLocacao.setEditable(true);
                tfDataLocacao.setText("");
                tfDataLocacao.setEditable(false);
                tfClientePessoaCpfPessoa.setText("");
                tfClientePessoaCpfPessoa.setEditable(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);

            }
        });
        tfClientePessoaCpfPessoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoCliente.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            tfClientePessoaCpfPessoa.getBounds().y + tfClientePessoaCpfPessoa.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfClientePessoaCpfPessoa.setText(aux[0]);

                        //preparar para salvar
                        cliente = daoCliente.obter(aux[0]);

                    } else {
                        tfClientePessoaCpfPessoa.requestFocus();
                        tfClientePessoaCpfPessoa.selectAll();
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

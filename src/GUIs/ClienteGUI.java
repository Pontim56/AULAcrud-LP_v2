package GUIs;import DAOs.DAOCliente;
import Entidades.Cliente;
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
22/10/2024 - 19:59:45 */public class ClienteGUI extends JDialog{
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
JLabel lbPessoa_cpf_pessoa = new JLabel("Pessoa_cpf_pessoa");
JTextField tfPessoa_cpf_pessoa = new JTextField(20);
JLabel[] lbVazio = new JLabel[99]; DAOCliente daoCliente = new DAOCliente();
    Cliente cliente = new Cliente();String[] colunas = new String[]{"pessoa_cpf_pessoa"};String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model); public ClienteGUI() {
 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas()
;        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Cliente");


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

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT)); pnNorte.add(lbPessoa_cpf_pessoa);
        pnNorte.add(tfPessoa_cpf_pessoa);
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
        btCancelar.setVisible(false);pnCentro.setLayout(new GridLayout(colunas.length-1, 2));

for (int i = 0; i < 99; i++) {
     lbVazio[i] = new JLabel("");
        } cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnVazio, "vazio");
        pnSul.add(pnAvisos, "avisos");
        pnSul.add(pnListagem, "listagem");
        tabela.setEnabled(false);

        pnAvisos.add(new JLabel("Avisos"));String caminho = "Cliente.csv";
        //carregar dados do HD para memória RAM

// listener Buscar
 btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");try{  cliente = daoCliente.obter(tfPessoa_cpf_pessoa.getText()); if (cliente != null) {//achou o cliente na lista
                    //mostrar
                    btAdicionar.setVisible(false);
                    btAlterar.setVisible(true);
                    btExcluir.setVisible(true); } else {//não achou na lista
                    //mostrar botão incluir
                    btAdicionar.setVisible(true);
                    btAlterar.setVisible(false);
                    btExcluir.setVisible(false);}} catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro, Digite Novamente!", "Erro ao buscar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// listener Adicionar
 btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {tfPessoa_cpf_pessoa.setEnabled(false); btAdicionar.setVisible(false);
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
                    cliente = new Cliente();
                }
                Cliente clienteAntigo = cliente;try{cliente.setPessoaCpfPessoa(tfPessoa_cpf_pessoa.getText());if (acao.equals("adicionar")) {
                   daoCliente.inserir(cliente);
                } else {
                    daoCliente.atualizar(cliente);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true); tfPessoa_cpf_pessoa.setEnabled(true);
                tfPessoa_cpf_pessoa.setEditable(true);
                tfPessoa_cpf_pessoa.requestFocus();
                tfPessoa_cpf_pessoa.setText("");tfPessoa_cpf_pessoa.setText("");
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
////                tfPessoa_cpf_pessoa.setEditable(false);
 btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
tfPessoa_cpf_pessoa.setEnabled(true);                btExcluir.setVisible(false);
                acao = "alterar";

            }
        });

// listener Excluir
  btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);tfPessoa_cpf_pessoa.setEnabled(true);
tfPessoa_cpf_pessoa.setEditable(true);
tfPessoa_cpf_pessoa.requestFocus();
tfPessoa_cpf_pessoa.setText("");  btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoCliente.remover(cliente);
                }
            }
        });

// listener Listar
 btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Cliente> listaCliente = daoCliente.listInOrderNome();String[] colunas = new String[]{"Pessoa_cpf_pessoa"};String[][] dados = new String[listaCliente.size()][colunas.length];
String aux[];
                for (int i = 0; i < listaCliente.size(); i++) {
                    aux = listaCliente.get(i).toString().split(";");
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
//cor do background e da letra de cada coluna
coluna1.setBackground(Color.decode("#FFB0CF"));
coluna1.setForeground(Color.decode("#111111"));
coluna1.setHorizontalAlignment(SwingConstants.CENTER);
tabela.getColumnModel().getColumn(0).setCellRenderer(coluna1);
            }
        });

// listener Cancelar
 btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);tfPessoa_cpf_pessoa.setText("");
                tfPessoa_cpf_pessoa.requestFocus();
tfPessoa_cpf_pessoa.setEnabled(true);
tfPessoa_cpf_pessoa.setEditable(true); btBuscar.setVisible(true);
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
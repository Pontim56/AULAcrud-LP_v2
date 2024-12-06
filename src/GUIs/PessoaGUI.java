package GUIs;

import DAOs.DAOCargo;
import DAOs.DAOCidade;
import DAOs.DAOCliente;
import DAOs.DAOFuncionario;
import Entidades.Pessoa;
import DAOs.DAOPessoa;
import Entidades.Cargo;
import java.awt.BorderLayout;
import Entidades.Cliente;
import Entidades.Funcionario;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
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
 * @author Leonardo Pontim 08/10/2024 - 17:45:35
 */
public class PessoaGUI extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnContainerCentro = new JPanel(new GridLayout(2, 1));
    JPanel pnCentroNorte = new JPanel();
    JPanel pnCentroSul = new JPanel(new GridLayout(1, 2));

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

    private final JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private final JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    DefaultTableCellRenderer coluna1 = new DefaultTableCellRenderer();
    private CardLayout cardLayout;

//////////////////// - mutável - /////////////////////////
    JLabel lbCpf_pessoa = new JLabel("Cpf_pessoa");
    JTextField tfCpf_pessoa = new JTextField(20);
    JLabel lbNome_pessoa = new JLabel("Nome_pessoa");
    JTextField tfNome_pessoa = new JTextField(45);
    JLabel lbData_de_nascimento = new JLabel("Data_de_nascimento");
    JTextField tfData_de_nascimento = new JTextField(50);
    JLabel lbCodigo_pessoa = new JLabel("Codigo_pessoa");
    JTextField tfCodigo_pessoa = new JTextField(45);
    JLabel lbCidade_id_cidade = new JLabel("Cidade_id_cidade");
    JTextField tfCidade_id_cidade = new JTextField(50);

    JLabel lbFuncionario = new JLabel("Funcionario");
    JCheckBox cbFuncionario = new JCheckBox();
    JLabel lbCliente = new JLabel("Cliente");
    JCheckBox cbCliente = new JCheckBox();

    JLabel lbCargo = new JLabel("Cargo");
    JTextField tfCargo_id_cargo = new JTextField(40);

    JLabel[] lbVazio = new JLabel[99];
    DAOPessoa daoPessoa = new DAOPessoa();
//    DAOFuncionario daoFuncionario = new DAOFuncionario();
    Pessoa pessoa = new Pessoa();

    String[] colunas = new String[]{"cpf_pessoa", "nome_pessoa", "data_de_nascimento", "codigo_pessoa", "cidade_id_cidade"};
    String[][] dados = new String[0][colunas.length];

    DAOCidade daoCidade = new DAOCidade();

    DAOCargo daoCargo = new DAOCargo();

    DAOFuncionario daoFuncionario = new DAOFuncionario();
    Funcionario funcionario = new Funcionario();

    DAOCliente daoCliente = new DAOCliente();

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public PessoaGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Pessoa");

        tabela.setEnabled(false);
        //tamanho da tabela
        tabela.setRowHeight(20);

        //cabeçalho
        tabela.getTableHeader().setBackground(Color.decode("#b068a5"));
        tabela.getTableHeader().setForeground(Color.decode("#111111"));

        //cor da linha da tabela
        tabela.setGridColor(new Color(0, 0, 0));
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnContainerCentro, BorderLayout.CENTER);
        pnContainerCentro.add(pnCentroNorte);
        pnContainerCentro.add(pnCentroSul);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.decode("#b068a5"));
        pnCentroNorte.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbCpf_pessoa);
        pnNorte.add(tfCpf_pessoa);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        pnCentroSul.add(lbCliente);
        pnCentroSul.add(cbCliente);
        pnCentroSul.add(lbFuncionario);
        pnCentroSul.add(cbFuncionario);
        pnCentroSul.add(lbCargo);
        pnCentroSul.add(tfCargo_id_cargo);
        tfCargo_id_cargo.setVisible(false);
        tfCargo_id_cargo.setEditable(false);
        lbCargo.setVisible(false);

        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btCancelar.setVisible(false);
        tfNome_pessoa.setEditable(false);
        tfData_de_nascimento.setEditable(false);
        tfCodigo_pessoa.setEditable(false);
        tfCidade_id_cidade.setEditable(false);
        cbFuncionario.setEnabled(false);
        cbCliente.setEnabled(false);
        pnCentroNorte.setLayout(new GridLayout(colunas.length - 1, 2));
        lbNome_pessoa.setHorizontalAlignment(SwingConstants.CENTER);
        lbData_de_nascimento.setHorizontalAlignment(SwingConstants.CENTER);
        lbCodigo_pessoa.setHorizontalAlignment(SwingConstants.CENTER);
        lbCidade_id_cidade.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 99; i++) {
            lbVazio[i] = new JLabel("");
        }
        pnCentroNorte.add(lbNome_pessoa);
        pnCentroNorte.add(tfNome_pessoa);
        pnCentroNorte.add(lbData_de_nascimento);
        pnCentroNorte.add(tfData_de_nascimento);
        pnCentroNorte.add(lbCodigo_pessoa);
        pnCentroNorte.add(tfCodigo_pessoa);
        pnCentroNorte.add(lbCidade_id_cidade);
        pnCentroNorte.add(tfCidade_id_cidade);
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

        cbCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbCliente.isSelected();
                //  labelAviso.setText("cli");
            }
        });
        cbFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbFuncionario.isSelected();
                lbCargo.setVisible(true);
                tfCargo_id_cargo.setVisible(true);
                // labelAviso.setText("func");
            }
        });

// listener Buscar
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                try {
                    pessoa = daoPessoa.obter(tfCpf_pessoa.getText());
                    if (pessoa != null) {//achou o pessoa na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNome_pessoa.setText(String.valueOf(pessoa.getNomePessoa()));
                        tfNome_pessoa.setEditable(false);
                        tfData_de_nascimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(pessoa.getDataDeNascimento()));
                        tfData_de_nascimento.setEditable(false);
                        tfCodigo_pessoa.setText(String.valueOf(pessoa.getCodigoPessoa()));
                        tfCodigo_pessoa.setEditable(false);
                        tfCidade_id_cidade.setText(String.valueOf(pessoa.getCidadeIdCidade()));
                        tfCidade_id_cidade.setEditable(false);

                        tfCargo_id_cargo.setText(String.valueOf(funcionario.getCargoIdCargo()));

                        tfCargo_id_cargo.setEditable(false);

                        Cliente cliente = daoCliente.obter(pessoa.getCpfPessoa());

                        if (cliente != null) { //é cliente
                            cbCliente.setSelected(true);
                        } else {
                            cbCliente.setSelected(false);
                        }

                        Funcionario funcionario = daoFuncionario.obter(pessoa.getCpfPessoa());
                        if (funcionario != null) {
                            cbFuncionario.setSelected(true);
                            lbCargo.setVisible(true);
                            tfCargo_id_cargo.setVisible(true);
                            tfCargo_id_cargo.setText(String.valueOf(funcionario.getCargoIdCargo().getIdCargo()
                                    + "-" + funcionario.getCargoIdCargo().getNomeCargo()));
                        } else {
                            cbFuncionario.setSelected(false);
                            lbCargo.setVisible(false);
                            tfCargo_id_cargo.setVisible(false);
                        }
                    } else {//não achou na lista
                        //mostrar botão incluir
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNome_pessoa.setText("");
                        tfNome_pessoa.setEditable(false);
                        tfData_de_nascimento.setText("");
                        tfData_de_nascimento.setEditable(false);
                        tfCodigo_pessoa.setText("");
                        tfCodigo_pessoa.setEditable(false);
                        tfCidade_id_cidade.setText("");
                        tfCidade_id_cidade.setEditable(false);
                        tfCargo_id_cargo.setText("");
                        tfCargo_id_cargo.setEditable(false);
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
                tfCpf_pessoa.setEnabled(false);
                tfNome_pessoa.requestFocus();
                tfNome_pessoa.setEditable(true);
                tfData_de_nascimento.setEditable(true);
                tfCodigo_pessoa.setEditable(true);
                tfCidade_id_cidade.setEditable(true);
                tfCargo_id_cargo.setEditable(true);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                cbCliente.setEnabled(true);
                cbFuncionario.setEnabled(true);
                acao = "adicionar";
            }
        });

// listener Salvar
        btSalvar.addActionListener(new ActionListener() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("adicionar")) {
                    pessoa = new Pessoa();
                }
                try {
                    pessoa.setCpfPessoa(tfCpf_pessoa.getText());
                    pessoa.setNomePessoa(tfNome_pessoa.getText());
                    sdf.setLenient(false);
                    data = sdf.parse(tfData_de_nascimento.getText());
                    pessoa.setDataDeNascimento(data);
                    pessoa.setDataDeNascimento(cf.converteDeStringParaDate(tfData_de_nascimento.getText()));
                    pessoa.setCodigoPessoa(tfCodigo_pessoa.getText());
                    pessoa.setCidadeIdCidade(daoCidade.obter(Integer.valueOf(tfCidade_id_cidade.getText())));
                    if (acao.equals("adicionar")) {
                        daoPessoa.inserir(pessoa);
                    } else {
                        daoPessoa.atualizar(pessoa);
                    }
                    Cliente cliente = daoCliente.obter(pessoa.getCpfPessoa());
                    if (cbCliente.isSelected()) {
                        boolean novo = false;

                        if (cliente == null) {
                            cliente = new Cliente();
                            novo = true;
                        }
                        cliente.setPessoaCpfPessoa(pessoa.getCpfPessoa());
                        if (novo) {
                            daoCliente.inserir(cliente);
                        } else {
                            daoCliente.atualizar(cliente);
                        }
                    } else {//vai deixar de ser funcionario
                        if (cliente != null) {
                            daoCliente.remover(cliente);
                        }
                    }
                    Funcionario funcionario = daoFuncionario.obter(pessoa.getCpfPessoa());

                    if (cbFuncionario.isSelected()) {
                        boolean novo = false;

                        if (funcionario == null) {
                            funcionario = new Funcionario();
                            novo = true;
                        }
                        funcionario.setPessoaCpfPessoa(pessoa.getCpfPessoa());
                        funcionario.setCargoIdCargo(daoCargo.obter(Integer.valueOf(tfCargo_id_cargo.getText().split("-")[0].trim())));
                        if (novo) {
                            daoFuncionario.inserir(funcionario);
                        } else {
                            daoFuncionario.atualizar(funcionario);
                        }
                    } else {//vai deixar de ser funcionario
                        if (funcionario != null) {
                            daoFuncionario.remover(funcionario);
                        }
                    }

                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfCpf_pessoa.setEnabled(true);
                    tfCpf_pessoa.setEditable(true);
                    tfCpf_pessoa.requestFocus();
                    tfCpf_pessoa.setText("");
                    tfCpf_pessoa.setText("");
                    tfNome_pessoa.setEnabled(true);
                    tfNome_pessoa.setEditable(true);
                    tfNome_pessoa.requestFocus();
                    tfNome_pessoa.setText("");
                    tfNome_pessoa.setText("");
                    tfData_de_nascimento.setEnabled(true);
                    tfData_de_nascimento.setEditable(true);
                    tfData_de_nascimento.requestFocus();
                    tfData_de_nascimento.setText("");
                    tfData_de_nascimento.setText("");
                    tfCodigo_pessoa.setEnabled(true);
                    tfCodigo_pessoa.setEditable(true);
                    tfCodigo_pessoa.requestFocus();
                    tfCodigo_pessoa.setText("");
                    tfCodigo_pessoa.setText("");
                    tfCidade_id_cidade.setEnabled(true);
                    tfCidade_id_cidade.setEditable(true);
                    tfCidade_id_cidade.requestFocus();
                    tfCidade_id_cidade.setText("");
                    tfCidade_id_cidade.setText("");
                    tfCargo_id_cargo.setEditable(true);
                    tfCargo_id_cargo.setEnabled(true);
                    tfCargo_id_cargo.requestFocus();
                    tfCargo_id_cargo.setText("");
                    tfCargo_id_cargo.setText("");
                    cbCliente.setEnabled(true);
                    cbFuncionario.setEnabled(true);
                    cbCliente.setSelected(false);
                    cbFuncionario.setSelected(false);
                    lbCargo.setVisible(false);
                    tfCargo_id_cargo.setVisible(false);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro, Digite Novamente!", "Erro ao buscar", JOptionPane.PLAIN_MESSAGE);

                }
            }
        }
        );

// listener Alterar
        btAlterar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfCpf_pessoa.setEditable(false);
                tfNome_pessoa.requestFocus();
                tfNome_pessoa.setEditable(true);
                tfData_de_nascimento.setEditable(true);
                tfCodigo_pessoa.setEditable(true);
                tfCidade_id_cidade.setEditable(true);
                tfCargo_id_cargo.setEditable(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                tfCpf_pessoa.setEnabled(true);
                btExcluir.setVisible(false);
                cbCliente.setEnabled(true);
                cbFuncionario.setEnabled(true);
                acao = "alterar";

            }
        }
        );

// listener Excluir
        btExcluir.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                Cliente cliente = daoCliente.obter(pessoa.getCpfPessoa());
                if (cliente != null) {
                    daoCliente.remover(cliente);
                }
                Funcionario funcionario = daoFuncionario.obter(pessoa.getCpfPessoa());
                if (funcionario != null) {
                    daoFuncionario.remover(funcionario);
                }

                daoPessoa.remover(pessoa);

                btExcluir.setVisible(false);
                tfCpf_pessoa.setEnabled(true);
                tfCpf_pessoa.setEditable(true);
                tfCpf_pessoa.requestFocus();
                tfCpf_pessoa.setText("");
                tfNome_pessoa.setText("");
                tfNome_pessoa.setEditable(false);
                tfData_de_nascimento.setText("");
                tfData_de_nascimento.setEditable(false);
                tfCodigo_pessoa.setText("");
                tfCodigo_pessoa.setEditable(false);
                tfCidade_id_cidade.setText("");
                tfCidade_id_cidade.setEditable(false);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoPessoa.remover(pessoa);
                }
            }
        }
        );

// listener Listar
        btListar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<Pessoa> listaPessoa = daoPessoa.list();
                String[] colunas = new String[]{"Cpf_pessoa", "Nome_pessoa", "Data_de_nascimento", "Codigo_pessoa", "Cidade_id_cidade"};
                String[][] dados = new String[listaPessoa.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaPessoa.size(); i++) {
                    aux = listaPessoa.get(i).toString().split(";");
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
                tfNome_pessoa.setEditable(false);
                tfData_de_nascimento.setEditable(false);
                tfCodigo_pessoa.setEditable(false);
                tfCidade_id_cidade.setEditable(false);//cor do background e da letra de cada coluna
                coluna1.setBackground(Color.decode("#FFB0CF"));
                coluna1.setForeground(Color.decode("#111111"));
                coluna1.setHorizontalAlignment(SwingConstants.CENTER);
                tabela.getColumnModel().getColumn(0).setCellRenderer(coluna1);
                tabela.getColumnModel().getColumn(1).setCellRenderer(coluna1);
                tabela.getColumnModel().getColumn(2).setCellRenderer(coluna1);
                tabela.getColumnModel().getColumn(3).setCellRenderer(coluna1);
                tabela.getColumnModel().getColumn(4).setCellRenderer(coluna1);
            }
        }
        );

// listener Cancelar
        btCancelar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                btCancelar.setVisible(false);
                tfCpf_pessoa.setText("");
                tfCpf_pessoa.requestFocus();
                tfCpf_pessoa.setEnabled(true);
                tfCpf_pessoa.setEditable(true);
                tfNome_pessoa.setText("");
                tfNome_pessoa.setEditable(false);
                tfData_de_nascimento.setText("");
                tfData_de_nascimento.setEditable(false);
                tfCodigo_pessoa.setText("");
                tfCodigo_pessoa.setEditable(false);
                tfCidade_id_cidade.setText("");
                tfCidade_id_cidade.setEditable(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                tfCargo_id_cargo.setText("");
                cbCliente.setSelected(false);
                cbFuncionario.setSelected(false);
                lbCargo.setVisible(false);
                tfCargo_id_cargo.setVisible(false);

            }
        }
        );

        tfCidade_id_cidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoCidade.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            getBounds().x - getWidth() / 2 + getWidth() + 5,
                            tfCidade_id_cidade.getBounds().y + tfCidade_id_cidade.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
//                        tfCidade_id_cidade.setText(selectedItem);
                        tfCidade_id_cidade.setText(aux[0]);

                        //preparar para salvar
                        daoCidade.obter(Integer.valueOf(aux[0]));

                    } else {
                        tfCidade_id_cidade.requestFocus();
                        tfCidade_id_cidade.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
                }
            }
        }
        );

        tfCargo_id_cargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoCargo.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            getBounds().x - getWidth() / 2 + getWidth() + 5,
                            tfCargo_id_cargo.getBounds().y + tfCargo_id_cargo.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");

                        tfCargo_id_cargo.setText(aux[0]);

                        //preparar para salvar
                        daoCargo.obter(Integer.valueOf(aux[0]));

                    } else {
                        tfCargo_id_cargo.requestFocus();
                        tfCargo_id_cargo.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
                }
            }
        }
        );

// listener ao fechar o programa
        addWindowListener(
                new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e
            ) {
                //antes de sair, salvar a lista em armazenamento permanente
                // Sai da classe
                dispose();
            }
        }
        );

        setModal(
                true);
        pack();

        setSize(
                600, 400);
        setLocationRelativeTo(
                null);//centraliza na tela
        setVisible(
                true);
    }//fim do contrutor de GUI
} //fim da classe

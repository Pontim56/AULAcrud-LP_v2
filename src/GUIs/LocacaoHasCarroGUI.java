package GUIs;

import DAOs.DAOCarro;
import DAOs.DAOLocacao;
import Entidades.LocacaoHasCarro;
import DAOs.DAOLocacaoHasCarro;
import Entidades.LocacaoHasCarroPK;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.BorderFactory;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.DefaultListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class LocacaoHasCarroGUI extends JDialog {

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

    JLabel lbCarroIdCarro = new JLabel("Carro (ID)");
    DefaultComboBoxModel comboboxModelCarroId = new DefaultComboBoxModel();
    JComboBox cbCarroId = new JComboBox(comboboxModelCarroId);

    JLabel lbLocacaoIdLocacao = new JLabel("Locação (ID)");
    DefaultComboBoxModel comboboxModelLocacaoId = new DefaultComboBoxModel();
    JComboBox cbLocacaoId = new JComboBox(comboboxModelLocacaoId);

    JLabel lbPreco = new JLabel("Preço");
    JTextField tfPreco = new JTextField(40);

    LocacaoHasCarroPK locacaoHasCarroPK = new LocacaoHasCarroPK();
    LocacaoHasCarro carroFinal = new LocacaoHasCarro();

    JLabel lbVazio = new JLabel("");

    String[] colunas = new String[]{"carroIdCarro", "locacaoIdLocacao", "preco"};
    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public LocacaoHasCarroGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - LocacaoHasCarro");

        tabela.setEnabled(false);
        tabela.setRowHeight(20);

        tabela.getTableHeader().setBackground(Color.decode("#b068a5"));
        tabela.getTableHeader().setForeground(Color.decode("#111111"));
        tabela.setGridColor(new Color(0, 0, 0));
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.decode("#b068a5"));
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));

        pnNorte.add(lbCarroIdCarro);
        pnNorte.add(cbCarroId);
        pnNorte.add(lbLocacaoIdLocacao);
        pnNorte.add(cbLocacaoId);
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
        tfPreco.setEditable(false);

//        pnCentro.setLayout(new GridLayout(colunas.length -1, 2));
        pnCentro.setLayout(new GridLayout(1, 2)); // Para alinhamento lado a lado (1 linha, 2 colunas)
        lbLocacaoIdLocacao.setHorizontalAlignment(SwingConstants.CENTER);
        lbPreco.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultListCellRenderer centralizarCB = new DefaultListCellRenderer();
        centralizarCB.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        cbLocacaoId.setRenderer(centralizarCB);
        cbCarroId.setRenderer(centralizarCB);

        pnCentro.add(lbPreco);
        pnCentro.add(tfPreco);
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

        DAOLocacaoHasCarro daoLocacaoHasCarro = new DAOLocacaoHasCarro();
        DAOCarro daoCarro = new DAOCarro();
        DAOLocacao daoLocacao = new DAOLocacao();

        String[] listaCarro = daoCarro.listInOrderNomeStringsArray();
        String[] listaLocacao = daoLocacao.listInOrderNomeStringsArray();
        String[] listaLocacaoHasCarro = daoLocacaoHasCarro.listInOrderNomeStringsArray();

        Arrays.sort(listaLocacaoHasCarro);

        for (String s : listaCarro) {
            String[] aux = s.split("-");
            comboboxModelCarroId.addElement(aux[0]);
        }

        cbCarroId.setSelectedIndex(0);

        for (String s : listaLocacao) {
            String[] aux = s.split("-");
            comboboxModelLocacaoId.addElement(aux[0]);
        }

        cbLocacaoId.setSelectedIndex(0);
        // listener Buscar
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                try {
                    LocacaoHasCarroPK chavesPrimarias = new LocacaoHasCarroPK();

                    chavesPrimarias.setCarroIdCarro(Integer.valueOf(cbCarroId.getSelectedItem().toString().split(";")[0].split("-")[0].trim()));
                    chavesPrimarias.setLocacaoIdLocacao(Integer.valueOf(cbLocacaoId.getSelectedItem().toString().split(";")[0].split("-")[0].trim()));

                    LocacaoHasCarro carroFinal = daoLocacaoHasCarro.obter(chavesPrimarias);

                    if (carroFinal != null) { // Encontrou o LocacaoHasCarro na lista
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfPreco.setText(String.valueOf(carroFinal.getPreco()));
                        tfPreco.setEditable(false);
                    } else { // Não encontrou
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfPreco.setText("");
                        tfPreco.setEditable(false);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro, Digite Novamente!", "Erro ao buscar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// Listener Adicionar
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbCarroId.setEnabled(false);
                cbLocacaoId.setEnabled(false);
                tfPreco.requestFocus();
                tfPreco.setEditable(true);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionar";
            }
        });

// Listener Salvar
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("adicionar")) {
                    LocacaoHasCarro carroFinal = new LocacaoHasCarro();
                }
                try {
                    locacaoHasCarroPK.setCarroIdCarro(Integer.valueOf(cbCarroId.getSelectedItem().toString().split(";")[0].split("-")[0].trim()));
                    locacaoHasCarroPK.setLocacaoIdLocacao(Integer.valueOf(cbLocacaoId.getSelectedItem().toString().split(";")[0].split("-")[0].trim()));

                    carroFinal.setLocacaoHasCarroPK(locacaoHasCarroPK);
                    carroFinal.setPreco(Integer.valueOf(tfPreco.getText()));

                    if (acao.equals("adicionar")) {
                        daoLocacaoHasCarro.inserir(carroFinal);
                    } else {
                        daoLocacaoHasCarro.atualizar(carroFinal);
                    }

                    cbCarroId.setEnabled(true);
                    cbLocacaoId.setEnabled(true);
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfPreco.setEnabled(true);
                    tfPreco.setEditable(false);
                    tfPreco.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro ao salvar. Digite novamente!", "Erro ao salvar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// Listener Alterar
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbCarroId.setEnabled(false);
                cbLocacaoId.setEnabled(false);
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfPreco.setEditable(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                btExcluir.setVisible(false);
                acao = "alterar";
            }
        });

// Listener Excluir
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                cbCarroId.setEnabled(true);
                cbLocacaoId.setEnabled(true);
                btExcluir.setVisible(false);
                tfPreco.setText("");
                tfPreco.setEditable(false);
                btAlterar.setVisible(false);

                LocacaoHasCarroPK chavesPrimarias = new LocacaoHasCarroPK();
                chavesPrimarias.setCarroIdCarro(Integer.valueOf(cbCarroId.getSelectedItem().toString().split(";")[0].split("-")[0].trim()));
                chavesPrimarias.setLocacaoIdLocacao(Integer.valueOf(cbLocacaoId.getSelectedItem().toString().split(";")[0].split("-")[0].trim()));

                LocacaoHasCarro carroFinal = daoLocacaoHasCarro.obter(chavesPrimarias);

                if (response == JOptionPane.YES_OPTION) {
                    daoLocacaoHasCarro.remover(carroFinal);
                }
            }
        });

// Listener Listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<LocacaoHasCarro> listaLocacaoHasCarro = daoLocacaoHasCarro.list();

                String[] colunas = new String[]{"CarroIdCarro", "LocacaoIdLocacao", "Preco"};
                String[][] dados = new String[listaLocacaoHasCarro.size()][colunas.length];
                String aux[];

                for (int i = 0; i < listaLocacaoHasCarro.size(); i++) {
                    aux = listaLocacaoHasCarro.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }

                cardLayout.show(pnSul, "listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                cbCarroId.setEnabled(true);
                cbLocacaoId.setEnabled(true);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
                tfPreco.setEditable(false);
                coluna1.setBackground(Color.decode("#FFB0CF"));
                coluna1.setForeground(Color.decode("#111111"));
                coluna1.setHorizontalAlignment(SwingConstants.CENTER);
tabela.getColumnModel().getColumn(0).setCellRenderer(coluna1);
tabela.getColumnModel().getColumn(1).setCellRenderer(coluna1);
tabela.getColumnModel().getColumn(2).setCellRenderer(coluna1);
            }
        });

// Listener Cancelar
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbCarroId.setEnabled(true);
                cbLocacaoId.setEnabled(true);
                btCancelar.setVisible(false);
                tfPreco.setText("");
                tfPreco.setEditable(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
            }
        });

// Listener ao Fechar
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setSize(550,200);
        setModal(true);
//        pack();
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);
    }

}

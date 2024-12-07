package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.Locacao;
import java.util.ArrayList;
import java.util.List;

public class DAOLocacao extends DAOGenerico<Locacao> {

    private List<Locacao> lista = new ArrayList<>();

    public DAOLocacao() {
        super(Locacao.class);
    }
    
    public int autoIdLocacao() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idLocacao) FROM Locacao e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Locacao> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Locacao e WHERE e.idLocacao) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Locacao> listById(int id) {
        return em.createQuery("SELECT e FROM Locacao + e WHERE e.idLocacao= :id").setParameter("id", id).getResultList();
    }

    public List<Locacao> listInOrderNome() {
        return em.createQuery("SELECT e FROM Locacao e ORDER BY e.dataLocacao").getResultList();
    }

    public List<Locacao> listInOrderId() {
        return em.createQuery("SELECT e FROM Locacao e ORDER BY e.idLocacao").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Locacao> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdLocacao()+ "-" + lf.get(i).getDataLocacao());
        }
        return ls;
    }
    public String[] listInOrderNomeStringsArray() {
        List<Locacao> lf = listInOrderNome();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i] = (lf.get(i).getIdLocacao()+ "-" + lf.get(i).getDataLocacao());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOLocacao DAOLocacao = new DAOLocacao();
        List<Locacao> listaLocacao = DAOLocacao.list();
        for (Locacao Locacao : listaLocacao) {
            System.out.println(Locacao.getIdLocacao()+ "-" + Locacao.getDataLocacao()+ "-" + Locacao.getClientePessoaCpfPessoa());
        }
    }
}

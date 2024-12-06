package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.Pessoa;
import java.util.ArrayList;
import java.util.List;

public class DAOPessoa extends DAOGenerico<Pessoa> {

    private List<Pessoa> lista = new ArrayList<>();

    public DAOPessoa() {
        super(Pessoa.class);
    }

    public int autoCpfPessoa() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.CpfPessoa) FROM Pessoa e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Pessoa> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Pessoa e WHERE e.CpfPessoa) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Pessoa> listById(int id) {
        return em.createQuery("SELECT e FROM Pessoa + e WHERE e.CpfPessoa= :id").setParameter("id", id).getResultList();
    }

    public List<Pessoa> listInOrderNome() {
        return em.createQuery("SELECT e FROM Pessoa e ORDER BY e.nomePessoa").getResultList();
    }

    public List<Pessoa> listInOrderId() {
        return em.createQuery("SELECT e FROM Pessoa e ORDER BY e.CpfPessoa").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Pessoa> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getCpfPessoa()+ "-" + lf.get(i).getNomePessoa());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPessoa daoPessoa = new DAOPessoa();
        List<Pessoa> listaPessoa = daoPessoa.list();
        for (Pessoa Pessoa : listaPessoa) {
            System.out.println(Pessoa.getCpfPessoa()+ "-" + Pessoa.getNomePessoa()+ "-" + Pessoa.getDataDeNascimento() + "-" + Pessoa.getCidadeIdCidade()+ "-" + Pessoa.getCodigoPessoa());
        }
    }
}

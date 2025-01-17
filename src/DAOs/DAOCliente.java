package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.Cliente;
import Entidades.Pessoa;
import java.util.ArrayList;
import java.util.List;

public class DAOCliente extends DAOGenerico<Cliente> {

    private List<Cliente> lista = new ArrayList<>();

    public DAOCliente() {
        super(Cliente.class);
    }

    public int autoPessoaCpfPessoa() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.pessoaCpfPessoa) FROM Cliente e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Cliente> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Cliente e WHERE e.pessoaCpfPessoa) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Cliente> listById(int id) {
        return em.createQuery("SELECT e FROM Cliente + e WHERE e.pessoaCpfPessoa= :id").setParameter("id", id).getResultList();
    }

    public List<Cliente> listInOrderNome() {
       return em.createQuery("SELECT e FROM Cliente e ORDER BY e.pessoaCpfPessoa").getResultList();
    }

    public List<Cliente> listInOrderId() {
        return em.createQuery("SELECT e FROM Cliente e ORDER BY e.pessoaCpfPessoa").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Cliente> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPessoaCpfPessoa()+ "-" + lf.get(i).getPessoaCpfPessoa());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOCliente daoCliente = new DAOCliente();
        List<Cliente> listaCliente = daoCliente.list();
        for (Cliente Cliente : listaCliente) {
            System.out.println(Cliente.getPessoaCpfPessoa( ));
        }
    }
}

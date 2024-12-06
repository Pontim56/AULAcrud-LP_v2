package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class DAOFuncionario extends DAOGenerico<Funcionario> {

    private List<Funcionario> lista = new ArrayList<>();

    public DAOFuncionario() {
        super(Funcionario.class);
    }

    public int autoPessoaCpfPessoa() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.pessoaCpfPessoa) FROM Funcionario e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Funcionario> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Funcionario e WHERE e.pessoaCpfPessoa) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Funcionario> listById(int id) {
        return em.createQuery("SELECT e FROM Funcionario + e WHERE e.pessoaCpfPessoa= :id").setParameter("id", id).getResultList();
    }

    public List<Funcionario> listInOrderNome() {
       return em.createQuery("SELECT e FROM Funcionario e ORDER BY e.pessoaCpfPessoa").getResultList();
    }

    public List<Funcionario> listInOrderId() {
        return em.createQuery("SELECT e FROM Funcionario e ORDER BY e.pessoaCpfPessoa").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Funcionario> lf;
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
        DAOFuncionario daoFuncionario = new DAOFuncionario();
        List<Funcionario> listaFuncionario = daoFuncionario.list();
        for (Funcionario Funcionario : listaFuncionario) {
            System.out.println(Funcionario.getPessoaCpfPessoa( ));
        }
    }
}

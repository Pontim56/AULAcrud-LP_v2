package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.LocacaoHasCarro;
import Entidades.LocacaoHasCarroPK;
import java.util.ArrayList;
import java.util.List;

public class DAOLocacaoHasCarro extends DAOGenerico<LocacaoHasCarro> {

    private List<LocacaoHasCarro> lista = new ArrayList<>();

    public DAOLocacaoHasCarro() {
        super(LocacaoHasCarro.class);
    }

    public int autoIdLocacaoHasCarro() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idLocacaoHasCarro) FROM LocacaoHasCarro e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<LocacaoHasCarro> listByNome(String nome) {
        return em.createQuery("SELECT e FROM LocacaoHasCarro e WHERE e.idLocacaoHasCarro) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<LocacaoHasCarro> listById(int id) {
        return em.createQuery("SELECT e FROM LocacaoHasCarro + e WHERE e.idLocacaoHasCarro= :id").setParameter("id", id).getResultList();
    }

    public List<LocacaoHasCarro> listInOrderNome() {
    return em.createQuery("SELECT e FROM LocacaoHasCarro e ORDER BY e.preco", LocacaoHasCarro.class).getResultList();
}


    public List<LocacaoHasCarro> listInOrderId() {
        return em.createQuery("SELECT e FROM LocacaoHasCarro e ORDER BY e.idLocacaoHasCarro").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<LocacaoHasCarro> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getLocacaoHasCarroPK()+ "-" + lf.get(i).getLocacao());
        }
        return ls;
    }
    public String[] listInOrderNomeStringsArray() {
        List<LocacaoHasCarro> lf = listInOrderNome();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i] = (lf.get(i).getLocacaoHasCarroPK()+ "-" + lf.get(i).getLocacao());
        }
        return ls;
    }
    @Override
public LocacaoHasCarro obter(LocacaoHasCarroPK id) {
    return em.find(LocacaoHasCarro.class, id);
}


    public static void main(String[] args) {
        DAOLocacaoHasCarro daoLocacaoHasCarro = new DAOLocacaoHasCarro();
//        List<LocacaoHasCarro> listaLocacaoHasCarro = daoLocacaoHasCarro.list();
//        for (LocacaoHasCarro LocacaoHasCarro : listaLocacaoHasCarro) {
//            System.out.println(LocacaoHasCarro.getIdLocacaoHasCarro()+ "-" + LocacaoHasCarro.getNomeLocacaoHasCarro()+ "-" + LocacaoHasCarro.getMotorIdMotor());
        }
    }


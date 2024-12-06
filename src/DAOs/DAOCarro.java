package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.Carro;
import java.util.ArrayList;
import java.util.List;

public class DAOCarro extends DAOGenerico<Carro> {

    private List<Carro> lista = new ArrayList<>();

    public DAOCarro() {
        super(Carro.class);
    }

    public int autoIdCarro() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idCarro) FROM Carro e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Carro> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Carro e WHERE e.idCarro) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Carro> listById(int id) {
        return em.createQuery("SELECT e FROM Carro + e WHERE e.idCarro= :id").setParameter("id", id).getResultList();
    }

    public List<Carro> listInOrderNome() {
        return em.createQuery("SELECT e FROM Carro e ORDER BY e.nomeCarro").getResultList();
    }

    public List<Carro> listInOrderId() {
        return em.createQuery("SELECT e FROM Carro e ORDER BY e.idCarro").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Carro> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdCarro()+ "-" + lf.get(i).getNomeCarro());
        }
        return ls;
    }
    public String[] listInOrderNomeStringsArray() {
        List<Carro> lf = listInOrderNome();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i] = (lf.get(i).getIdCarro()+ "-" + lf.get(i).getNomeCarro());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOCarro daoCarro = new DAOCarro();
        List<Carro> listaCarro = daoCarro.list();
        for (Carro Carro : listaCarro) {
            System.out.println(Carro.getIdCarro()+ "-" + Carro.getNomeCarro()+ "-" + Carro.getMotorIdMotor());
        }
    }
}

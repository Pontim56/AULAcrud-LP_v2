package DAOs;

import Entidades.Motor;
import java.util.ArrayList;
import java.util.List;

public class DAOMotor extends DAOGenerico<Motor> {

    private List<Motor> lista = new ArrayList<>();

    public DAOMotor() {
        super(Motor.class);
    }

    public int autoIdMotor() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.cpf) FROM Motor e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Motor> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Motor e WHERE e.idMotor) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Motor> listById(int id) {
        return em.createQuery("SELECT e FROM Motor + e WHERE e.idMotor= :id").setParameter("id", id).getResultList();
    }

    public List<Motor> listInOrderNome() {
        return em.createQuery("SELECT e FROM Motor e ORDER BY e.tipoMotor").getResultList();
    }

    public List<Motor> listInOrderId() {
        return em.createQuery("SELECT e FROM Motor e ORDER BY e.idMotor").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Motor> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdMotor()+ "-" + lf.get(i).getTipoMotor());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOMotor daoMotor = new DAOMotor();
        List<Motor> listaMotor = daoMotor.list();
        for (Motor Motor : listaMotor) {
            System.out.println(Motor.getIdMotor()+ "-" + Motor.getTipoMotor());
        }
    }
}

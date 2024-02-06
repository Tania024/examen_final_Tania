package ups.edu.ec.examen_final.dao;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import ups.edu.ec.examen_final.model.RecargaElectronica;

@Stateless
public class RecargaElectronicaDAO implements Serializable{
	@PersistenceContext
    private EntityManager em;

    public void insert(RecargaElectronica recargaElectronica) {
        try {
            em.persist(recargaElectronica);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(RecargaElectronica recargaElectronica) {
        try {
            em.merge(recargaElectronica);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(int id) {
        try {
            RecargaElectronica recargaElectronica = em.find(RecargaElectronica.class, id);
            if (recargaElectronica != null) {
                em.remove(recargaElectronica);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RecargaElectronica read(int id) {
        try {
            return em.find(RecargaElectronica.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RecargaElectronica> getAll() {
        try {
            String jpql = "SELECT r FROM RecargaElectronica r";
            TypedQuery<RecargaElectronica> query = em.createQuery(jpql, RecargaElectronica.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package ups.edu.ec.examen_final.business;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.examen_final.dao.RecargaElectronicaDAO;
import ups.edu.ec.examen_final.model.RecargaElectronica;

@Stateless
public class GestionRecargaElectronica {

    @Inject
    private RecargaElectronicaDAO daoRecarga;

    public void guardarRecargaElectronica(RecargaElectronica recargaElectronica) {
        if (recargaElectronica != null) {
            daoRecarga.insert(recargaElectronica);
        } else {
            throw new IllegalArgumentException("La recarga electrónica no puede ser nula");
        }
    }

    public void actualizarRecargaElectronica(RecargaElectronica recargaElectronica) {
        if (recargaElectronica != null) {
            daoRecarga.update(recargaElectronica);
        } else {
            throw new IllegalArgumentException("La recarga electrónica no puede ser nula");
        }
    }

    public RecargaElectronica obtenerRecargaElectronica(int recargaId) {
        if (recargaId > 0) {
            return daoRecarga.read(recargaId);
        } else {
            throw new IllegalArgumentException("El ID de recarga electrónica debe ser mayor que cero");
        }
    }

    public void eliminarRecargaElectronica(int recargaId) {
        if (recargaId > 0) {
            daoRecarga.remove(recargaId);
        } else {
            throw new IllegalArgumentException("El ID de recarga electrónica debe ser mayor que cero");
        }
    }

    public List<RecargaElectronica> obtenerTodasRecargasElectronicas() {
        return daoRecarga.getAll();
    }
}

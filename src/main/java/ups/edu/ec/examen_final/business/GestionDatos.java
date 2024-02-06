package ups.edu.ec.examen_final.business;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import ups.edu.ec.examen_final.dao.RecargaElectronicaDAO;
import ups.edu.ec.examen_final.model.RecargaElectronica;


@Singleton
@Startup
public class GestionDatos {
	@Inject
	private RecargaElectronicaDAO daoRecargaElectronica;
	
	@PostConstruct
	public void init() {
		System.out.println("iniciando");
		
		RecargaElectronica recarga= new RecargaElectronica();
		recarga.setId(1);
		recarga.setNumeroTelefono("0993235895");
		recarga.setOperador("Claro");
		recarga.setMonto(5.00);
		
		daoRecargaElectronica.insert(recarga);
		
		System.out.println("Datos de prueba ingresados");
		
	}
		

}

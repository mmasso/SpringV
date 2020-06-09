package org.formacio.servei;

import java.util.Optional;

import org.formacio.domain.Factura;
import org.formacio.domain.LiniaFactura;
import org.formacio.repositori.FacturesRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FacturesService {

	
	/*
	 * Aquest metode ha de carregar la factura amb id idFactura i afegir una nova linia amb les dades
	 * passades (producte i totalProducte)
	 * 
	 * S'ha de retornar la factura modificada
	 * 
	 * Per implementar aquest metode necessitareu una referencia (dependencia) a FacturesRepositori
	 */
	
	@Autowired
	FacturesRepositori facturaRepo;
	
	@Autowired
	FidalitzacioService fidelService;
	
	public Factura afegirProducte (long idFactura, String producte, int totalProducte) {
		
		Optional<Factura> factura= facturaRepo.findById(idFactura);
		
		if(factura.isPresent()) {
			LiniaFactura linia = new LiniaFactura();
			linia.setProducte(producte);
			linia.setTotal(totalProducte);
			factura.get().getLinies().add(linia);
			facturaRepo.save(factura.get());
			
			if(factura.get().getLinies().size() >= 4) {
				String email = factura.get().getClient().getEmail();
				fidelService.notificaRegal(email);
			}
		}
		
		return factura.get();
	}
}

package org.formacio.repositori;

import org.formacio.domain.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FacturesRepositori extends CrudRepository<Factura, Long>  {

	@Query("select count(factura.client) from Factura factura")
	
	public Number totalClient(String client);
	
}

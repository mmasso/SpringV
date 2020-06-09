package org.formacio.repositori;

import org.formacio.domain.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FacturesRepositori extends CrudRepository<Factura, Long>  {

	@Query("select sum(linies.total) from Factura factura join factura.linies linies where factura.client.nom = :client")
	public Number totalClient(@Param("client")String client);
	
}

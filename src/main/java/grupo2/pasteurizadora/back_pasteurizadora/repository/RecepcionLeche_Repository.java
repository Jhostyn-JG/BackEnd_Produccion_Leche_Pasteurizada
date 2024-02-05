package grupo2.pasteurizadora.back_pasteurizadora.repository;

import grupo2.pasteurizadora.back_pasteurizadora.entity.RecepcionLeche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecepcionLeche_Repository extends JpaRepository<RecepcionLeche, String> {

    //Por consultas JPQL traer una lista de los id de los lecheros independientes
    @Query("SELECT l.codLechero FROM LecheroIndependiente l")
    List<String> findCodLechero();

    //Por consultas JPQL traer una lista de los id de los lechereros independientes que no esten relacionados con una recepcion
    @Query("SELECT l.codLechero FROM LecheroIndependiente l WHERE l.codLechero NOT IN (SELECT li.codLechero FROM RecepcionLeche r JOIN r.lecheroIndependiente li)")
    List<String> findCodLecheroSinRecepcion();

    //Por consultas JPQL traer una lista de los id de las haciendas lecheras
    @Query("SELECT h.codHacienda FROM HaciendaLechera h")
    List<String> findCodHacienda();

    //Por consultas JPQL traer una lista de los id de las haciendas lecheras que no esten relacionados con una recepcion
    @Query("SELECT h.codHacienda FROM HaciendaLechera h WHERE h.codHacienda NOT IN (SELECT hl.codHacienda FROM RecepcionLeche r JOIN r.haciendaLechera hl)")
    List<String> findCodHaciendaSinRecepcion();

    //Consulta JPQL para saber cuantos datos hay en la tabla
    @Query("SELECT COUNT(r) FROM RecepcionLeche r")
    Long countRecepcionLeche();

    //Cantidad leche recibida por año/mes
   @Query("SELECT SUM(r.cantidadLecheRecibida) FROM RecepcionLeche r WHERE EXTRACT(YEAR FROM r.fechaRecepcion) = ?1 AND EXTRACT(MONTH FROM r.fechaRecepcion) = ?2")
    Long sumRecepcionLecheByYearMonth(Integer year, Integer month);

}
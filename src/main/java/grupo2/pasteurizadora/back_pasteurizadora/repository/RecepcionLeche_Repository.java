package grupo2.pasteurizadora.back_pasteurizadora.repository;

import grupo2.pasteurizadora.back_pasteurizadora.entity.RecepcionLeche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecepcionLeche_Repository extends JpaRepository<RecepcionLeche, String> {

    //Por consultas JPQL traer una lista de los id de los lecheros independientes
    @Query("SELECT l.codLechero FROM LecheroIndependiente l")
    List<String> findCodLechero();

    //Por consultas JPQL traer una lista de los id de las haciendas lecheras
    @Query("SELECT h.codHacienda FROM HaciendaLechera h")
    List<String> findCodHacienda();

    //Consulta JPQL para saber cuantos datos hay en la tabla
    @Query("SELECT COUNT(r) FROM RecepcionLeche r")
    Long countRecepcionLeche();

   //Cantidad de leche recibida entre el rango de fecha de incio y fecha fin con el campo (fechaRecepcion)
    @Query("SELECT r.fechaRecepcion, SUM(r.cantidadLecheRecibida) as sumaCantidad FROM RecepcionLeche r WHERE r.fechaRecepcion BETWEEN :fechaInicio AND :fechaFin GROUP BY r.fechaRecepcion")
    List<Object[]> sumRecepcionLecheByDateRangeGroupByFecha(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

}
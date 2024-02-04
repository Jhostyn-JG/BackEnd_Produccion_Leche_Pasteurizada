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



}
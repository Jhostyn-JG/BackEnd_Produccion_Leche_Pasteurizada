package grupo2.pasteurizadora.back_pasteurizadora.services;

import grupo2.pasteurizadora.back_pasteurizadora.entity.LoteProductos;
import grupo2.pasteurizadora.back_pasteurizadora.entity.ProcesoVerificacion;
import grupo2.pasteurizadora.back_pasteurizadora.exception.ClientException;
import grupo2.pasteurizadora.back_pasteurizadora.repository.LoteProductos_Repository;
import grupo2.pasteurizadora.back_pasteurizadora.repository.ProcesoVerificacion_Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoteProductos_Service {

    final
    LoteProductos_Repository loteProductos_repository;

    final
    ProcesoVerificacion_Repository procesoVerificacion_repository;

    public LoteProductos_Service(LoteProductos_Repository loteProductos_repository, ProcesoVerificacion_Repository procesoVerificacion_repository) {
        this.loteProductos_repository = loteProductos_repository;
        this.procesoVerificacion_repository = procesoVerificacion_repository;
    }

    public List<LoteProductos> getAllLoteProductos() {
        return loteProductos_repository.findAll();
    }


    // Create
    public LoteProductos saveLoteProductos(LoteProductos loteProductos) {
        return loteProductos_repository.save(loteProductos);
    }

    // Read
    public LoteProductos getLoteProductosbyCodLote(String codLote) {
        return loteProductos_repository.findById(codLote)
                .orElseThrow(() -> new ClientException("No se encuentra el codigo de Lote Proporcionado: " + codLote));
    }

    // Update
    public LoteProductos updateLoteProductos(LoteProductos loteProductos) {
        LoteProductos existingLoteProductos = getLoteProductosbyCodLote(loteProductos.getCodLote());
        existingLoteProductos.setNombreLote(loteProductos.getNombreLote());
        existingLoteProductos.setTipoLote(loteProductos.getTipoLote());
        existingLoteProductos.setFechadeProduccion(loteProductos.getFechadeProduccion());
        existingLoteProductos.setFechadeVencimiento(loteProductos.getFechadeVencimiento());
        existingLoteProductos.setDetallesLote(loteProductos.getDetallesLote());
        existingLoteProductos.setCantidadPaquetes(loteProductos.getCantidadPaquetes());
        return loteProductos_repository.save(existingLoteProductos);
    }

    // Delete
    public void deleteLoteProductos(String codLote) {
        Optional<LoteProductos> loteProductosOptional = loteProductos_repository.findById(codLote);
        if(loteProductosOptional.isPresent()){
            LoteProductos loteProductos = loteProductosOptional.get();
            // Desvincula el ProcesoVerificacion
            if (loteProductos.getProcesoVerificacion() != null) {
                ProcesoVerificacion procesoVerificacion = loteProductos.getProcesoVerificacion();
                procesoVerificacion.getLoteProductos().remove(loteProductos);
                procesoVerificacion_repository.save(procesoVerificacion);
            }
            // Ahora puedes eliminar el LoteProductos
            loteProductos_repository.delete(loteProductos);

        } else {
            throw new ClientException("No se encuentra el codigo de Lote Proporcionado: " + codLote);
        }
    }

    //Consulta JPQL para saber cuantos datos hay en la tabla
    public Long countLoteProductos() {
        return loteProductos_repository.countLoteProductos();
    }



}

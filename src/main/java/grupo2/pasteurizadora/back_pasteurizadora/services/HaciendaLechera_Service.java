package grupo2.pasteurizadora.back_pasteurizadora.services;

import grupo2.pasteurizadora.back_pasteurizadora.entity.HaciendaLechera;
import grupo2.pasteurizadora.back_pasteurizadora.exception.ClientException;
import grupo2.pasteurizadora.back_pasteurizadora.repository.HaciendaLechera_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HaciendaLechera_Service {

    final
    HaciendaLechera_Repository haciendaLechera_repository;

    public HaciendaLechera_Service(HaciendaLechera_Repository haciendaLechera_repository) {
        this.haciendaLechera_repository = haciendaLechera_repository;
    }

    public List<HaciendaLechera> getAll() {
        return haciendaLechera_repository.findAll();
    }

    public HaciendaLechera getHaciendaLecheraByCodHacienda(String codHacienda) {
        Optional<HaciendaLechera> haciendaLechera = haciendaLechera_repository.findById(codHacienda);
        if (haciendaLechera.isPresent()) {
            return haciendaLechera.get();
        } else {
            throw new ClientException("No se encontró la hacienda lechera con codigo: " + codHacienda);
        }
    }

    public HaciendaLechera saveHaciendaLechera(HaciendaLechera haciendaLechera) {
        return haciendaLechera_repository.save(haciendaLechera);
    }

    public HaciendaLechera updateHaciendaLechera(HaciendaLechera haciendaLechera) {
        String codHacienda = haciendaLechera.getCodHacienda();
        Optional<HaciendaLechera> optionalHaciendaLechera = haciendaLechera_repository.findById(codHacienda);
        if (optionalHaciendaLechera.isPresent()) {
            HaciendaLechera updatedHaciendaLechera = optionalHaciendaLechera.get();
            updatedHaciendaLechera.setNombreHacienda(haciendaLechera.getNombreHacienda());
            updatedHaciendaLechera.setRuc(haciendaLechera.getRuc());
            updatedHaciendaLechera.setDireccion(haciendaLechera.getDireccion());
            updatedHaciendaLechera.setTelefonoEmpresa(haciendaLechera.getTelefonoEmpresa());
            updatedHaciendaLechera.setResponsable(haciendaLechera.getResponsable());
            updatedHaciendaLechera.setEmail(haciendaLechera.getEmail());
            updatedHaciendaLechera.setTelefonoContacto(haciendaLechera.getTelefonoContacto());
            updatedHaciendaLechera.setFechaCompra(haciendaLechera.getFechaCompra());
            updatedHaciendaLechera.setDetallesSuministro(haciendaLechera.getDetallesSuministro());
            return haciendaLechera_repository.save(updatedHaciendaLechera);
        } else {
            throw new ClientException("No se encontró la hacienda lechera con codigo: " + codHacienda);
        }
    }

    public void deleteHaciendaLechera(String codHacienda) {
        haciendaLechera_repository.deleteById(codHacienda);
    }



}


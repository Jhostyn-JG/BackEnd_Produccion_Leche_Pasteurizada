package grupo2.pasteurizadora.back_pasteurizadora.services;

import grupo2.pasteurizadora.back_pasteurizadora.entity.LecheroIndependiente;
import grupo2.pasteurizadora.back_pasteurizadora.exception.ClientException;
import grupo2.pasteurizadora.back_pasteurizadora.repository.LecheroIndependiente_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LecheroIndependiente_Service {

     final
    LecheroIndependiente_Repository lecheroIndependiente_repository;

    public LecheroIndependiente_Service(LecheroIndependiente_Repository lecheroIndependiente_repository) {
        this.lecheroIndependiente_repository = lecheroIndependiente_repository;
    }

    public List<LecheroIndependiente> getAll() {
        return lecheroIndependiente_repository.findAll();
    }

    public LecheroIndependiente getLecheroIndependienteByCodLechero(String codLechero) {
        Optional<LecheroIndependiente> lecheroIndependiente = lecheroIndependiente_repository.findById(codLechero);
        if (lecheroIndependiente.isPresent()) {
            return lecheroIndependiente.get();
        } else {
            throw new ClientException("No se encontró el lechero independiente con codigo: " + codLechero);
        }
    }

    public LecheroIndependiente saveLecheroIndependiente(LecheroIndependiente lecheroIndependiente) {
        return lecheroIndependiente_repository.save(lecheroIndependiente);
    }

    public LecheroIndependiente updateLecheroIndependiente(LecheroIndependiente lecheroIndependiente) {
        String codLechero = lecheroIndependiente.getCodLechero();
        Optional<LecheroIndependiente> optionalLecheroIndependiente = lecheroIndependiente_repository.findById(codLechero);
        if (optionalLecheroIndependiente.isPresent()) {
            LecheroIndependiente updatedLecheroIndependiente = optionalLecheroIndependiente.get();
            updatedLecheroIndependiente.setNombres(lecheroIndependiente.getNombres());
            updatedLecheroIndependiente.setApellidos(lecheroIndependiente.getApellidos());
            updatedLecheroIndependiente.setCedula(lecheroIndependiente.getCedula());
            updatedLecheroIndependiente.setDireccion(lecheroIndependiente.getDireccion());
            updatedLecheroIndependiente.setEmail(lecheroIndependiente.getEmail());
            updatedLecheroIndependiente.setContacto(lecheroIndependiente.getContacto());
            updatedLecheroIndependiente.setFechaCompra(lecheroIndependiente.getFechaCompra());
            updatedLecheroIndependiente.setDetallesSuministro(lecheroIndependiente.getDetallesSuministro());
            return lecheroIndependiente_repository.save(updatedLecheroIndependiente);
        } else {
            throw new ClientException("No se encontró el lechero independiente con codigo: " + codLechero);
        }
    }

    public void deleteLecheroIndependiente(String codLechero) {
        lecheroIndependiente_repository.deleteById(codLechero);
    }


}

package com.abelardo.MsLiquidacion.service;

import com.abelardo.MsLiquidacion.persistence.entity.Cargue;
import com.abelardo.MsLiquidacion.persistence.repository.CargueRepository;
import com.abelardo.MsLiquidacion.service.dto.DatosEditarCargueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargueService {

    private CargueRepository cargueRepository;

    public CargueService(CargueRepository cargueRepository) {
        this.cargueRepository = cargueRepository;
    }


    public Cargue createCargue() {

        //CREO EL CARGUE
        Cargue cargue = new Cargue();
        cargueRepository.save(cargue);

        return cargue;
    }

    public Optional<Cargue> findById(Long cargueId) {
        return cargueRepository.findById(cargueId);
    }

    public List<Cargue> findAll(){
        return cargueRepository.findAll();
    }

    public Page<Cargue> findAll(Pageable paginacion){

        return cargueRepository.findAll(paginacion);
    }


    public void eliminarCargue(Long id) {

        cargueRepository.deleteById(id);

       }

    public Cargue updateCargue(Long id, DatosEditarCargueDTO datosEditarCargueDTO) {

        Cargue updatedCargue = this.cargueRepository.findById(id).get();

        updatedCargue.setComprador(datosEditarCargueDTO.getComprador());
        updatedCargue.setVendedor(datosEditarCargueDTO.getVendedor());
        updatedCargue.setDestino(datosEditarCargueDTO.getDestino());
        updatedCargue.setInspector(datosEditarCargueDTO.getInspector());
        updatedCargue.setReferencia(datosEditarCargueDTO.getReferencia());
        updatedCargue.setReferenciaCliente(datosEditarCargueDTO.getReferenciaCliente());
        updatedCargue.setTerminal(datosEditarCargueDTO.getTerminal());
        updatedCargue.setNombreBuque(datosEditarCargueDTO.getNombreBuque());
        return cargueRepository.save(updatedCargue);
    }

}


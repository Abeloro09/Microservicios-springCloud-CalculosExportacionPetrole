package com.abelardo.MsLiquidacion.mapper;

import com.abelardo.MsLiquidacion.persistence.entity.Cargue;
import com.abelardo.MsLiquidacion.service.dto.OutDTOCargue;

public class OutDTOToCargue implements IMapper<Cargue, OutDTOCargue>{

    @Override
    public OutDTOCargue map(Cargue in) {
        OutDTOCargue outDTOCargue = new OutDTOCargue();

        outDTOCargue.setId(in.getId());
        outDTOCargue.setReferencia(in.getReferencia());
        outDTOCargue.setReferenciaCliente(in.getReferenciaCliente());
        outDTOCargue.setComprador(in.getComprador());
        outDTOCargue.setVendedor(in.getVendedor());
        outDTOCargue.setNombreBuque(in.getNombreBuque());


        return null;
    }
}

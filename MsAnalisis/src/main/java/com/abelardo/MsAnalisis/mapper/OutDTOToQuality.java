package com.abelardo.MsAnalisis.mapper;

import com.abelardo.MsAnalisis.persistence.identity.Quality;
import com.abelardo.MsAnalisis.service.dto.InDTOQuality;
import com.abelardo.MsAnalisis.service.dto.OutDTOQuality;
import org.springframework.stereotype.Component;

@Component
public class OutDTOToQuality implements IMapper<Quality, OutDTOQuality> {


    @Override
    public OutDTOQuality map(Quality in) {
        OutDTOQuality outDTOQuality = new OutDTOQuality();

        outDTOQuality.setId(in.getId());
        outDTOQuality.setReferencia(in.getReferencia());
        outDTOQuality.setReferencia_cliente(in.getReferencia_cliente());
        outDTOQuality.setDescripcion(in.getDescripcion());


        return outDTOQuality;
    }
}
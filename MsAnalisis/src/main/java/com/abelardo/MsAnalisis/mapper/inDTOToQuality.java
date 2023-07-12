package com.abelardo.MsAnalisis.mapper;

import com.abelardo.MsAnalisis.persistence.identity.Quality;
import com.abelardo.MsAnalisis.service.dto.InDTOQuality;
import org.springframework.stereotype.Component;

@Component
public class InDTOToQuality implements IMapper<InDTOQuality, Quality> {


    @Override
    public Quality map(InDTOQuality in) {
         Quality quality = new Quality();

         quality.setDescripcion(in.getDescripcion());
         quality.setApi(in.getApi());
         quality.setWater(in.getWater());
         quality.setSediment(in.getSediment());
         quality.setSalt(in.getSalt());
         quality.setSulfur(in.getSulfur());
         quality.setTan(in.getTan());
         quality.setViscosity(in.getViscosity());
         quality.setFlashpoint(in.getFlashpoint());
         quality.setABD(in.getABD());

         return quality;
    }
}

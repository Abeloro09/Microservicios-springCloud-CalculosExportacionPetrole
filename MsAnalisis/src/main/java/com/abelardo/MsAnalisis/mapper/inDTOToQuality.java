package com.abelardo.MsAnalisis.mapper;

import com.abelardo.MsAnalisis.persistence.identity.Quality;
import com.abelardo.MsAnalisis.service.dto.inDTOQuality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class inDTOToQuality implements IMapper<inDTOQuality, Quality> {


    @Override
    public Quality map(inDTOQuality in) {
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

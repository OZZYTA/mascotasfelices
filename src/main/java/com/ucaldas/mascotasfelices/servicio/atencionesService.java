package com.ucaldas.mascotasfelices.servicio;

import com.ucaldas.mascotasfelices.modelos.Atenciones;
import com.ucaldas.mascotasfelices.repositorio.atencionesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class atencionesService {
    @Autowired
    atencionesRepo atencionesRepo;

    public List<Atenciones> getAllAtenciones(){
        List<Atenciones> atencionesList = new ArrayList<>();
        atencionesRepo.findAll().forEach(atencion -> atencionesList.add(atencion));
        return atencionesList;
    }

    public Atenciones getAtencionById(Integer id){
        return  atencionesRepo.findById(id).get();
    }

    public boolean saveOrUpdateAtencion(Atenciones atencion){
        Atenciones aten=atencionesRepo.save(atencion);
        if (atencionesRepo.findById(aten.getId())!=null){
            return true;
        }
        return false;
    }

    public boolean deleteAtencion(Integer id){
        atencionesRepo.deleteById(id);

        if (atencionesRepo.findById(id)!=null){
            return true;
        }
        return false;
    }

    public ArrayList<Atenciones> obtenerPorMascota(Integer id){
        return atencionesRepo.findByMascota(id);
    }

    public ArrayList<Atenciones> obtenerPorVeterinario(Integer id){
        return atencionesRepo.findByVeterinario(id);
    }

    public Date obtenerPrimeraFecha(Integer id){
        return atencionesRepo.findOldestDate(id);
    }



}

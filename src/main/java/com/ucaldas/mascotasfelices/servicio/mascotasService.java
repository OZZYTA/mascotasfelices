package com.ucaldas.mascotasfelices.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucaldas.mascotasfelices.modelos.Mascotas;
import com.ucaldas.mascotasfelices.repositorio.mascotasRepo;

@Service
public class mascotasService {

    @Autowired
    mascotasRepo mascotasRepo;

    public List<Mascotas> getAllMascotas(){
        List<Mascotas> mascotasList= new ArrayList<>();
        mascotasRepo.findAll().forEach(mascota -> mascotasList.add(mascota));
        return mascotasList;
    }

    public Mascotas getMascotaById(Integer id){
        return mascotasRepo.findById(id).get();
    }

    public ArrayList<Mascotas> obtenerPorDueno(Integer id){
        return mascotasRepo.findByDueno(id);
    }

    public ArrayList<Mascotas> obtenerPorVeterinario(Integer id){
        return mascotasRepo.findByVeterinario(id);
    }


    //Metodo para guardar o actualizar registros en Empleados
    public boolean saveOrUpdateMascota(Mascotas mascota){
        Mascotas masco=mascotasRepo.save(mascota);
        if (mascotasRepo.findById(masco.getId())!=null){
            return true;
        }
        return false;
    }

    //Metodo para eliminar un registro de Empleado por Id
    public boolean deleteMascota(Integer id){
        mascotasRepo.deleteById(id);
        if(this.mascotasRepo.findById(id).isPresent()){
            return false;
        }
        return true;
    }
    
}

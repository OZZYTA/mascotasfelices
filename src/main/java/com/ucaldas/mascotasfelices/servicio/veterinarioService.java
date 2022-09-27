package com.ucaldas.mascotasfelices.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucaldas.mascotasfelices.modelos.Veterinarios;
import com.ucaldas.mascotasfelices.repositorio.veterinariosRepo;

@Service
public class veterinarioService {

    @Autowired
    veterinariosRepo veterinariosRepo;

    public List<Veterinarios> getAllVeterinarios(){
        List<Veterinarios> vetList = new ArrayList<>();
        veterinariosRepo.findAll().forEach(veterinario -> vetList.add(veterinario));  
        return vetList;
    }

    public Veterinarios getVeterinarioById(Integer id){
        return veterinariosRepo.findById(id).get();
    }

    public boolean saveOrUpdateVeterinario(Veterinarios veterinario){
        Veterinarios temp=veterinariosRepo.save(veterinario);
        if (veterinariosRepo.findById(temp.getId())!=null){
            return true;
        }
        return false;
    }

    public boolean deleteVeterinario(Integer id){
        veterinariosRepo.deleteById(id);  

        if (veterinariosRepo.findById(id)!=null){ 
            return true;
        }
        return false;
    }
    
}

    


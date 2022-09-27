package com.ucaldas.mascotasfelices.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucaldas.mascotasfelices.modelos.duenos;
import com.ucaldas.mascotasfelices.repositorio.duenoRepo;

@Service
public class duenosService {

    @Autowired
    duenoRepo duenoRepo;

    public List<duenos> getAllDuenos(){
        List<duenos> duenosList = new ArrayList<>();
        duenoRepo.findAll().forEach(dueno -> duenosList.add(dueno));  
        return duenosList;
    }

    public duenos getDuenoById(Integer id){
        return duenoRepo.findById(id).get();
    }

    public boolean saveOrUpdateDueno(duenos dueno){
        duenos temp=duenoRepo.save(dueno);
        if (duenoRepo.findById(temp.getId())!=null){
            return true;
        }
        return false;
    }

    public boolean deleteDueno(Integer id){
        duenoRepo.deleteById(id);  

        if (duenoRepo.findById(id)!=null){ 
            return true;
        }
        return false;
    }
    
}

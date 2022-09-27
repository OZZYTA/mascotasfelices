package com.ucaldas.mascotasfelices.repositorio;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ucaldas.mascotasfelices.modelos.Mascotas;
@Repository
public interface mascotasRepo extends JpaRepository<Mascotas,Integer>{
    @Query(value="SELECT * FROM mascotas where duenos_id= ?1", nativeQuery=true)
    public abstract ArrayList<Mascotas> findByDueno(Integer id);

    @Query(value="SELECT * FROM mascotas where veterinarios_id= ?1", nativeQuery=true)
    public abstract ArrayList<Mascotas> findByVeterinario(Integer id);
}

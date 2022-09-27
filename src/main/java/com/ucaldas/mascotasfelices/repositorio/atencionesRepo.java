package com.ucaldas.mascotasfelices.repositorio;

import com.ucaldas.mascotasfelices.modelos.Mascotas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ucaldas.mascotasfelices.modelos.Atenciones;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface atencionesRepo extends JpaRepository<Atenciones,Integer> {

    @Query(value="SELECT * FROM atenciones where mascotas_id= ?1", nativeQuery=true)
    public abstract ArrayList<Atenciones> findByMascota(Integer id);

    @Query(value="SELECT * FROM atenciones where veterinarios_id= ?1", nativeQuery=true)
    public abstract ArrayList<Atenciones> findByVeterinario(Integer id);


    @Query(value="SELECT fecha FROM atenciones WHERE mascotas_id=?1 ORDER BY fecha ASC LIMIT 1", nativeQuery=true)
    public abstract Date findOldestDate(Integer id);
}

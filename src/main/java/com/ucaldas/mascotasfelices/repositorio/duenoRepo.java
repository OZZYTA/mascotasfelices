package com.ucaldas.mascotasfelices.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucaldas.mascotasfelices.modelos.duenos;
@Repository
public interface duenoRepo extends JpaRepository<duenos, Integer>{
    
}

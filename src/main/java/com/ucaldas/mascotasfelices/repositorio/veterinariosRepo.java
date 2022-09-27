package com.ucaldas.mascotasfelices.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucaldas.mascotasfelices.modelos.Veterinarios;
@Repository
public interface veterinariosRepo extends JpaRepository<Veterinarios,Integer> {
    
}

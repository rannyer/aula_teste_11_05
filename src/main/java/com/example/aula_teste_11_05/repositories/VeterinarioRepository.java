package com.example.aula_teste_11_05.repositories;


import com.example.aula_teste_11_05.models.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
}
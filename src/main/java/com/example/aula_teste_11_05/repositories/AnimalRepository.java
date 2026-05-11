package com.example.aula_teste_11_05.repositories;

import com.example.aula_teste_11_05.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}

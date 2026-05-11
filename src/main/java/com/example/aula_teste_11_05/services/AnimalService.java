package com.example.aula_teste_11_05.services;

import com.example.aula_teste_11_05.models.Animal;
import com.example.aula_teste_11_05.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {


    private AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> pegarTodos(){
        return animalRepository.findAll();
    }

    public Animal salvar(Animal animal){
        return animalRepository.save(animal);
    }


}

package com.example.aula_teste_11_05.services;

import com.example.aula_teste_11_05.models.Animal;
import com.example.aula_teste_11_05.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {


    private AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> pegarTodos(){
        return animalRepository.findAll();
    }
    public Animal buscarPorId(Long id){
        Optional<Animal> animalOptional = animalRepository.findById(id);

        if(animalOptional.isEmpty()){
            throw new RuntimeException("Animal não encontrado");
        }
        return animalOptional.get();
    }

    public Animal salvar(Animal animal){
        return animalRepository.save(animal);
    }


}

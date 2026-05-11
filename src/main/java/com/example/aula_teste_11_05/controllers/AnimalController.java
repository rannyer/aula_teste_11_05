package com.example.aula_teste_11_05.controllers;

import com.example.aula_teste_11_05.models.Animal;
import com.example.aula_teste_11_05.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;


    @GetMapping
    public ResponseEntity<List<Animal>> pegarTodos(){
        return  ResponseEntity.ok(animalService.pegarTodos());
    }
    @PostMapping
    public ResponseEntity<Animal> salvar(@RequestBody Animal animal){
        return ResponseEntity.ok(animalService.salvar(animal));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> pegarPeloId(@PathVariable Long id){
        try{
            return ResponseEntity.ok(animalService.buscarPorId(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        try {
            return ResponseEntity.ok(animalService.deletar(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

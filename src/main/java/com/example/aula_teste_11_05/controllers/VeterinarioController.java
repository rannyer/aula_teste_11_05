package com.example.aula_teste_11_05.controllers;



import com.example.aula_teste_11_05.models.Veterinario;
import com.example.aula_teste_11_05.services.VeterinarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {

    private VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @GetMapping
    public ResponseEntity<List<Veterinario>> pegarTodos() {
        return ResponseEntity.ok(veterinarioService.pegarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(veterinarioService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody Veterinario veterinario
    ){
        try {
            return ResponseEntity.ok(veterinarioService.atualiar(id, veterinario));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Veterinario> salvar(@RequestBody Veterinario veterinario) {
        return ResponseEntity.ok(veterinarioService.salvar(veterinario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    veterinarioService.deletar(id)
            );

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
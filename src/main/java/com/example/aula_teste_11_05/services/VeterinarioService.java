package com.example.aula_teste_11_05.services;

import com.example.aula_teste_11_05.models.Veterinario;
import com.example.aula_teste_11_05.repositories.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioService {

    private VeterinarioRepository veterinarioRepository;

    public VeterinarioService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    public List<Veterinario> pegarTodos() {
        return veterinarioRepository.findAll();
    }

    public Veterinario buscarPorId(Long id) {
        return veterinarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Veterinário não encontrado")
        );
    }

    public Veterinario salvar(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    public Veterinario atualiar(Long id, Veterinario novoVet){
        Veterinario veterinario = veterinarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Veterinário não encontrado")
        );

        veterinario.setNome(novoVet.getNome());
        veterinario.setEspecialidade(novoVet.getEspecialidade());
        veterinario.setAnosExperiencia(novoVet.getAnosExperiencia());

        return veterinarioRepository.save(veterinario);
    }

    public String deletar(Long id) {
        Optional<Veterinario> veterinarioOptional = veterinarioRepository.findById(id);

        if (veterinarioOptional.isEmpty()) {
            throw new RuntimeException("Veterinário não encontrado");
        }

        veterinarioRepository.deleteById(id);
        return "Veterinário excluído com sucesso";
    }
}
package com.example.aula_teste_11_05.cucumber;

import com.example.aula_teste_11_05.models.Veterinario;
import com.example.aula_teste_11_05.repositories.VeterinarioRepository;
import com.example.aula_teste_11_05.services.VeterinarioService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class VeterinarioSteps {

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    private Veterinario veterinarioSalvo;
    private Veterinario veterinarioAtualizado;

    @Given("O Veterinario com o nome {string}, especialidade {string} e {int} anos de experiencia existe no sistema")
    public void queExisteUmVeterinarioCadatrado(
            String nome,
            String especialidade,
            int anos
    ){
        veterinarioRepository.deleteAll();

        veterinarioSalvo = veterinarioRepository.save(
                new Veterinario(
                        null,
                        nome,
                        especialidade,
                        anos
                )
        );
    }

    @When("eu atualizo os dados desse Veterinario")
    public void euAtualizaOsDadosDesseVeterinario(){
        Veterinario novosDados =  new Veterinario(
                null,
                "Dr. João Silva",
                "Cirurgia e Dermatologia",
                12
        );
    veterinarioAtualizado = veterinarioService.atualiar(
            veterinarioSalvo.getId(),
            novosDados
    );
    }
    @Then("o veterinario deve ser retornado com os dados atualizados")
    public void oVeterinarioDeveSerRetornadoComOsDadosAtualizados(){
        assert veterinarioAtualizado.getId().equals(veterinarioSalvo.getId());
        assert veterinarioAtualizado.getNome().equals("Dr. João Silva");
        assert veterinarioAtualizado.getEspecialidade().equals("Cirurgia e Dermatologia");
        assert veterinarioAtualizado.getAnosExperiencia() == 12;
    }




}

Feature: Atualizacao de Veterinario
  Scenario: Atualizar Veterinario com sucesso
    Given O Veterinario com o nome "Dr. Smith", especialidade "Cirurgia" e 18 anos de experiencia existe no sistema
    When eu atualizo os dados desse Veterinariop
    Then o veterinario deve ser retornado com os dados atualizados

    Scenario: Retornar um usuario do sistema
      Given O Veterinario com o nome "Dr. Smith", especialidade "Cirurgia" e 18 anos de experiencia existe no sistema
      When eu busco por esse veterinario
      Then o veterinario deve ser retornado com os dados corretos
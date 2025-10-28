package br.com.alura.marketplace.domain.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import br.com.alura.marketplace.domain.entity.Produto;

class CadastroProdutoUseCaseTest {

    protected CadastroProdutoUseCase cadastroProdutoUseCase;

    @BeforeEach
    void beforeEach() {
        cadastroProdutoUseCase = new CadastroProdutoUseCase(null, null, null, null);
    }

    @DisplayName("Quando cadastrar produto")
    @Nested
    class Cadastrar {

        @DisplayName("Então deve executar com sucesso")
        @Nested
        class Sucesso {

            @DisplayName("Dado um produto com todos os campos")
            @Test
            void teste1() {
                // Dado
                var produto = Produto.builder()
                        .nome("Nome 1")
                        .categoria("Livro")
                        .build();

                // Quando
                var atual = cadastroProdutoUseCase.cadastrar(produto);

                // Então

            }

        }

    }
}

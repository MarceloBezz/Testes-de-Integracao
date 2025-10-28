package br.com.alura.marketplace.domain.usecase;

import static br.com.alura.marketplace.domain.factory.ProdutoFactory.criarProduto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CadastroProdutoUseCaseTest {

    protected CadastroProdutoUseCase cadastroProdutoUseCase;

    @BeforeEach
    void beforeEach() {
        cadastroProdutoUseCase = new CadastroProdutoUseCase();
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
                var produto = criarProduto().comTodosOsCampos();

                // Quando
                var atual = cadastroProdutoUseCase.cadastrar(produto);

                // Então

            }

        }

    }
}

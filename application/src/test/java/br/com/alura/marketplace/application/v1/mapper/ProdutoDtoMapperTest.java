package br.com.alura.marketplace.application.v1.mapper;

import static br.com.alura.marketplace.application.v1.assertions.ProdutoAssertions.afirmaQue_Produto;
import static br.com.alura.marketplace.application.v1.dto.ProdutoDtoFactory.criarProdutoDtoRequest;
import static org.mapstruct.factory.Mappers.getMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ProdutoDtoMapperTest {

    ProdutoDtoMapper mapper = getMapper(ProdutoDtoMapper.class);

    @DisplayName("Quando converter ProdutoDTO.Request")
    @Nested
    class Converter {

        @DisplayName("Então deve executar com sucesso")
        @Nested
        class Sucesso {

            @DisplayName("Dado um ProdutoDTO.Request com todos os campos")
            @Test
            void teste1() {
                // Dado
                var dto = criarProdutoDtoRequest().comTodosOsCampos();

                // Quando
                var atual = mapper.converter(dto);

                // Então
                // assertEquals(atual.getNome(), "Produto Teste");  < legibilidade
                afirmaQue_Produto(atual).foiConvertidoDe_ProdutoDto_Request();
            }

        }

    }
}

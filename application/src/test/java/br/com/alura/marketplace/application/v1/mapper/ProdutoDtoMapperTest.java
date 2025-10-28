package br.com.alura.marketplace.application.v1.mapper;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mapstruct.factory.Mappers.getMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import br.com.alura.marketplace.application.v1.dto.FotoDto;
import br.com.alura.marketplace.application.v1.dto.ProdutoDto;
import br.com.alura.marketplace.domain.entity.Produto.Status;

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
                var dto = ProdutoDto.Request.builder()
                        .nome("Produto Teste")
                        .categoria("Categoria 1")
                        .status(Status.AVAILABLE)
                        .descricao("Descricao do Produto Teste")
                        .valor(new BigDecimal("1.99"))
                        .foto(FotoDto.Request.builder()
                                .fileName("file-name-1.jpg")
                                .base64("Y2Fyb2xpbmEgSGVycmVyYQ==")
                                .build())
                        .tag("tag-1")
                        .build();

                // Quando
                var atual = mapper.converter(dto);

                // Então
                // assertEquals(atual.getNome(), "Produto Teste");  < legibilidade
                assertThat(atual.getNome()).isEqualTo("Produto Teste");
                assertThat(atual.getCategoria()).isEqualTo("Categoria 1");
                assertThat(atual.getStatus()).isEqualTo(Status.AVAILABLE);
                assertThat(atual.getDescricao()).isEqualTo("Descricao do Produto Teste");
                assertThat(atual.getValor()).isEqualTo(new BigDecimal("1.99"));
                assertThat(atual.getTags().getFirst()).isEqualTo("tag-1");
                assertThat(atual.getPetStorePetId()).isNull();
                assertThat(atual.getCriadoEm()).isNull();
                assertThat(atual.getAtualizadoEm()).isNull();
                // E
                var foto = atual.getFotos().getFirst();
                assertThat(foto.getFotoId()).isNull();
                assertThat(foto.getFileName()).isEqualTo("file-name-1.jpg");
                assertThat(foto.getBase64()).isEqualTo("Y2Fyb2xpbmEgSGVycmVyYQ==");
            }

        }

    }
}

package br.com.alura.marketplace.application.v1.dto;

import static br.com.alura.marketplace.application.v1.dto.FotoDtoFactory.criaFotoDtoRequest;
import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;

import br.com.alura.marketplace.domain.entity.Produto.Status;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class ProdutoDtoFactory {

    public static Request criarProdutoDtoRequest() {
        return new Request(ProdutoDto.Request.builder());
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static class Request {

        private final ProdutoDto.Request.RequestBuilder builder;

        public ProdutoDto.Request comTodosOsCampos() {
            return builder
                    .nome("Produto Teste")
                    .categoria("Categoria 1")
                    .status(Status.AVAILABLE)
                    .descricao("Descricao do Produto Teste")
                    .valor(new BigDecimal("1.99"))
                    .foto(criaFotoDtoRequest().comTodosOsCampos())
                    .tag("tag-1").build();
        }
    }
}

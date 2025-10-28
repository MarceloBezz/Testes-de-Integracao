package br.com.alura.marketplace.domain.factory;

import static br.com.alura.marketplace.domain.factory.FotoFactory.criaFoto;
import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.alura.marketplace.domain.entity.Produto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class ProdutoFactory {

    public static Request criarProduto() {
        return new Request(Produto.builder());
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static class Request {

        private final Produto.ProdutoBuilder builder;

        public Produto comTodosOsCampos() {
            return builder
                    .nome("Produto Teste")
                    .categoria("Categoria 1")
                    .status(Produto.Status.AVAILABLE)
                    .descricao("Descrição completa do Produto Teste (Disponível)")
                    .valor(new BigDecimal("1.99"))
                    .foto(criaFoto().comTodosOsCampos()) // se você tiver uma FotoFactory
                    .tag("tag-1")
                    .petStorePetId(123L)
                    .criadoEm(LocalDateTime.now())
                    .atualizadoEm(LocalDateTime.now())
                    .build();
        }
    }
}
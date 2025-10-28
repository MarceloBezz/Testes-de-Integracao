package br.com.alura.marketplace.application.v1.assertions;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;

import br.com.alura.marketplace.domain.entity.Produto;
import br.com.alura.marketplace.domain.entity.Produto.Status;
import static org.assertj.core.api.Assertions.assertThat;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PRIVATE)
public final class ProdutoAssertions {

    private final Produto atual;

    public static ProdutoAssertions afirmaQue_Produto(Produto atual) {
        return new ProdutoAssertions(atual);
    }

    /**
     * @see br.com.alura.marketplace.application.v1.dto.ProdutoDtoFactory;  
     */    
    public void foiConvertidoDe_ProdutoDto_Request() {
        assertThat(atual.getNome()).isEqualTo("Produto Teste");
        assertThat(atual.getCategoria()).isEqualTo("Categoria 1");
        assertThat(atual.getStatus()).isEqualTo(Status.AVAILABLE);
        assertThat(atual.getDescricao()).isEqualTo("Descricao do Produto Teste");
        assertThat(atual.getValor()).isEqualTo(new BigDecimal("1.99"));
        assertThat(atual.getTags().getFirst()).isEqualTo("tag-1");
        assertThat(atual.getPetStorePetId()).isNull();
        assertThat(atual.getCriadoEm()).isNull();
        assertThat(atual.getAtualizadoEm()).isNull();

        var foto = atual.getFotos().getFirst();
        assertThat(foto.getFotoId()).isNull();
        assertThat(foto.getFileName()).isEqualTo("file-name-1.jpg");
        assertThat(foto.getBase64()).isEqualTo("Y2Fyb2xpbmEgSGVycmVyYQ==");
    }
}

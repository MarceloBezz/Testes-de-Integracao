package br.com.alura.marketplace.domain.usecase;

import static br.com.alura.marketplace.domain.entity.ProdutoFactory.criarProduto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.marketplace.domain.entity.Produto;
import br.com.alura.marketplace.domain.exception.BusinessException;
import br.com.alura.marketplace.domain.repository.BucketRepository;
import br.com.alura.marketplace.domain.repository.PetStoreRepository;
import br.com.alura.marketplace.domain.repository.ProdutoRepository;
import br.com.alura.marketplace.domain.repository.QueueRepository;

@ExtendWith(MockitoExtension.class)
class CadastroProdutoUseCaseTest {

    @InjectMocks
    protected CadastroProdutoUseCase cadastroProdutoUseCase;

    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    PetStoreRepository petStoreRepository;

    @Mock
    BucketRepository bucketRepository;

    @Mock
    QueueRepository queueRepository;

    @DisplayName("Quando cadastrar produto")
    @Nested
    class Cadastrar {

        @DisplayName("Então deve executar com sucesso")
        @Nested
        class Sucesso {

            @BeforeEach
            void beforeEach() {
                when(produtoRepository.save(any()))
                        .thenAnswer(invocationOnMock -> {
                            Produto produto = invocationOnMock.getArgument(0);
                            setField(produto, "produtoId", UUID.fromString("1572146a-115b-4bd2-a89f-aa17616f73ec"));
                            return produto;
                        });
            }

            @DisplayName("Dado um produto com todos os campos")
            @Test
            void teste1() {
                // Dado
                var produto = criarProduto().comTodosOsCampos();

                // Quando
                var atual = cadastroProdutoUseCase.cadastrar(produto);

                // Então
                assertThat(atual.getProdutoId())
                        .isEqualTo(UUID.fromString("1572146a-115b-4bd2-a89f-aa17616f73ec"));
            }

        }

        @DisplayName("Então deve retornar erro")
        @Nested
        class Falha {

            @DisplayName("Dado um produto com o nome que começa com -")
            @Test
            void teste1() {
                // Dado
                var produto = criarProduto().comTodosOsCampos();
                setField(produto, "nome", "- Nome 2");

                // Quando
                var atual = assertThrows(BusinessException.class, () -> cadastroProdutoUseCase.cadastrar(produto));

                // Então
                assertThat(atual).hasMessage("O nome não pode começar com -");
            }
        }

    }
}

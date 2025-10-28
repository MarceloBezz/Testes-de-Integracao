package br.com.alura.marketplace.domain.usecase;

import br.com.alura.marketplace.domain.entity.Produto;
import br.com.alura.marketplace.domain.repository.BucketRepository;
import br.com.alura.marketplace.domain.repository.PetStoreRepository;
import br.com.alura.marketplace.domain.repository.ProdutoRepository;
import br.com.alura.marketplace.domain.repository.QueueRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.alura.marketplace.domain.util.ValidationUtil.validate;

@RequiredArgsConstructor
@Service
public class CadastroProdutoUseCase {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PetStoreRepository petStoreRepository;

    @Autowired
    private BucketRepository bucketRepository;

    @Autowired
    private QueueRepository queueRepository;
    
    public Produto cadastrar(Produto produto) {
        validate(produto);

        if (!produto.getFotos().isEmpty())
            produto.getFotos()
                    .forEach(bucketRepository::armazenar);

        var produtoPetCadastrado = petStoreRepository.cadastrarPet(produto);

        produto.atualizar(produtoPetCadastrado);

        var produtoSalvo = produtoRepository.save(produto);

        queueRepository.notificarCadastro(produtoSalvo);

        return produtoSalvo;
    }
}

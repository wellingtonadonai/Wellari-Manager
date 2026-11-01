package com.wellari.service;

import com.wellari.exception.ProdutoNaoEncontradoException;
import com.wellari.model.Produto;
import com.wellari.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository){
        this.repository = repository;
    }

    public Produto cadastrarProduto(Produto produto){
        return repository.save(produto);
    }

    public List<Produto> listarProdutos(){
        return repository.findAll();
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado){
        Produto produtoNovo = repository.findById(id)
        .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        produtoNovo.setNome(produtoAtualizado.getNome());
        produtoNovo.setPreco(produtoAtualizado.getPreco());
        produtoNovo.setCategoria(produtoAtualizado.getCategoria());
        produtoNovo.setDisponivel(produtoAtualizado.isDisponivel());
        produtoNovo.setQuantidadeDeEstoque(produtoAtualizado.getQuantidadeDeEstoque());

        return repository.save(produtoNovo);

    }

    public Optional<Produto> buscarProdutoPorId(Long id){
        return repository.findById(id);
    }

    public void deletarProduto(Long id){

        Optional<Produto> produto = buscarProdutoPorId(id);

        if (produto.isEmpty()) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado");
        }
        repository.deleteById(id);
    }

}

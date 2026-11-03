package com.wellari.service;

import com.wellari.exception.ProdutoNaoEncontradoException;
import com.wellari.model.Produto;
import com.wellari.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public Produto buscarProdutoPorId(Long id){
        return repository.findById(id).orElseThrow(()->
        new ProdutoNaoEncontradoException("Produto não encontrado"));
    }

    public void deletarProduto(Long id){

        Produto produto = buscarProdutoPorId(id);
        repository.deleteById(id);
    }

}

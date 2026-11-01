package com.wellari.controller;

import com.wellari.model.Produto;
import com.wellari.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service){
        this.service = service;
    }

    @PostMapping
    public Produto cadastrarProduto(@RequestBody Produto produto){
        return service.cadastrarProduto(produto);
    }
    @GetMapping
    public List<Produto> listarProdutos(){
        return service.listarProdutos();
    }

    @GetMapping("/{id}")
    public Optional<Produto> buscarPeloId(@PathVariable Long id){
        return service.buscarProdutoPorId(id);
    }
    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id){
        service.deletarProduto(id);
    }
}

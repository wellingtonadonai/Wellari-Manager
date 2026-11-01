package com.wellari.controller;

import com.wellari.model.Produto;
import com.wellari.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Produto> cadastrarProduto(@Valid @RequestBody Produto produto){

        Produto produtoSalvo = service.cadastrarProduto(produto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoSalvo);
    }
    @PutMapping("{id}")
    public Produto atualizarProdutos( @PathVariable Long id,
                                      @Valid @RequestBody Produto produtoAtualizado){

        return service.atualizarProduto(id, produtoAtualizado);
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

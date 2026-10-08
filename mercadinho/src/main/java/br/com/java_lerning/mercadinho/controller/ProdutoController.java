package br.com.java_lerning.mercadinho.controller;


import br.com.java_lerning.mercadinho.dto.ProdutoDTO;
import br.com.java_lerning.mercadinho.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

/* A classe Controller não é responsavel por validar nada apenas direcionar!
   ela apenas direciona cada requisição para o seu devido responsavel!       */


@RestController //Indica para o Spring que está classe é um controller de resquisições RESTful
@RequestMapping("/produto")//mapeia todas as requisições da url /produtos
public class ProdutoController {


    @Autowired //Faz a injenção de dependencias automaticamete(não preciso ficar inicializando!)
    private ProdutoService produtoService;

    //salva um produto
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProdutoDTO produtoDTO, UriComponentsBuilder uriBuilder){
            ProdutoDTO produtoSalvo = produtoService.save(produtoDTO);
            URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produtoSalvo.id()).toUri();
            return ResponseEntity.created(uri).body(produtoSalvo);

    }

    //busca todos os produtos
    @GetMapping
    public ResponseEntity<?> searchAll(Pageable pageable){
            return ResponseEntity.ok(produtoService.findAll(pageable));
    }

    //busca por id
    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable UUID id){
            return ResponseEntity.ok(produtoService.findById(id));
    }

    //atualiza produto por id
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id,@RequestBody ProdutoDTO produtoDTO ){
            return ResponseEntity.ok(produtoService.update(id,produtoDTO));
    }

    //deleta produto pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
            return ResponseEntity.noContent().build();
    }
}

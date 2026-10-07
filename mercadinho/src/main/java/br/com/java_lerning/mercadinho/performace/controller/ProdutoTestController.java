package br.com.java_lerning.mercadinho.performace.controller;

import br.com.java_lerning.mercadinho.model.Produto;
import br.com.java_lerning.mercadinho.repository.ProdutoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//Esta classe serve como exemeplos de otimização na hora de fazer buscas em bancos com grandes quantidades de registros

@RequestMapping("produto/performace")
@RestController
public class ProdutoTestController {

    @Autowired
    ProdutoRepository produtoRepository;

    //Uma busca total sem nenhuma tecnica de otimização
    @GetMapping("/sem-paginacao")
    public ResponseEntity<List<Produto>> sem_paginacao(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        var produtos = produtoRepository.findAll();
        stopWatch.stop();

        System.out.println("Tempo : " + stopWatch.getTotalTimeMillis() + " ms");
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/com-paginacao")// /com-paginacao?page=X&size=Y -> X e Y sao numeros (tamanho padrão é 20)
    public ResponseEntity<Page<Produto>> com_paginacao(Pageable pageable){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
                                        // findAll do proprio jpa já possui um metodo com busca de pagincao e retorna um page
        var produtos = produtoRepository.findAll(pageable);
        stopWatch.stop();

        System.out.println("Tempo : " + stopWatch.getTotalTimeMillis() + " ms");
        return ResponseEntity.ok(produtos);
    }
}

package br.com.java_lerning.mercadinho.performace;

import br.com.java_lerning.mercadinho.model.Produto;
import br.com.java_lerning.mercadinho.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Component
public class MercadinhoSeed implements CommandLineRunner {

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Produto> produtos = new ArrayList<>();

        for (int i = 0; i <= 100000; i++){
            Produto produto = new Produto();
            produto.setNome("Produto " + i);
            produto.setQuantidade((i % 100));
            produto.setDataExpiracao(LocalDate.now().minusDays((i % 30)));
            produto.setValor(BigDecimal.valueOf(10 + (i % 80)));
            produtos.add(produto);
        }
        produtoRepository.saveAll(produtos);
    }
}

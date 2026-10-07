package br.com.java_lerning.mercadinho.repository;

import br.com.java_lerning.mercadinho.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    /* O Spring já automaticando identifca palavras chaves e altera o sql de acordo como nesse caso, eu coloquei
    * o (Containig) que verifica se a string é parecida e o (IgnoreCase) que ignora as diferenças de caixa alta e baixa
    * na hora de compapar.*/
    /*Além disso é possivel criar seus proprios codigos Sql como no exemplo abaixo*/

    /*  Utilizando JPQL
    @Query("SELECT p FROM Produto p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Produto> buscarPorNomeManual(@Param("name") String name, Pageable pageable);
       */

    /* Utilizando SQL (Como estamos lidando com paginas temos que adicionar o countQuery por causa do nativeQuery!)
    @Query(value = "SELECT * FROM produto WHERE LOWER(nome) LIKE LOWER(CONCAT('%', :name, '%'))",
           countQuery = "SELECT COUNT(*) FROM produto WHERE LOWER(produto) LIKE LOWER(CONCAT('%', :name, '%'))",
           nativeQuery = true)
    Page<Produto> buscarPorNomeSqlNativo(@Param("name") String name, Pageable pageable);
    */
    Page<Produto> findByNomeContainingIgnoreCase(Pageable pageable, String nome);

}

package br.com.java_lerning.mercadinho.util;

import br.com.java_lerning.mercadinho.dto.ProdutoDTO;
import br.com.java_lerning.mercadinho.model.Produto;

public class EntityConverter {

    public static ProdutoDTO toDTO(Produto produto){
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getQuantidade(),
                produto.getDataExpiracao()
        );
    }

    // dto -> produto
    public static Produto toEntity(ProdutoDTO produtoDTO) {
        return new Produto(
                produtoDTO.id(),
                produtoDTO.nome(),
                produtoDTO.valor(),
                produtoDTO.quantidade(),
                produtoDTO.dataExpiracao()
        );
    }
}

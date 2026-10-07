package br.com.java_lerning.mercadinho.service;

import br.com.java_lerning.mercadinho.dto.ProdutoDTO;
import br.com.java_lerning.mercadinho.model.Produto;
import br.com.java_lerning.mercadinho.repository.ProdutoRepository;
import br.com.java_lerning.mercadinho.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service //Indica para o Spring que está classe é uma service
/*A service é responsavel por toda a regra de negocio */
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    //cria um produto e salva no banco
    public ProdutoDTO saveBD(ProdutoDTO produtoDTO){

        //Valida se algum campo está vazio e retorna uma execeção de argumento ilegal
        if(produtoDTO.nome() == null || produtoDTO.quantidade() <= 0 || produtoDTO.valor() == null || produtoDTO.dataExpiracao() == null){
            throw new IllegalArgumentException("Os campos precisam ser válidos, por favor preencher corretamente");
        }

        Produto produto = EntityConverter.toEntity(produtoDTO);

        return EntityConverter.toDTO(produtoRepository.save(produto));
    }

    //busca produto pelo id
    public Produto findById(UUID id){
            Optional<Produto> produtoOptional = produtoRepository.findById(id);
            return produtoOptional.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    //busca todos os produtos
    public Page<ProdutoDTO> findAll(Pageable pageable){
        Page<ProdutoDTO> produtoDTOs;
        Page<Produto> produtos = produtoRepository.findAll(pageable);
        produtoDTOs = produtos.map(EntityConverter::toDTO);
        return produtoDTOs;

    }

    //FAZER UM JEITO DE VERIFICAR A DATA POSI PRECISA DO DIA E MES COM DOIS DIGITOS!!!
    public Produto update(UUID id, ProdutoDTO produtoAtualizado){

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setValor(produtoAtualizado.valor());
        produto.setNome(produtoAtualizado.nome());
        produto.setQuantidade(produtoAtualizado.quantidade());
        produto.setDataExpiracao(produtoAtualizado.dataExpiracao());

        return produtoRepository.save(produto);
    }

    //deleta pelo id
    public void deleteById(UUID id){
            Optional<Produto> optionalProduto = produtoRepository.findById(id);
            if(optionalProduto.isPresent()){
                produtoRepository.deleteById(id);
            }
            else  {
                throw new RuntimeException("Produto não encontrado");
            }
    }
}

package br.com.java_lerning.mercadinho.dto;

import br.com.java_lerning.mercadinho.model.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


public record ProdutoDTO(UUID id, String nome, BigDecimal valor, int quantidade, LocalDate dataExpiracao) { }
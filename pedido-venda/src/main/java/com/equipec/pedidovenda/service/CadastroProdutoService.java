package com.equipec.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.equipec.pedidovenda.model.Produto;
import com.equipec.pedidovenda.repository.Produtos;
import com.equipec.pedidovenda.util.jpa.Transactional;

public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;
	
	@Transactional
	public Produto salvar(Produto produto) throws NegocioException {
		Produto produtoExistente = produtos.porId(produto.getId());
		
		if (produtoExistente != null && !produtoExistente.equals(produto)) {
			throw new NegocioException("Já existe um produto com o SKU informado.");
		}
		
		return produtos.guardar(produto);
	}
	
}

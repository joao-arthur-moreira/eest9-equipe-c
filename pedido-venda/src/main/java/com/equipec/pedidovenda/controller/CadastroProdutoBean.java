package com.equipec.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.equipec.pedidovenda.model.Produto;
import com.equipec.pedidovenda.service.CadastroProdutoService;
import com.equipec.pedidovenda.service.NegocioException;
import com.equipec.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	
	private Produto produto;	
	
	public CadastroProdutoBean() {
		limpar();
	}
	
	public void inicializar() {
		if (this.produto == null) {
			limpar();
		}		
	}
	
	private void limpar() {
		produto = new Produto();
	}
	
	public void salvar() {
		try {
			this.produto = cadastroProdutoService.salvar(this.produto);
			limpar();
			
			FacesUtil.addInfoMessage("Produto salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}

	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;		
	}

	public boolean isEditando() {
		return this.produto.getId() != null;
	}

}
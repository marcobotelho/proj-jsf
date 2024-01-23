package com.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.model.UsuarioModel;

@ViewScoped
@ManagedBean(name = "usuarioBean")
public class UsuarioBean {

	private UsuarioModel usuario;

	@PostConstruct
	private void init() {
		usuario = new UsuarioModel();
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	// Method to clear form
	public void limpar() {
		init();
	}

	// Method to save
	public String salvar() {
		try {
			System.out.println("Nome: " + this.usuario.getNome());
			System.out.println("Email: " + this.usuario.getEmail());
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	// Method to cancel
	public String cancelar() {
		return "index.xhtml?faces-redirect=true";
	}

	// Method to delete
	public String excluir() {
		return "index.xhtml?faces-redirect=true";
	}

	// Method to update
	public String atualizar() {
		return "index.xhtml?faces-redirect=true";
	}

	// Method to search
	public void search() {
		// Add your code here to implement the search functionality
	}

}

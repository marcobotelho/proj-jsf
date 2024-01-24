package com.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import com.dao.GenericDao;
import com.model.UsuarioModel;

@ConversationScoped
@Named("usuarioBean")
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private GenericDao<UsuarioModel, Long> usuarioDAO = new GenericDao<>(UsuarioModel.class);

	private UsuarioModel usuario;

	private List<UsuarioModel> usuarios;

	@PostConstruct
	private void init() {
		usuario = new UsuarioModel();
		usuarios = usuarioDAO.findAll();
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public List<UsuarioModel> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioModel> usuarios) {
		this.usuarios = usuarios;
	}

	public void limpar() {
		init();
	}

	public String salvar() {
		try {
			System.out.println("Nome: " + this.usuario.getNome());
			System.out.println("Email: " + this.usuario.getEmail());
			usuarioDAO.save(this.usuario);
			init();
			for (UsuarioModel u : usuarios) {
				System.out.println(u);
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public String cancelar() {
		return "index.xhtml?faces-redirect=true";
	}

	public String excluir() {
		usuarioDAO.delete(this.usuario);
		init();
		return null;
	}

	public String atualizar() {
		usuarioDAO.update(this.usuario);
		init();
		return null;
	}

	public String buscarPorId() {
		usuario = usuarioDAO.findById(this.usuario.getId());
		return null;
	}

	public String buscarTodos() {
		usuarioDAO.findAll();
		return null;
	}

}

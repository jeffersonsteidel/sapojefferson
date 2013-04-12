package br.com.siscone.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import br.com.siscone.dao.DAO;
import br.com.siscone.dao.SubLotacaoDAO;
import br.com.siscone.dao.UsuarioDAO;
import br.com.siscone.entity.Cronograma;
import br.com.siscone.entity.Lotacao;
import br.com.siscone.entity.Mensagem;
import br.com.siscone.entity.SubLotacao;
import br.com.siscone.entity.Usuario;
import br.com.siscone.util.Encripty;

public class UsuarioController {

	private Usuario usuario;
	private Usuario usuarioAutenticado;
	private String novaSenha;
	private String confirmarSenha;
	private List<Usuario> usuarioList = new ArrayList<Usuario>();
	private Mensagem mensagem;
	private List<Mensagem> mensagemList;
	private Cronograma cronograma;
	private List<SelectItem> lotacoes = new ArrayList<SelectItem>();
	private List<SelectItem> subLotacoes = new ArrayList<SelectItem>();


	public List<Mensagem> getMensagemList() {
		return mensagemList;
	}

	public void setMensagemList(List<Mensagem> mensagemList) {
		this.mensagemList = mensagemList;
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public List<SelectItem> getLotacoes() {
		return lotacoes;
	}

	public void setLotacoes(List<SelectItem> lotacoes) {
		this.lotacoes = lotacoes;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public Cronograma getCronograma() {
		return cronograma;
	}

	public void setCronograma(Cronograma cronograma) {
		this.cronograma = cronograma;
	}

	
	public List<SelectItem> getSubLotacoes() {
		return subLotacoes;
	}

	public void setSubLotacoes(List<SelectItem> subLotacoes) {
		this.subLotacoes = subLotacoes;
	}

	public UsuarioController() {

		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public void login() throws Exception {
		usuarioAutenticado = new Usuario();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		usuarioAutenticado = UsuarioDAO.getInstance().autentica(usuario);

		if (usuarioAutenticado != null) {
			session.setAttribute("usuarioLogado", usuarioAutenticado);
			buscar();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("listaMensagemInformativa.jsp");
			usuario = new Usuario();
			session.setMaxInactiveInterval(-1);
		} else {
			session.setAttribute("usuarioLogado", null);
			session.removeAttribute("user");
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Usuário ou Senha inválida!",
					"Siape ou Senha inválida!");
			FacesContext.getCurrentInstance().addMessage("", message);
		}
	}

	public void isAutenticado() throws IOException {
		if (usuario == null) {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("login.jsp");
		}
	}

	public void alterarSenha() throws IOException {
		usuario = new Usuario();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("alterarSenha.jsp");
	}

	public void carregarMenus() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("menus.jsp");
	}

	public void verificarSenha() throws NoSuchAlgorithmException {
		usuarioAutenticado = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");
		String senhaAtual = Encripty.criptografaSenha(usuario.getSenha());
		if (!usuarioAutenticado.getSenha().equals(senhaAtual)) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Senha Atual incorreta!",
					"Senha Atual incorreta!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} else if (!this.getNovaSenha().equals(this.getConfirmarSenha())) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"A Nova Senha não corresponde à Senha de confirmação!",
					"A Nova Senha não corresponde à Senha de confirmação!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} else {
			String novaSenha = Encripty.criptografaSenha(this.getNovaSenha());
			usuario.setLogin(usuarioAutenticado.getLogin());
			usuario.setSenha(novaSenha);
			usuario.setSubLotacao(usuarioAutenticado.getSubLotacao());
			usuario.getSubLotacao().setLotacao(usuarioAutenticado.getSubLotacao().getLotacao());
			usuario.setIndAdministrador(usuarioAutenticado
					.getIndAdministrador());
			usuario.setIndAprovador(usuarioAutenticado
					.getIndAprovador());
			usuario.setIndCadastrador(usuarioAutenticado
					.getIndCadastrador());
			usuario.setIndCadastradorSubLotacao(usuarioAutenticado
					.getIndCadastradorSubLotacao());
			usuario.setIndPriorizador(usuarioAutenticado
					.getIndPriorizador());
			usuario.setIndReplanejador(usuarioAutenticado
					.getIndReplanejador());
			UsuarioDAO.getInstance().update(usuario);
		}
	}

	public void cadastrar() throws Exception {
		try {
			usuario = new Usuario();
			usuario.setSubLotacao(new SubLotacao());
			usuario.getSubLotacao().setLotacao(new Lotacao());
			listarLotacoes();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastroUsuario.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void salvar() throws Exception {
		Usuario usuarioTemp = (Usuario) DAO.getInstance().getById(usuario.getLogin(), Usuario.class);
		if (usuarioTemp == null) {
			String senhaCriptografada = Encripty.criptografaSenha(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);
		}
		DAO.getInstance().saveOrUpdate(usuario);
		usuario = new Usuario();
		usuario.setSubLotacao(new SubLotacao());
		usuario.getSubLotacao().setLotacao(new Lotacao());
		listarLotacoes();
	}

	public Usuario editar() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		usuario = (Usuario) context.getExternalContext().getRequestMap()
				.get("list");
		if(usuario.getSubLotacao() == null){
			usuario.setSubLotacao(new SubLotacao());
			usuario.getSubLotacao().setLotacao(new Lotacao());
		}
		listarLotacoes();
		listarSubLotacoes();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadastroUsuario.jsp");
		return usuario;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listar() throws IOException {
		usuarioList = new ArrayList<Usuario>();
		usuarioList = DAO.getInstance().list(Usuario.class);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listaUsuario.jsp");
		return usuarioList;
	}

	@SuppressWarnings("unchecked")
	public List<SelectItem> listarLotacoes() {
		lotacoes = new ArrayList<SelectItem>();
		List<Lotacao> lotacaoList = new ArrayList<Lotacao>();
		lotacaoList = DAO.getInstance().list(Lotacao.class, "descricao");
		for (Lotacao lotacao : lotacaoList) {
			lotacoes.add(new SelectItem(lotacao.getCodigo(), lotacao
					.getDescricao()));
		}
		return lotacoes;
	}

	public void logout() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		session.invalidate();
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<SelectItem> listarSubLotacoes() {
		subLotacoes = new ArrayList<SelectItem>();
		List<SubLotacao> listSubLotacao = new ArrayList<SubLotacao>();
		listSubLotacao = (ArrayList<SubLotacao>) SubLotacaoDAO.getInstance()
				.listBySubLotacao(usuario.getSubLotacao().getLotacao());
		for (SubLotacao subLotacao : listSubLotacao) {
			subLotacoes.add(new SelectItem(subLotacao.getCodigo(),
					subLotacao.getDescricao()));
		}
		return subLotacoes;
	}
	
	@SuppressWarnings("unchecked")
	public void excluir() throws IOException {
		DAO.getInstance().delete(usuario);
		usuarioList = DAO.getInstance().list(Usuario.class);
	}

	@SuppressWarnings("unchecked")
	public List<Mensagem> buscar() throws IOException {
		mensagem = new Mensagem();
		mensagemList = new ArrayList<Mensagem>();
		mensagemList = DAO.getInstance().list(Mensagem.class);
		if (!mensagemList.isEmpty()) {
			int ultimaMensagem = mensagemList.size() - 1;
			mensagem = mensagemList.get(ultimaMensagem);
		}
		return mensagemList;
	}

}
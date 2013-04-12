<%@page import="java.util.Date"%>
<%@page import="javax.faces.context.FacesContext"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Instituto Federal do Paraná</title>
<link rel="StyleSheet" type="text/css" href="../css/messages-style.css"
	media="screen" />
</head>
<body bgcolor="#8ac444" text="uppercase">
	<%
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("usuarioLogado") == null) {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("login.jsp");
		}
	%>
	<f:subview id="menus">
		<a4j:loadScript src="../js/script.js" />
		<a4j:form id="form">
			<center>
				<h:graphicImage value="../images/topo.png" width="100%"
					height="150px" />
				<br>

				<rich:toolBar>

					<rich:dropDownMenu style="font-size: 13px;"
						rendered="#{usuarioController.usuarioAutenticado.indAdministrador}">
						<f:facet name="label">
							<h:panelGroup>
								<h:outputText value="Unidade" />
							</h:panelGroup>
						</f:facet>
						<rich:menuItem submitMode="ajax" value="Cadastrar Unidade"
							action="#{unidadeController.cadastrar}" />
						<rich:menuItem submitMode="ajax" value="Listar Unidade"
							action="#{unidadeController.listar}" />
					</rich:dropDownMenu>

					<rich:dropDownMenu style="font-size: 13px;"
						rendered="#{usuarioController.usuarioAutenticado.indAdministrador}">
						<f:facet name="label">
							<h:panelGroup>
								<h:outputText value="Cronograma" />
							</h:panelGroup>
						</f:facet>
						<rich:menuItem submitMode="ajax" value="Cadastrar Cronograma"
							action="#{cronogramaController.cadastrar}" />
						<rich:menuItem submitMode="ajax" value="Listar Cronograma"
							action="#{cronogramaController.listar}" />
					</rich:dropDownMenu>


					<rich:dropDownMenu style="font-size: 13px;">
						<f:facet name="label">
							<h:panelGroup>
								<h:outputText value="Mensagem" />
							</h:panelGroup>
						</f:facet>
						<rich:menuItem submitMode="ajax" value="Cadastrar Mensagem"
							action="#{mensagemController.cadastrar}"
							rendered="#{usuarioController.usuarioAutenticado.indAdministrador}" />
						<rich:menuItem submitMode="ajax" value="Listar Mensagem"
							action="#{mensagemController.listar}" />
					</rich:dropDownMenu>


					<rich:dropDownMenu style="font-size: 13px;"
						rendered="#{usuarioController.usuarioAutenticado.indAdministrador}">
						<f:facet name="label">
							<h:panelGroup>
								<h:outputText value="Lotação" />
							</h:panelGroup>
						</f:facet>
						<rich:menuItem submitMode="ajax" value="Cadastrar Lotação"
							action="#{lotacaoController.cadastrarLotacao}" />
						<rich:menuItem submitMode="ajax" value="Listar Lotação"
							action="#{lotacaoController.listarLotacao}" />
					</rich:dropDownMenu>

					<rich:dropDownMenu style="font-size: 13px;"
						rendered="#{usuarioController.usuarioAutenticado.indAdministrador}">
						<f:facet name="label">
							<h:panelGroup>
								<h:outputText value="Sub Lotação" />
							</h:panelGroup>
						</f:facet>
						<rich:menuItem submitMode="ajax" value="Cadastrar Sub Lotação"
							action="#{subLotacaoController.cadastrar}" />
						<rich:menuItem submitMode="ajax" value="Listar Sub Lotação"
							action="#{subLotacaoController.listar}" />
					</rich:dropDownMenu>

					<rich:dropDownMenu style="font-size: 13px;"
						rendered="#{usuarioController.usuarioAutenticado.indAdministrador}">
						<f:facet name="label">
							<h:panelGroup>
								<h:outputText value="Elemento" />
							</h:panelGroup>
						</f:facet>
						<rich:menuItem submitMode="ajax" value="Cadastrar Elemento"
							action="#{elementoController.cadastrar}" />
						<rich:menuItem submitMode="ajax" value="Listar Elemento"
							action="#{elementoController.listar}" />
					</rich:dropDownMenu>

					<rich:dropDownMenu style="font-size: 13px;"
						rendered="#{usuarioController.usuarioAutenticado.indAdministrador}">
						<f:facet name="label">
							<h:panelGroup>
								<h:outputText value="SubElemento" />
							</h:panelGroup>
						</f:facet>
						<rich:menuItem submitMode="ajax" value="Cadastrar SubElemento"
							action="#{subElementoController.cadastrar}" />
						<rich:menuItem submitMode="ajax" value="Listar SubElemento"
							action="#{subElementoController.listar}" />
					</rich:dropDownMenu>

					<rich:dropDownMenu style="font-size: 13px;"
						rendered="#{usuarioController.usuarioAutenticado.indAdministrador}">
						<f:facet name="label">
							<h:panelGroup>
								<h:outputText value="Usuário" />
							</h:panelGroup>
						</f:facet>
						<rich:menuItem submitMode="ajax" value="Cadastrar Usuário"
							action="#{usuarioController.cadastrar}" />
						<rich:menuItem submitMode="ajax" value="Listar Usuário"
							action="#{usuarioController.listar}" />
					</rich:dropDownMenu>

					<rich:dropDownMenu style="font-size: 13px;"
						rendered="#{usuarioController.usuarioAutenticado.indAdministrador}">
						<f:facet name="label">
							<h:panelGroup>
								<h:outputText value="Orçamento" />
							</h:panelGroup>
						</f:facet>
						<rich:menuItem submitMode="ajax" value="Cadastrar Orçamento"
							action="#{lotacaoController.cadastrar}" />
						<rich:menuItem submitMode="ajax" value="Listar Orçamentos"
							action="#{lotacaoController.listar}" />
					</rich:dropDownMenu>

					<rich:dropDownMenu style="font-size: 13px;">
						<f:facet name="label">
							<h:panelGroup>
								<h:outputText value="Planejamento" />
							</h:panelGroup>
						</f:facet>
						<rich:menuItem submitMode="ajax"
							value="Cadastrar Materiais e Serviços"
							action="#{materialController.cadastrar}"
							rendered="#{usuarioController.usuarioAutenticado.indCadastrador || usuarioController.usuarioAutenticado.indCadastradorSubLotacao}" />
						<rich:menuItem submitMode="ajax"
							value="Listar Materiais e Serviços"
							action="#{materialController.listar}"
							rendered="#{usuarioController.usuarioAutenticado.indCadastrador || usuarioController.usuarioAutenticado.indAdministrador || usuarioController.usuarioAutenticado.indCadastradorSubLotacao}" />
						<rich:menuItem submitMode="ajax"
							value="Priorizar Materiais e Serviços"
							rendered="#{usuarioController.usuarioAutenticado.indPriorizador || usuarioController.usuarioAutenticado.indAdministrador}"
							action="#{materialController.priorizar}" />
						<rich:menuItem submitMode="ajax" value="Aprovar Prioridades"
							rendered="#{usuarioController.usuarioAutenticado.indAprovador ||usuarioController.usuarioAutenticado.indAdministrador}"
							action="#{materialController.aprovarPrioridades}" />
						<rich:menuItem submitMode="ajax" value="Listar Aprovações"
							rendered="#{usuarioController.usuarioAutenticado.indAprovador || usuarioController.usuarioAutenticado.indAdministrador}"
							action="#{materialController.listarAprovacao}" />
					</rich:dropDownMenu>

					<rich:dropDownMenu style="font-size: 13px;">
						<f:facet name="label">
							<h:panelGroup>
								<h:outputText value="Replanejamento" />
							</h:panelGroup>
						</f:facet>
						<rich:menuItem submitMode="ajax" value="Carregar Tabela"
							action="#{replanejamentoController.carregarTabela}"
							rendered="#{usuarioController.usuarioAutenticado.indAdministrador}" />
						<rich:menuItem submitMode="ajax" value="Lançar Executado"
							action="#{replanejamentoController.cadastrarExcutado}"
							rendered="#{usuarioController.usuarioAutenticado.indAdministrador}" />
						<rich:menuItem submitMode="ajax" value="Replanejar"
							action="#{replanejamentoController.cadastrarReplanejamento}"
							rendered="#{usuarioController.usuarioAutenticado.indReplanejador || usuarioController.usuarioAutenticado.indAdministrador}" />
					</rich:dropDownMenu>


					<rich:dropDownMenu style="font-size: 13px;">
						<f:facet name="label">
							<h:panelGroup>
								<h:outputText value="Trocar Senha" />
							</h:panelGroup>
						</f:facet>
						<rich:menuItem submitMode="ajax" value="Trocar Senha"
							action="#{usuarioController.alterarSenha}" />
					</rich:dropDownMenu>

					<rich:dropDownMenu style="font-size: 13px;">
						<f:facet name="label">
							<h:panelGroup>
								<h:outputText value="Sugestão" />
							</h:panelGroup>
						</f:facet>
						<rich:menuItem submitMode="ajax" value="Cadastrar Sugestão"
							action="#{sugestaoController.cadastrar}" />
						<rich:menuItem submitMode="ajax" value="Listar Sugestão"
							action="#{sugestaoController.listar}"
							rendered="#{usuarioController.usuarioAutenticado.indAdministrador}" />
					</rich:dropDownMenu>


					<rich:dropDownMenu style="font-size: 13px;">
						<f:facet name="label">
							<h:panelGroup>
								<h:outputText value="Sair" />
							</h:panelGroup>
						</f:facet>
						<rich:menuItem submitMode="ajax" value="Sair"
							action="#{usuarioController.logout}" />
					</rich:dropDownMenu>
				</rich:toolBar>



				<rich:panel style="width: 100%; left: 100%;">
					<center>
						<a4j:status>
							<f:facet name="start">
								<h:graphicImage value="../images/ajax-loader.gif" />
							</f:facet>
						</a4j:status>
					</center>
					<rich:messages layout="list" errorLabelClass="errorLabel"
						style="top:auto;" infoLabelClass="infoLabel">
						<f:facet name="infoMarker">
							<h:graphicImage value="../images/passed.gif" />
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="../images/error.gif" />
						</f:facet>
					</rich:messages>
				</rich:panel>

			</center>

		</a4j:form>

	</f:subview>
</body>
</html>
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
<body>
	<f:view>
		<a4j:loadScript src="../js/script.js" />
		<jsp:directive.include file="menus.jsp" />
		<a4j:form id="form">
			<center>
				<rich:panel header="CADASTRAR USUÁRIO"
					style="width: 100%; left: 100%;">

					<h:panelGrid columns="2">

						<h:outputText value="Login: ">
						</h:outputText>
						<h:inputText required="true"
							requiredMessage="Campo Login é obrigatório!"
							value="#{usuarioController.usuario.login}" size="25"
							maxlength="30">
						</h:inputText>

						<h:outputText value="Senha: " />
						<h:inputSecret value="#{usuarioController.usuario.senha}"
							size="25" maxlength="12" required="true"
							disabled="#{usuarioController.usuario.login != null}"
							requiredMessage="Campo Senha obrigatório!"></h:inputSecret>

						<h:outputText value="Lotação: " />
						<h:selectOneMenu
							value="#{usuarioController.usuario.subLotacao.lotacao.codigo}"
							required="true" requiredMessage="Campo Lotação é obrigatório!">
							<f:selectItem itemLabel="SELECIONE" itemValue="" />
							<f:selectItems value="#{usuarioController.lotacoes}" />
							<a4j:support event="onchange"
								action="#{usuarioController.listarSubLotacoes}"
								ajaxSingle="true" reRender="sub"></a4j:support>
						</h:selectOneMenu>

						<h:outputText value="Sub Lotação: " />
						<h:selectOneMenu id="sub"
							value="#{usuarioController.usuario.subLotacao.codigo}"
							required="true"
							requiredMessage="Campo Sub Lotação é obrigatório!">
							<f:selectItem itemLabel="SELECIONE" itemValue="" />
							<f:selectItems value="#{usuarioController.subLotacoes}" />
						</h:selectOneMenu>

						<h:outputText value="Administrador:" />
						<h:selectBooleanCheckbox
							value="#{usuarioController.usuario.indAdministrador}">
						</h:selectBooleanCheckbox>

						<h:outputText value="Cadastrador:" />
						<h:selectBooleanCheckbox
							value="#{usuarioController.usuario.indCadastrador}">
						</h:selectBooleanCheckbox>

						<h:outputText value="Cadastrador Sub Lotacao:" />
						<h:selectBooleanCheckbox
							value="#{usuarioController.usuario.indCadastradorSubLotacao}">
						</h:selectBooleanCheckbox>

						<h:outputText value="Priorizador:" />
						<h:selectBooleanCheckbox
							value="#{usuarioController.usuario.indPriorizador}">
						</h:selectBooleanCheckbox>

						<h:outputText value="Aprovador:" />
						<h:selectBooleanCheckbox
							value="#{usuarioController.usuario.indAprovador}">
						</h:selectBooleanCheckbox>

						<h:outputText value="Replanejador:" />
						<h:selectBooleanCheckbox
							value="#{usuarioController.usuario.indReplanejador}">
						</h:selectBooleanCheckbox>

					</h:panelGrid>
					<a4j:commandButton value="Salvar"
						action="#{usuarioController.salvar}" reRender="form" />


				</rich:panel>
			</center>
		</a4j:form>
	</f:view>
</body>
</html>
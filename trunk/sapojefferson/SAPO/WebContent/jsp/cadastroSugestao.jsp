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
		<center><rich:panel header="CADASTRAR SUGESTÃO"
			style="width: 100%; left: 100%;">

			<h:panelGrid columns="2">

				<h:outputText value="Login: ">
				</h:outputText>
				<h:outputText value="#{sugestaoController.sugestao.usuario.login}" />


				<h:outputText value="Lotação: " />
				<h:outputText
					value="#{sugestaoController.sugestao.usuario.lotacao.descricao}"></h:outputText>

				<h:outputText value="Telefone:" />
				<h:inputText
					rendered="#{sugestaoController.sugestao.codigo == null}"
					value="#{sugestaoController.sugestao.telefone}" size="16"
					maxlength="13" required="true"
					requiredMessage="Campo Telefone é obrigatório!"
					onkeypress="mascara(this,telefone);"
					validatorMessage="Campo Telefone deve ter no mínimo 13 caracteres!">
					<f:validateLength minimum="13" />
				</h:inputText>
				<h:outputText
					rendered="#{sugestaoController.sugestao.codigo != null}"
					value="#{sugestaoController.sugestao.telefone}"></h:outputText>

				<h:outputText value="E-mail:" />
				<h:inputText
					rendered="#{sugestaoController.sugestao.codigo == null}"
					value="#{sugestaoController.sugestao.email}" size="40"
					maxlength="200" required="true"
					requiredMessage="Campo E-mail é obrigatório!">
				</h:inputText>
				<h:outputText
					rendered="#{sugestaoController.sugestao.codigo != null}"
					value="#{sugestaoController.sugestao.email}"></h:outputText>

				<h:outputText value="Sugestão: " />
				<h:inputTextarea
					rendered="#{sugestaoController.sugestao.codigo == null}"
					value="#{sugestaoController.sugestao.sugestao}" rows="5" cols="61"
					required="true" requiredMessage="Campo Sugestão é obrigatório!"
					validatorMessage="Sugestão deve ter no máximo 600 caracteres!">
					<f:validateLength maximum="600"></f:validateLength>
				</h:inputTextarea>
				<h:outputText
					rendered="#{sugestaoController.sugestao.codigo != null}"
					value="#{sugestaoController.sugestao.sugestao}"></h:outputText>

				<h:outputText value="Marcar como lida: "
					rendered="#{sugestaoController.sugestao.codigo != null}" />
				<h:selectBooleanCheckbox 
					value="#{sugestaoController.sugestao.lida}"
					rendered="#{sugestaoController.sugestao.codigo != null}"></h:selectBooleanCheckbox>
				<h:outputText value="Arquivar: "
					rendered="#{sugestaoController.sugestao.codigo != null}" />
				<h:selectBooleanCheckbox
					value="#{sugestaoController.sugestao.arquivada}"
					rendered="#{sugestaoController.sugestao.codigo != null}"></h:selectBooleanCheckbox>
			</h:panelGrid>
			<a4j:commandButton value="Salvar"
				action="#{sugestaoController.salvar}" reRender="form" />


		</rich:panel></center>
	</a4j:form>
</f:view>
</body>
</html>
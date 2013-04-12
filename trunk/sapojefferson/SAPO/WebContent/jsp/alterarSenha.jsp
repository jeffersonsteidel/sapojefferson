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
	<jsp:directive.include file="menus.jsp" />
	<a4j:loadScript src="../js/script.js" />
	<a4j:form id="form">
		<center><rich:panel header="TROCAR SENHA"
					style="width: 100%; left: 100%;">
			<h:panelGrid columns="2">
				<h:outputText value="Senha Atual: ">
				</h:outputText>
				<h:inputSecret required="true"
					requiredMessage="Campo Senha Atual é obrigatório!"
					value="#{usuarioController.usuario.senha}" size="12"
					maxlength="6">
				</h:inputSecret>
				<h:outputText value="Nova Senha: ">
				</h:outputText>
				<h:inputSecret required="true"
					requiredMessage="Campo Nova Senha é obrigatório!"
					value="#{usuarioController.novaSenha }" size="12"
					maxlength="6">
				</h:inputSecret>
				<h:outputText value="Confirmar Nova Senha: ">
				</h:outputText>
				<h:inputSecret required="true"
					requiredMessage="Campo Confirmar Nova Senha é obrigatório!"
					value="#{usuarioController.confirmarSenha}" size="12"
					maxlength="6">
				</h:inputSecret>
			</h:panelGrid>
			<a4j:commandButton value="Salvar"
				action="#{usuarioController.verificarSenha}" />
		</rich:panel></center>
	</a4j:form>
</f:view>
</body>
</html>
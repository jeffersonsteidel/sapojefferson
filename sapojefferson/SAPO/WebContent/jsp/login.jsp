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
<link rel="shortcut icon" href="../images/icone.png" />
<link rel="StyleSheet" type="text/css" href="../css/messages-style.css"
	media="screen" />
</head>
<body bgcolor=" #8ac444">
	<f:view>
		<center>
			<h:form id="form">
				<h:graphicImage value="../images/topo.png" width="100%"
					height="150px" />
				<rich:panel header="PROPLAN"
					style="width: 60%; height:30%;  position: absolute; left: 20%; top: 25%">
					<rich:messages layout="list" errorLabelClass="errorLabel"
						infoLabelClass="infoLabel">
						<f:facet name="infoMarker">
							<h:graphicImage value="../images/passed.gif" />
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="../images/error.gif" />
						</f:facet>
					</rich:messages>
					<h:panelGrid columns="2">
						<h:outputText value="Login: " style="font-size: 15px;"/>
						<h:inputText value="#{usuarioController.usuario.login}" size="25"
							maxlength="30" required="true"
							requiredMessage="Campo Login obrigatório!"></h:inputText>
						<h:outputText value="Senha: " style="font-size: 15px" />
						<h:inputSecret value="#{usuarioController.usuario.senha}"
							size="25" maxlength="6" required="true" 
							requiredMessage="Campo Senha obrigatório!"></h:inputSecret>
					</h:panelGrid>
					<a4j:commandButton value="Entrar" type="submit" reRender="form"
						action="#{usuarioController.login}" />
					<h:panelGrid columns="4">
						<h:graphicImage value="../images/firefox.png" />
						<h:outputText style="font-size: 15px"
							value="Este sistema é melhor visualizado utilizando o Mozilla Firefox, para baixá-lo e instalá-lo,">
						</h:outputText>
						<h:outputLink style="font-size: 15px"
							value="http://download.mozilla.org/?product=firefox-11.0&amp;os=win&amp;lang=pt-BR">Clique aqui.</h:outputLink>
					</h:panelGrid>

				</rich:panel>
			</h:form>
		</center>
	</f:view>
</body>
</html>
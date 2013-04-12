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
	<%
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("usuarioLogado") == null) {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("login.jsp");
		}
	%>
	<f:view>
		<center>
			<a4j:form id="form" styleClass="form">
				<h:graphicImage value="../images/topo.png" width="100%"
					height="150px" />

				<center>
					<h:panelGrid columns="1">

						<rich:panel style="width:100%; padding:0px;">
							<f:facet name="header">
								<h:panelGroup>
									<h:outputText
										value=" Última Mensagem: #{usuarioController.mensagem.titulo}"
										style="font-weight:bold" />
								</h:panelGroup>
							</f:facet>
							<h:panelGrid columns="2">
								<h:outputText value="Data:" style="font-weight:bold; font-size: 15px;" />
								<h:outputText value="#{usuarioController.mensagem.data}" style="font-size: 15px; font-weight:normal;">
									<f:convertDateTime pattern="dd/MM/yyyy" />
								</h:outputText>
								<h:outputText value="Descrição:" style="font-size: 15px; font-weight:bold"/>
								<h:outputText value="#{usuarioController.mensagem.descricao}" style="font-size: 15px; font-weight:normal;"/>
							</h:panelGrid>
						</rich:panel>

					</h:panelGrid>
					<h:panelGrid columns="1">
						<a4j:commandButton value="Entrar no Sistema" type="submit"
							reRender="form" action="#{usuarioController.carregarMenus}"  />
					</h:panelGrid>

				</center>
			</a4j:form>
		</center>
	</f:view>
</body>
</html>
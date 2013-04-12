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
				<rich:panel header="CADASTRAR ELEMENTO"
					style="width: 100%; left: 100%;">
					<h:panelGrid columns="2">

						<h:outputText value="Elemento: " />
						<h:inputText required="true"
							requiredMessage="Campo Elemento é obrigatório!"
							value="#{elementoController.elemento.descricao}" size="50"
							maxlength="50">
						</h:inputText>

						<h:outputText value="Categoria: " />
						<h:selectOneMenu
							value="#{elementoController.elemento.categoria.codigo}"
							required="true" requiredMessage="Campo Categoria é obrigatório!">
							<f:selectItem itemLabel="SELECIONE" itemValue="" />
							<f:selectItems value="#{elementoController.categorias}" />
						</h:selectOneMenu>

					</h:panelGrid>
					<a4j:commandButton value="Salvar"
						action="#{elementoController.salvar}" reRender="form" />

				</rich:panel>
			</center>
		</a4j:form>
	</f:view>
</body>
</html>
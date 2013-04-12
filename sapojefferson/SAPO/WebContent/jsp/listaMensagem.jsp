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
		<jsp:directive.include file="menus.jsp" />
		<center>
			<a4j:form id="form">
				<rich:panel header="LISTAR MENSAGENS"
					style="width: 100%; left: 100%;">

					<rich:dataTable id="listarMensagens"
						value="#{mensagemController.mensagemList}" var="list"
						columnClasses="center" rows="20" reRender="ds"
						rendered="#{not empty mensagemController.mensagemList}">

						<rich:column width="500px" sortBy="#{list.titulo}"
							filterBy="#{list.titulo}" filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Titulo" />
							</f:facet>
							<h:outputText value="#{list.titulo}" />
						</rich:column>

						<rich:column width="200px" sortBy="#{list.data}"
							filterBy="#{list.data}" filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Data" />
							</f:facet>
							<h:outputText value="#{list.data}" >
								<f:convertDateTime pattern="dd/MM/yyyy" />
							</h:outputText>
						</rich:column>
						
						<rich:column width="50px">
							<f:facet name="header">
								<h:outputText value="Visualizar" />
							</f:facet>
							<a4j:commandLink action="#{mensagemController.visualizar}"
								reRender="listarMensagens" ajaxSingle="true">
								<h:graphicImage value="../images/play.png" style="border:0"
									width="20" height="18" id="visualizar" />
								<f:setPropertyActionListener value="#{list}"
									target="#{mensagemController.mensagem}" />
							</a4j:commandLink>
							<rich:toolTip for="editar" value="Editar" />
						</rich:column>

						<rich:column width="50px" rendered="#{usuarioController.usuarioAutenticado.indAdministrador}">
							<f:facet name="header">
								<h:outputText value="Editar" />
							</f:facet>
							<a4j:commandLink action="#{mensagemController.editar}"
								reRender="listarMensagens" ajaxSingle="true">
								<h:graphicImage value="../images/edit.gif" style="border:0"
									width="20" height="18" id="editar" />
								<f:setPropertyActionListener value="#{list}"
									target="#{mensagemController.mensagem}" />
							</a4j:commandLink>
							<rich:toolTip for="editar" value="Editar" />
						</rich:column>

						<rich:column rendered="#{usuarioController.usuarioAutenticado.indAdministrador}">
							<f:facet name="header">
								<h:outputText value="Excluir" />
							</f:facet>
							<a4j:commandLink ajaxSingle="true" id="delete"
								oncomplete="#{rich:component('deletePanel')}.show()">
								<h:graphicImage id="excluir" value="../images/delete.gif"
									style="border:0" />
								<f:setPropertyActionListener value="#{list}"
									target="#{mensagemController.mensagem}" />
							</a4j:commandLink>
							<rich:toolTip for="excluir" value="Excluir" />
						</rich:column>
						<f:facet name="footer">
							<rich:datascroller id="ds"></rich:datascroller>
						</f:facet>
					</rich:dataTable>
				</rich:panel>
			</a4j:form>
			<rich:modalPanel id="deletePanel" autosized="true" width="200">
				<f:facet name="header">
					<h:outputText value="Deseja realmente deletar este item?"
						style="padding-right:15px;" />
				</f:facet>
				<h:form>
					<table width="100%">
						<tbody>
							<tr>
								<td align="center" width="50%"><a4j:commandButton
										value="Sim" ajaxSingle="true"
										action="#{mensagemController.excluir}"
										oncomplete="#{rich:component('deletePanel')}.hide();"
										reRender="listarMensagens, form" /></td>
								<td align="center" width="50%"><a4j:commandButton
										value="Não"
										onclick="#{rich:component('deletePanel')}.hide();return false;" />
								</td>
							</tr>
						</tbody>
					</table>
				</h:form>
			</rich:modalPanel>
		</center>
	</f:view>
</body>
</html>
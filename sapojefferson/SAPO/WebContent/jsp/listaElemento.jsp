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
				<rich:panel header="LISTAR ELEMENTOS"
					style="width: 100%; left: 100%;">

					<rich:dataTable id="listarElementos"
						value="#{elementoController.elementoList}" var="list"
						columnClasses="center" rows="20" reRender="ds"
						rendered="#{not empty elementoController.elementoList}">

						<rich:column width="500px" sortBy="#{list.descricao}"
							filterBy="#{list.descricao}" filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Elemento" />
							</f:facet>
							<h:outputText value="#{list.descricao}" />
						</rich:column>

						<rich:column width="200px" sortBy="#{list.categoria.descricao}"
							filterBy="#{list.categoria.descricao}" filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Categoria" />
							</f:facet>
							<h:outputText value="#{list.categoria.descricao}" />
						</rich:column>


						<rich:column width="50px">
							<f:facet name="header">
								<h:outputText value="Editar" />
							</f:facet>
							<a4j:commandLink action="#{elementoController.editar}"
								reRender="listarCategoria" ajaxSingle="true">
								<h:graphicImage value="../images/edit.gif" style="border:0"
									width="20" height="18" id="editar" />
								<f:setPropertyActionListener value="#{list}"
									target="#{elementoController.elemento}" />
							</a4j:commandLink>
							<rich:toolTip for="editar" value="Editar" />
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Excluir" />
							</f:facet>
							<a4j:commandLink ajaxSingle="true" id="delete"
								oncomplete="#{rich:component('deletePanel')}.show()">
								<h:graphicImage id="excluir" value="../images/delete.gif"
									style="border:0" />
								<f:setPropertyActionListener value="#{list}"
									target="#{elementoController.elemento}" />
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
										action="#{elementoController.excluir}"
										oncomplete="#{rich:component('deletePanel')}.hide();"
										reRender="listarUnidade, form" /></td>
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
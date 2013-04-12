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
				<rich:panel header="LISTAR APROVAÇÕES"
					style="width: 100%; left: 100%;">
					<rich:dataTable id="listarAprovacao"
						value="#{materialController.aprovacaoList}" var="list"
						columnClasses="center" rows="20" reRender="ds"
						rendered="#{not empty materialController.aprovacaoList}">

						<rich:column width="200px" sortBy="#{list.subLotacao.descricao}">
							<f:facet name="header">
								<h:outputText value="Sub Lotação" />
							</f:facet>
							<h:outputText value="#{list.subLotacao.descricao}" />
						</rich:column>

						<rich:column width="200px" sortBy="#{list.categoria.descricao}">
							<f:facet name="header">
								<h:outputText value="Categoria" />
							</f:facet>
							<h:outputText value="#{list.categoria.descricao}" />
						</rich:column>

						<rich:column width="200px" sortBy="#{list.orcamento}">
							<f:facet name="header">
								<h:outputText value="Orçamento" />
							</f:facet>
							<h:outputText value="#{list.orcamento}">
								<f:convertNumber type="currency" />
							</h:outputText>
						</rich:column>

						<rich:column width="200px" sortBy="#{list.total}">
							<f:facet name="header">
								<h:outputText value="Total" />
							</f:facet>
							<h:outputText value="#{list.total}">
								<f:convertNumber type="currency" />
							</h:outputText>
						</rich:column>

						<rich:column width="200px" sortBy="#{list.acumulado}">
							<f:facet name="header">
								<h:outputText value="Acumulado" />
							</f:facet>
							<h:outputText value="#{list.acumulado}">
								<f:convertNumber type="currency" />
							</h:outputText>
						</rich:column>

						<rich:column width="200px" sortBy="#{list.diferenca}">
							<f:facet name="header">
								<h:outputText value="Diferença" />
							</f:facet>
							<h:outputText value="#{list.diferenca}">
								<f:convertNumber type="currency" />
							</h:outputText>
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
									target="#{materialController.aprovacao}" />
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
										action="#{materialController.excluirAprovacao}"
										oncomplete="#{rich:component('deletePanel')}.hide();"
										reRender="listarAprovacao, form" /></td>
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
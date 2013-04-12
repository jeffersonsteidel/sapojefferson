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
				<rich:panel header="LISTAR USUÁRIOS"
					style="width: 100%; left: 100%;">

					<rich:dataTable id="listarUsuarios"
						value="#{usuarioController.usuarioList}" var="list"
						columnClasses="center" rows="20" reRender="ds"
						rendered="#{not empty usuarioController.usuarioList}">

						<rich:column width="200px" sortBy="#{list.login}"
							filterBy="#{list.login}" filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Login" />
							</f:facet>
							<h:outputText value="#{list.login}" />
						</rich:column>


						<rich:column width="200px"
							sortBy="#{list.subLotacao.lotacao.descricao}"
							filterBy="#{list.subLotacao.lotacao.descricao}"
							filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Lotação" />
							</f:facet>
							<h:outputText value="#{list.subLotacao.lotacao.descricao}" />
						</rich:column>

						<rich:column width="200px" sortBy="#{list.subLotacao.descricao}"
							filterBy="#{list.subLotacao.descricao}" filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Sub Lotação" />
							</f:facet>
							<h:outputText value="#{list.subLotacao.descricao}" />
						</rich:column>

						<rich:column width="200px" sortBy="#{list.indAdministrador}"
							filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Admin" />
							</f:facet>
							<h:outputText value="SIM" rendered="#{list.indAdministrador}" />
							<h:outputText value="NÃO" rendered="#{! list.indAdministrador}" />
						</rich:column>

						<rich:column width="200px" sortBy="#{list.indCadastrador}"
							filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Cadastrador" />
							</f:facet>
							<h:outputText value="SIM" rendered="#{list.indCadastrador}" />
							<h:outputText value="NÃO" rendered="#{! list.indCadastrador}" />
						</rich:column>

						<rich:column width="200px"
							sortBy="#{list.indCadastradorSubLotacao}" filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Cadastrador Sub Lotação" />
							</f:facet>
							<h:outputText value="SIM"
								rendered="#{list.indCadastradorSubLotacao}" />
							<h:outputText value="NÃO"
								rendered="#{! list.indCadastradorSubLotacao}" />
						</rich:column>


						<rich:column width="200px" sortBy="#{list.indPriorizador}"
							filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Priorizador" />
							</f:facet>
							<h:outputText value="SIM" rendered="#{list.indPriorizador}" />
							<h:outputText value="NÃO" rendered="#{! list.indPriorizador}" />
						</rich:column>

						<rich:column width="200px" sortBy="#{list.indAprovador}"
							filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Aprovador" />
							</f:facet>
							<h:outputText value="SIM" rendered="#{list.indAprovador}" />
							<h:outputText value="NÃO" rendered="#{! list.indAprovador}" />
						</rich:column>

						<rich:column width="200px" sortBy="#{list.indReplanejador}"
							filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Replanejador" />
							</f:facet>
							<h:outputText value="SIM" rendered="#{list.indReplanejador}" />
							<h:outputText value="NÃO" rendered="#{! list.indReplanejador}" />
						</rich:column>


						<rich:column width="50px">
							<f:facet name="header">
								<h:outputText value="Editar" />
							</f:facet>
							<a4j:commandLink action="#{usuarioController.editar}"
								reRender="listarUsuarios" ajaxSingle="true">
								<h:graphicImage value="../images/edit.gif" style="border:0"
									width="20" height="18" id="editar" />
								<f:setPropertyActionListener value="#{list}"
									target="#{usuarioController.usuario}" />
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
									target="#{usuarioController.usuario}" />
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
										action="#{usuarioController.excluir}"
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
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
	<center><a4j:form id="form">
		<rich:panel header="LISTAR SUGESTÕES" style="width: 100%; left: 100%;">

			<h:outputText value="Filtro: " />
			<h:selectOneMenu value="#{sugestaoController.indicador}"
				required="true" requiredMessage="Campo Filtro é obrigatório!">
				<f:selectItem itemLabel="TODAS" itemValue="0" />
				<f:selectItem itemLabel="LIDAS" itemValue="1" />
				<f:selectItem itemLabel="NÃO LIDAS" itemValue="2" />
				<f:selectItem itemLabel="ARQUIVADAS" itemValue="3" />
			</h:selectOneMenu>

			<a4j:commandButton value="Pesquisar"  
						action="#{sugestaoController.pesquisar}" reRender="form" />
			

			<rich:dataTable id="listarSugestao"
				value="#{sugestaoController.sugestaoList}" var="list"
				columnClasses="center" rows="20" reRender="ds"
				rendered="#{not empty sugestaoController.sugestaoList}">
				
				<rich:column width="10%" sortBy="#{list.data}">
					<f:facet name="header">
						<h:outputText value="Data" />
					</f:facet>
					<h:outputText value="#{list.data}" >
					<f:convertDateTime pattern="dd/MM/yyyy - HH:mm:ss"/>
					</h:outputText>
				</rich:column>

				<rich:column width="10%" sortBy="#{list.usuario.login}">
					<f:facet name="header">
						<h:outputText value="Usuário" />
					</f:facet>
					<h:outputText value="#{list.usuario.login}" />
				</rich:column>

				<rich:column width="10%" sortBy="#{list.usuario.lotacao.descricao}">
					<f:facet name="header">
						<h:outputText value="Lotação" />
					</f:facet>
					<h:outputText value="#{list.usuario.lotacao.descricao}" />
				</rich:column>

				<rich:column width="10%" sortBy="#{list.email}">
					<f:facet name="header">
						<h:outputText value="E-mail" />
					</f:facet>
					<h:outputText value="#{list.email}" />
				</rich:column>

				<rich:column width="10%" sortBy="#{list.telefone}">
					<f:facet name="header">
						<h:outputText value="Telefone" />
					</f:facet>
					<h:outputText value="#{list.telefone}" />
				</rich:column>

				<rich:column width="1%">
					<f:facet name="header">
						<h:outputText value="Editar" />
					</f:facet>
					<a4j:commandLink action="#{sugestaoController.editar}"
						reRender="listarSugestao" ajaxSingle="true">
						<h:graphicImage value="../images/edit.gif" style="border:0"
							width="20" height="18" id="editar" />
						<f:setPropertyActionListener value="#{list}"
							target="#{sugestaoController.sugestao}" />
					</a4j:commandLink>
					<rich:toolTip for="editar" value="Editar" />
				</rich:column>
				<rich:column width="1%">
					<f:facet name="header">
						<h:outputText value="Excluir" />
					</f:facet>
					<a4j:commandLink ajaxSingle="true" id="delete"
						oncomplete="#{rich:component('deletePanel')}.show()">
						<h:graphicImage id="excluir" value="../images/delete.gif"
							style="border:0" />
						<f:setPropertyActionListener value="#{list}"
							target="#{sugestaoController.sugestao}" />
					</a4j:commandLink>
					<rich:toolTip for="excluir" value="Excluir" />
				</rich:column>
				<f:facet name="footer">
					<rich:datascroller id="ds"></rich:datascroller>
				</f:facet>
			</rich:dataTable>
		</rich:panel>
	</a4j:form> <rich:modalPanel id="deletePanel" autosized="true" width="200">
		<f:facet name="header">
			<h:outputText value="Deseja realmente deletar este item?"
				style="padding-right:15px;" />
		</f:facet>
		<h:form>
			<table width="100%">
				<tbody>
					<tr>
						<td align="center" width="50%"><a4j:commandButton value="Sim"
							ajaxSingle="true" action="#{sugestaoController.excluir}"
							oncomplete="#{rich:component('deletePanel')}.hide();"
							reRender="listarSugestao, form" /></td>
						<td align="center" width="50%"><a4j:commandButton value="Não"
							onclick="#{rich:component('deletePanel')}.hide();return false;" />
						</td>
					</tr>
				</tbody>
			</table>
		</h:form>
	</rich:modalPanel></center>
</f:view>
</body>
</html>
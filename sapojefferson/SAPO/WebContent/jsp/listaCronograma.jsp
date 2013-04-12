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
				<rich:panel header="LISTAR CRONOGRAMA"
					style="width: 100%; left: 100%;">

					<rich:dataTable id="listarCronograma"
						value="#{cronogramaController.cronogramaList}" var="list"
						columnClasses="center" rows="20" reRender="ds"
						rendered="#{not empty cronogramaController.cronogramaList}">

						<rich:column width="200px" sortBy="#{list.dataIniCadastrar}">
							<f:facet name="header">
								<h:outputText value="Início Cadastro" />
							</f:facet>
							<h:outputText value="#{list.dataIniCadastrar}">
								<f:convertDateTime pattern="dd/MM/yyyy" />
							</h:outputText>
						</rich:column>
						<rich:column width="200px" sortBy="#{list.dataFimCadastrar}">
							<f:facet name="header">
								<h:outputText value="Fim Cadastro" />
							</f:facet>
							<h:outputText value="#{list.dataFimCadastrar}">
								<f:convertDateTime pattern="dd/MM/yyyy" />
							</h:outputText>
						</rich:column>


						<rich:column width="200px" sortBy="#{list.dataIniPriorizar}">
							<f:facet name="header">
								<h:outputText value="Início Priorizar" />
							</f:facet>
							<h:outputText value="#{list.dataIniPriorizar}">
								<f:convertDateTime pattern="dd/MM/yyyy" />
							</h:outputText>
						</rich:column>
						<rich:column width="200px" sortBy="#{list.dataFimPriorizar}">
							<f:facet name="header">
								<h:outputText value="Fim Priorizar" />
							</f:facet>
							<h:outputText value="#{list.dataFimPriorizar}">
								<f:convertDateTime pattern="dd/MM/yyyy" />
							</h:outputText>
						</rich:column>

						<rich:column width="200px" sortBy="#{list.dataIniAprovar}">
							<f:facet name="header">
								<h:outputText value="Início Aprovar" />
							</f:facet>
							<h:outputText value="#{list.dataIniAprovar}">
								<f:convertDateTime pattern="dd/MM/yyyy" />
							</h:outputText>
						</rich:column>
						<rich:column width="200px" sortBy="#{list.dataFimAprovar}">
							<f:facet name="header">
								<h:outputText value="Fim Aprovar" />
							</f:facet>
							<h:outputText value="#{list.dataFimAprovar}">
								<f:convertDateTime pattern="dd/MM/yyyy" />
							</h:outputText>
						</rich:column>

						<rich:column width="200px" sortBy="#{list.dataIniReplanejamento}">
							<f:facet name="header">
								<h:outputText value="Início Replanejamento" />
							</f:facet>
							<h:outputText value="#{list.dataIniReplanejamento}">
								<f:convertDateTime pattern="dd/MM/yyyy" />
							</h:outputText>
						</rich:column>
						<rich:column width="200px" sortBy="#{list.dataFimReplanejamento}">
							<f:facet name="header">
								<h:outputText value="Fim Replanejamento" />
							</f:facet>
							<h:outputText value="#{list.dataFimReplanejamento}">
								<f:convertDateTime pattern="dd/MM/yyyy" />
							</h:outputText>
						</rich:column>

						<rich:column width="50px">
							<f:facet name="header">
								<h:outputText value="Editar" />
							</f:facet>
							<a4j:commandLink action="#{cronogramaController.editar}"
								reRender="listarCronograma" ajaxSingle="true">
								<h:graphicImage value="../images/edit.gif" style="border:0"
									width="20" height="18" id="editar" />
								<f:setPropertyActionListener value="#{list}"
									target="#{cronogramaController.cronograma}" />
							</a4j:commandLink>
							<rich:toolTip for="editar" value="Editar" />
						</rich:column>
						<f:facet name="footer">
							<rich:datascroller id="ds"></rich:datascroller>
						</f:facet>
					</rich:dataTable>
				</rich:panel>
			</a4j:form>
		</center>
	</f:view>
</body>
</html>


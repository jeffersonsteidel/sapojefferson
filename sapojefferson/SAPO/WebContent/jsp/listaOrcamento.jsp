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
				<rich:panel header="LISTAR ORÇAMENTOS"
					style="width: 100%; left: 100%;">

					<rich:dataTable id="listarOrcamentos"
						value="#{lotacaoController.lotacaoList}" var="list"
						columnClasses="center" rows="20" reRender="ds"
						rendered="#{not empty lotacaoController.lotacaoList}">

						<rich:column width="200px" sortBy="#{list.descricao}"
							filterBy="#{list.descricao}" filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Lotação" />
							</f:facet>
							<h:outputText value="#{list.descricao}" />
						</rich:column>

						<rich:column width="200px" sortBy="#{list.orcamentoCapital}"
							filterBy="#{list.orcamentoCapital}" filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Orçamento Capital" />
							</f:facet>
							<h:outputText value="#{list.orcamentoCapital}">
								<f:convertNumber type="currency" />
							</h:outputText>
						</rich:column>

						<rich:column width="200px" sortBy="#{list.orcamentoCusteio}"
							filterBy="#{list.orcamentoCusteio}" filterEvent="onkeyup">
							<f:facet name="header">
								<h:outputText value="Orçamento Custeio" />
							</f:facet>
							<h:outputText value="#{list.orcamentoCusteio}">
								<f:convertNumber type="currency" />
							</h:outputText>
						</rich:column>


						<rich:column width="50px">
							<f:facet name="header">
								<h:outputText value="Editar" />
							</f:facet>
							<a4j:commandLink action="#{lotacaoController.editar}"
								reRender="listarOrcamentos" ajaxSingle="true">
								<h:graphicImage value="../images/edit.gif" style="border:0"
									width="20" height="18" id="editar" />
								<f:setPropertyActionListener value="#{list}"
									target="#{lotacaoController.lotacao}" />
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
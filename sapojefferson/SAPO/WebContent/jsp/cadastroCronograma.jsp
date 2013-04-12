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
				<rich:panel header="CADASTRAR CRONOGRAMA"
					style="width: 100%; left: 100%;">

					<h:panelGrid columns="3">

						<h:outputText value="Cadastrar Materiais/Serviços: " />
						<rich:calendar
							value="#{cronogramaController.cronograma.dataIniCadastrar}"
							locale="" popup="true" datePattern="dd/MM/yyyy"
							showApplyButton="#" cellWidth="12px" cellHeight="12px"
							style="width:80px" required="true" inputSize="12"
							requiredMessage="Campo Data Inical do Cadastro é obrigatório!" />
						<rich:calendar
							value="#{cronogramaController.cronograma.dataFimCadastrar}"
							locale="" popup="true" datePattern="dd/MM/yyyy"
							showApplyButton="#" cellWidth="12px" cellHeight="12px"
							style="width:80px" required="true" inputSize="12"
							requiredMessage="Campo Data Final do Cadastro é obrigatório!" />

						<h:outputText value="Priorizar Materiais/Serviços: " />
						<rich:calendar
							value="#{cronogramaController.cronograma.dataIniPriorizar}"
							locale="" popup="true" datePattern="dd/MM/yyyy"
							showApplyButton="#" cellWidth="12px" cellHeight="12px"
							style="width:80px" required="true" inputSize="12"
							requiredMessage="Campo Data Inical do Priorizar é obrigatório!" />
						<rich:calendar
							value="#{cronogramaController.cronograma.dataFimPriorizar}"
							locale="" popup="true" datePattern="dd/MM/yyyy"
							showApplyButton="#" cellWidth="12px" cellHeight="12px"
							style="width:80px" required="true" inputSize="12"
							requiredMessage="Campo Data Final do Priorizar é obrigatório!" />

						<h:outputText value="Aprovar Materiais/Serviços: " />
						<rich:calendar
							value="#{cronogramaController.cronograma.dataIniAprovar}"
							locale="" popup="true" datePattern="dd/MM/yyyy"
							showApplyButton="#" cellWidth="12px" cellHeight="12px"
							style="width:80px" required="true" inputSize="12"
							requiredMessage="Campo Data Inical do Aprovar é obrigatório!" />
						<rich:calendar
							value="#{cronogramaController.cronograma.dataFimAprovar}"
							locale="" popup="true" datePattern="dd/MM/yyyy"
							showApplyButton="#" cellWidth="12px" cellHeight="12px"
							style="width:80px" required="true" inputSize="12"
							requiredMessage="Campo Data Final do Aprovar é obrigatório!" />

						<h:outputText value="Replanejamento: " />
						<rich:calendar
							value="#{cronogramaController.cronograma.dataIniReplanejamento}"
							locale="" popup="true" datePattern="dd/MM/yyyy"
							showApplyButton="#" cellWidth="12px" cellHeight="12px"
							style="width:80px" required="true" inputSize="12"
							requiredMessage="Campo Data Inical do Replanejamento é obrigatório!" />
						<rich:calendar
							value="#{cronogramaController.cronograma.dataFimReplanejamento}"
							locale="" popup="true" datePattern="dd/MM/yyyy"
							showApplyButton="#" cellWidth="12px" cellHeight="12px"
							style="width:80px" required="true" inputSize="12"
							requiredMessage="Campo Data Final do Replanejamento é obrigatório!" />
					</h:panelGrid>

					<h:panelGrid columns="1">
						<a4j:commandButton value="Salvar"
							action="#{cronogramaController.salvar}" reRender="form" />
					</h:panelGrid>

				</rich:panel>
			</center>
		</a4j:form>
	</f:view>
</body>
</html>
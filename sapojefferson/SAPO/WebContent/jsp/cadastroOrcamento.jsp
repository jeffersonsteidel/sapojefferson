<%@page import="javax.faces.context.FacesContext"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Instituto Federal do Paran�</title>
<link rel="StyleSheet" type="text/css" href="../css/messages-style.css"
	media="screen" />
</head>
<body>
	<f:view>
		<a4j:loadScript src="../js/script.js" />
		<jsp:directive.include file="menus.jsp" />
		<a4j:form id="form">
			<center>
				<rich:panel header="CADASTRAR OR�AMENTO"
					style="width: 100%; left: 100%;">

					<h:panelGrid columns="10">
						<h:outputText value="Lota��o: " />
						<h:selectOneMenu value="#{lotacaoController.lotacao.codigo}"
							required="true" requiredMessage="Campo Lota��o � obrigat�rio!">
							<f:selectItem itemLabel="SELECIONE" itemValue="" />
							<f:selectItems value="#{lotacaoController.lotacoes}" />
							<a4j:support event="onchange"
								action="#{lotacaoController.buscarLotacao}" ajaxSingle="true"
								reRender="form"></a4j:support>
						</h:selectOneMenu>
					</h:panelGrid>

					<h:panelGrid columns="2" id="painel"
						rendered="#{lotacaoController.lotacao.descricao != null}">

						<h:outputText value="Or�amento Custeio: " />
						<h:inputText value="#{lotacaoController.lotacao.orcamentoCusteioString}"
							size="10" maxlength="13" required="true"
							converterMessage="Campo Or�amento Custeio inv�lido"
							requiredMessage="Campo Or�amento Custeio obrigat�rio!"
							onkeyup="mascara(this,moeda2);">
						</h:inputText>

						<h:outputText value="Or�amento Capital: " />
						<h:inputText value="#{lotacaoController.lotacao.orcamentoCapitalString}"
							size="10" maxlength="13" required="true"
							converterMessage="Campo Or�amento Capital inv�lido"
							requiredMessage="Campo Or�amento Capital obrigat�rio!"
							onkeyup="mascara(this,moeda2);">
						</h:inputText>

					</h:panelGrid>
					<a4j:commandButton value="Salvar" id="botao"
						action="#{lotacaoController.salvar}" reRender="form"
						rendered="#{lotacaoController.lotacao.descricao != null}" />


				</rich:panel>
			</center>
		</a4j:form>
	</f:view>
</body>
</html>
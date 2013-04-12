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
		<a4j:form id="form">
			<center>
				<rich:panel header="LANÇAR REPLANEJAMENTO"
					style="width: 100%; left: 100%;">

					<h:outputText value="Lotação: " />
					<h:selectOneMenu disabled="#{!usuarioController.usuarioAutenticado.indAdministrador}"
						value="#{replanejamentoController.replanejamento.material.subLotacao.lotacao.codigo}"
						requiredMessage="Campo Lotação é obrigatório!">
						<f:selectItem itemLabel="SELECIONE" itemValue="" />
						<f:selectItems value="#{replanejamentoController.lotacoes}" />
						<a4j:support event="onchange"
							action="#{replanejamentoController.pesquisar}" ajaxSingle="true"
							reRender="form"></a4j:support>
					</h:selectOneMenu>


					<h:outputText value="Categoria: " />
					<h:selectOneMenu id="categoria"
						value="#{replanejamentoController.replanejamento.material.subElemento.elemento.categoria.codigo}"
						required="true" requiredMessage="Campo Categoria é obrigatório!">
						<f:selectItem itemLabel="SELECIONE" itemValue="" />
						<f:selectItems value="#{replanejamentoController.categorias}" />
						<a4j:support event="onchange"
							action="#{replanejamentoController.pesquisar}" ajaxSingle="true"
							reRender="form"></a4j:support>
					</h:selectOneMenu>

					<rich:dataTable id="listarReplanejamento"
						value="#{replanejamentoController.replanejamentoList}" var="list"
						columnClasses="center"
						rendered="#{not empty replanejamentoController.replanejamentoList}">

						<rich:column width="200px"
							sortBy="#{list.material.subElemento.elemento.descricao}">
							<f:facet name="header">
								<h:outputText value="Elemento" />
							</f:facet>
							<h:outputText
								value="#{list.material.subElemento.elemento.descricao}" />
						</rich:column>

						<rich:column width="200px"
							sortBy="#{list.material.subElemento.descricao}">
							<f:facet name="header">
								<h:outputText value="SubElemento" />
							</f:facet>
							<h:outputText value="#{list.material.subElemento.descricao}" />
						</rich:column>


						<rich:column width="200px" sortBy="#{list.material.descricao}">
							<f:facet name="header">
								<h:outputText value="Material ou Serviço" />
							</f:facet>
							<h:outputText value="#{list.material.descricao}" />
						</rich:column>

						<rich:column width="200px" sortBy="#{list.material.tipo}">
							<f:facet name="header">
								<h:outputText value="Tipo" />
							</f:facet>
							<h:outputText value="#{list.material.tipo}" />
						</rich:column>

						<rich:column width="200px"
							sortBy="#{list.material.unidade.descricao}">
							<f:facet name="header">
								<h:outputText value="Unidade" />
							</f:facet>
							<h:outputText value="#{list.material.unidade.descricao}" />
						</rich:column>


						<rich:column width="200px" sortBy="#{list.material.vlrTotal}">
							<f:facet name="header">
								<h:outputText value="Vlr. Total" />
							</f:facet>
							<h:outputText value="#{list.material.vlrTotal}">
								<f:convertNumber type="currency" />
							</h:outputText>
						</rich:column>

						<rich:column width="200px" sortBy="#{list.vlrExecutadoString}">
							<f:facet name="header">
								<h:outputText value="Vlr. Exceutado" />
							</f:facet>
							<h:outputText value="#{list.vlrExecutadoString}">
								<f:convertNumber type="currency" />
							</h:outputText>
						</rich:column>

						<rich:column width="200px" sortBy="#{list.vlrReplanejado}">
							<f:facet name="header">
								<h:outputText value="Vlr. Disponível" />
							</f:facet>
							<h:outputText value="#{list.vlrReplanejado}">
								<f:convertNumber type="currency" />
							</h:outputText>
						</rich:column>

						<rich:column width="20px" sortBy="#{list.vlrCanceladoString}">
							<f:facet name="header">
								<h:outputText value="Cancelado" />
							</f:facet>
							<h:inputText onkeypress="mascara(this,moeda2);"
								value="#{list.vlrCanceladoString}" size="10" maxlength="11">
								<a4j:support event="onblur"
									action="#{replanejamentoController.calcularNovoSaldo}"
									ajaxSingle="true" reRender="novoSaldo, totalNovoSaldo"></a4j:support>
							</h:inputText>

						</rich:column>

						<rich:column width="20px" sortBy="#{list.vlrSuplamentarString}">
							<f:facet name="header">
								<h:outputText value="Suplementar" />
							</f:facet>
							<h:inputText onkeypress="mascara(this,moeda2);"
								value="#{list.vlrSuplamentarString}" size="10" maxlength="11">
								<a4j:support event="onblur"
									action="#{replanejamentoController.calcularNovoSaldo}"
									ajaxSingle="true" reRender="novoSaldo, totalNovoSaldo"></a4j:support>
							</h:inputText>
						</rich:column>

						<rich:column width="200px" sortBy="#{list.vlrNovoSaldo}">
							<f:facet name="header">
								<h:outputText value="Novo Saldo" />
							</f:facet>
							<h:outputText value="#{list.vlrNovoSaldo}" id="novoSaldo">
								<f:convertNumber type="currency" />
							</h:outputText>
						</rich:column>
					</rich:dataTable>
					
					<h:panelGrid columns="2">
						<h:outputText value="Total de Material"
							rendered="#{not empty replanejamentoController.replanejamentoList}" />
						<h:outputText value="#{replanejamentoController.totalMaterial}"
							rendered="#{not empty replanejamentoController.replanejamentoList}">
							<f:convertNumber type="currency" />
						</h:outputText>
						<h:outputText value="Total Novo Saldo" 
							rendered="#{not empty replanejamentoController.replanejamentoList}" />
						<h:outputText value="#{replanejamentoController.totalNovoSaldo}" id="totalNovoSaldo"
							rendered="#{not empty replanejamentoController.replanejamentoList}">
							<f:convertNumber type="currency" />
						</h:outputText>
					</h:panelGrid>

					<a4j:commandButton id="salvar" value="Salvar"
						rendered="#{not empty replanejamentoController.replanejamentoList}"
						action="#{replanejamentoController.salvarReplanejamento}" reRender="form" />

				</rich:panel>
			</center>
		</a4j:form>
	</f:view>
</body>
</html>
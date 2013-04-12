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
				<rich:panel header="PRIORIZAR MATERIAL / SERVIÇO"
					style="width: 100%; left: 100%;">

					<h:outputText value="Lotação: " />
					<h:selectOneMenu
						disabled="#{!(usuarioController.usuarioAutenticado.subLotacao.lotacao.codigo eq materialController.material.subLotacao.lotacao.codigo) ||	!(usuarioController.usuarioAutenticado.indAdministrador)}"
						value="#{materialController.material.subLotacao.lotacao.codigo}"
						required="#{!usuarioController.usuarioAutenticado.indAdministrador}"
						requiredMessage="Campo Lotação é obrigatório!">
						<f:selectItem itemLabel="SELECIONE" itemValue="" />
						<f:selectItems value="#{materialController.lotacoes}" />
						<a4j:support event="onchange"
							action="#{materialController.pesquisar}" ajaxSingle="true"
							reRender="form"></a4j:support>
					</h:selectOneMenu>


					<h:outputText value="Categoria: " />
					<h:selectOneMenu id="categoria"
						value="#{materialController.material.subElemento.elemento.categoria.codigo}"
						required="true" requiredMessage="Campo Categoria é obrigatório!">
						<f:selectItem itemLabel="SELECIONE" itemValue="" />
						<f:selectItems value="#{materialController.categorias}" />
						<a4j:support event="onchange"
							action="#{materialController.pesquisar}" ajaxSingle="true"
							reRender="form"></a4j:support>
					</h:selectOneMenu>

					<rich:dataTable id="listarMateriais"
						value="#{materialController.materialList}" var="list"
						columnClasses="center"
						rendered="#{not empty materialController.materialList}">

						<rich:column width="200px"
							sortBy="#{list.subLotacao.descricao}">
							<f:facet name="header">
								<h:outputText value="Sub Lotação" />
							</f:facet>
							<h:outputText value="#{list.subLotacao.descricao}" />
						</rich:column>
						
						<rich:column width="200px" sortBy="#{list.tipo}">
							<f:facet name="header">
								<h:outputText value="Tipo" />
							</f:facet>
							<h:outputText value="#{list.tipo}" />
							<h:inputHidden value="#{list.subLotacao.lotacao.codigo}" />
						</rich:column>

						<rich:column width="200px"
							sortBy="#{list.subElemento.elemento.descricao}">
							<f:facet name="header">
								<h:outputText value="Elemento" />
							</f:facet>
							<h:outputText value="#{list.subElemento.elemento.descricao}" />
						</rich:column>

						<rich:column width="200px" sortBy="#{list.subElemento.descricao}">
							<f:facet name="header">
								<h:outputText value="SubElemento" />
							</f:facet>
							<h:outputText value="#{list.subElemento.descricao}" />
						</rich:column>


						<rich:column width="200px" sortBy="#{list.descricao}">
							<f:facet name="header">
								<h:outputText value="Material ou Serviço" />
							</f:facet>
							<h:outputText value="#{list.descricao}" />
						</rich:column>

						<rich:column width="20px" sortBy="#{list.quantidade}">
							<f:facet name="header">
								<h:outputText value="Qtd" />
							</f:facet>
							<h:outputText value="#{list.quantidade}" />
						</rich:column>

						<rich:column width="200px" sortBy="#{list.unidade.descricao}">
							<f:facet name="header">
								<h:outputText value="Unidade" />
							</f:facet>
							<h:outputText value="#{list.unidade.descricao}" />
						</rich:column>

						<rich:column width="200px" sortBy="#{list.vlrUnitario}">
							<f:facet name="header">
								<h:outputText value="Vlr. Unit." />
							</f:facet>
							<h:outputText value="#{list.vlrUnitario}">
								<f:convertNumber type="currency" />
							</h:outputText>
						</rich:column>

						<rich:column width="200px" sortBy="#{list.vlrTotal}">
							<f:facet name="header">
								<h:outputText value="Vlr. Total" />
							</f:facet>
							<h:outputText value="#{list.vlrTotal}">
								<f:convertNumber type="currency" />
							</h:outputText>
						</rich:column>


						<rich:column width="20px" sortBy="#{list.prioridade}">
							<f:facet name="header">
								<h:outputText value="Prioridade" />
							</f:facet>
							<h:inputText onkeypress="mascara(this,soNumeros);" id="campo"
								rendered="#{(usuarioController.usuarioAutenticado.subLotacao.lotacao.codigo eq materialController.material.subLotacao.lotacao.codigo) || usuarioController.usuarioAutenticado.indAdministrador}"
								value="#{list.prioridade}" size="4" maxlength="11">
								<a4j:support event="onblur"
									action="#{materialController.numeroExiste2}" ajaxSingle="true"
									reRender="messages, salvar, form">
									<f:setPropertyActionListener value="#{list}"
										target="#{materialController.materialTemp}" />
								</a4j:support>
							</h:inputText>
							<h:outputText
								rendered="#{!(usuarioController.usuarioAutenticado.subLotacao.lotacao.codigo eq materialController.material.subLotacao.lotacao.codigo) && !(usuarioController.usuarioAutenticado.indAdministrador)}"
								value="#{list.prioridade}">
							</h:outputText>
						</rich:column>

						<rich:column width="30px" id="execucao" sortBy="#{list.execucao}">
							<f:facet name="header">
								<h:outputText value="Execução" />
							</f:facet>
							<h:outputText value="#{list.execucao}" />
						</rich:column>

					</rich:dataTable>
					<h:panelGrid columns="2"
						rendered="#{not empty materialController.materialList}">
						<a4j:commandButton value="Salvar" id="salvar"
							disabled="#{!materialController.botaoSalvar}"
							rendered="#{usuarioController.usuarioAutenticado.subLotacao.lotacao.codigo eq materialController.material.subLotacao.lotacao.codigo || usuarioController.usuarioAutenticado.indAdministrador}"
							action="#{materialController.salvarPrioridades}" reRender="form">
							<f:setPropertyActionListener value="#{list}"
								target="#{materialController.material}" />
							<f:setPropertyActionListener value="#{list.prioridade}"
								target="#{materialController.material.prioridade}" />
						</a4j:commandButton>
					</h:panelGrid>

					<h:panelGrid columns="10" cellpadding="10" id="totais1"
						rendered="#{not empty materialController.materialList}">
						<h:outputText value="Total: " style="font-weight: bold;" />
						<h:outputText value="#{materialController.total}"
							style="font-weight: bold;">
							<f:convertNumber type="currency" />
						</h:outputText>

						<h:outputText value="Total do Orçamento de Custeio: "
							style="font-weight: bold;"
							rendered="#{materialController.material.subElemento.elemento.categoria.codigo eq 3}" />
						<h:outputText
							rendered="#{materialController.material.subElemento.elemento.categoria.codigo eq 3}"
							value="#{materialController.material.subLotacao.lotacao.orcamentoCusteio}"
							style="font-weight: bold;">
							<f:convertNumber type="currency" />
						</h:outputText>

						<h:outputText value="Total do Orçamento de Capital: "
							rendered="#{materialController.material.subElemento.elemento.categoria.codigo eq 4}"
							style="font-weight: bold;" />
						<h:outputText
							rendered="#{materialController.material.subElemento.elemento.categoria.codigo eq 4}"
							value="#{materialController.material.subLotacao.lotacao.orcamentoCapital}"
							style="font-weight: bold;">
							<f:convertNumber type="currency" />
						</h:outputText>
					</h:panelGrid>

					<h:panelGrid columns="10" cellpadding="10" id="totais2"
						rendered="#{not empty materialController.materialList}">
						<h:outputText value="Total Acumulado: " style="font-weight: bold;" />
						<h:outputText value="#{materialController.acumulado}"
							style="font-weight: bold;">
							<f:convertNumber type="currency" />
						</h:outputText>

						<h:outputText value="Total Diferença: " style="font-weight: bold;" />
						<h:outputText value="#{materialController.diferenca}"
							style="font-weight: bold;">
							<f:convertNumber type="currency" />
						</h:outputText>
					</h:panelGrid>
				</rich:panel>
			</center>
		</a4j:form>
	</f:view>
</body>
</html>
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
				<rich:panel header="LISTAR MATERIAL / SERVIÇO"
					style="width: 100%; left: 100%;">

					<h:outputText value="Lotação: " />
					<h:selectOneMenu
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



					<a4j:region>
						<rich:dataTable id="listarMateriais"
							value="#{materialController.materialList}" var="list"
							columnClasses="center" rows="20" reRender="ds"
							rendered="#{not empty materialController.materialList}">
							
							<rich:column width="200px" sortBy="#{list.subElemento.descricao}">
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


							<rich:column width="50px"
								rendered="#{usuarioController.usuarioAutenticado.subLotacao.lotacao.codigo eq materialController.material.subLotacao.lotacao.codigo || usuarioController.usuarioAutenticado.indAdministrador}">
								<f:facet name="header">
									<h:outputText value="Editar" />
								</f:facet>
								<a4j:commandLink action="#{materialController.editar}"
									reRender="listarMateriais" ajaxSingle="true">
									<h:graphicImage value="../images/edit.gif" style="border:0"
										width="20" height="18" id="editar" />
									<f:setPropertyActionListener value="#{list}"
										target="#{materialController.material}" />
								</a4j:commandLink>
								<rich:toolTip for="editar" value="Editar" />
							</rich:column>

							<rich:column
								rendered="#{usuarioController.usuarioAutenticado.subLotacao.lotacao.codigo eq materialController.material.subLotacao.lotacao.codigo || usuarioController.usuarioAutenticado.indAdministrador}">
								<f:facet name="header">
									<h:outputText value="Excluir" />
								</f:facet>
								<a4j:commandLink ajaxSingle="true" id="delete"
									oncomplete="#{rich:component('deletePanel')}.show()">
									<h:graphicImage id="excluir" value="../images/delete.gif"
										style="border:0" />
									<f:setPropertyActionListener value="#{list}"
										target="#{materialController.material}" />
								</a4j:commandLink>
								<rich:toolTip for="excluir" value="Excluir" />
							</rich:column>

							<f:facet name="footer">
								<rich:datascroller id="ds"></rich:datascroller>
							</f:facet>
						</rich:dataTable>

						<h:outputText value="Total: "
							rendered="#{materialController.total > 0}"
							style="font-weight: bold;" />
						<h:outputText value="#{materialController.total}"
							rendered="#{materialController.total > 0}"
							style="font-weight: bold;">
							<f:convertNumber type="currency" />
						</h:outputText>
					</a4j:region>
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
										action="#{materialController.excluir}"
										oncomplete="#{rich:component('deletePanel')}.hide();"
										reRender="listarMateriais" /></td>
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
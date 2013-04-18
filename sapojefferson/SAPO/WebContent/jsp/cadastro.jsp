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
				<rich:panel header="CADASTRAR MATERIAL / SERVI�O"
					style="width: 100%; left: 100%;">
					<h:panelGrid columns="6">

						<h:outputText value="Lota��o: " />
						<h:selectOneMenu
							value="#{materialController.material.subLotacao.lotacao.codigo}"
							disabled="#{!usuarioController.usuarioAutenticado.indAdministrador}"
							required="true" requiredMessage="Campo Lota��o � obrigat�rio!">
							<f:selectItem itemLabel="SELECIONE" itemValue="" />
							<f:selectItems value="#{materialController.lotacoes}" />
							<a4j:support event="onchange"
								action="#{materialController.listarSubLotacoes}"
								ajaxSingle="true" reRender="sublotacoes"></a4j:support>
						</h:selectOneMenu>

						<h:outputText value="Sub Lota��o: " />
						<h:selectOneMenu
							value="#{materialController.material.subLotacao.codigo}"
							required="true" id="sublotacoes"
							requiredMessage="Campo Sub Lota��o � obrigat�rio!"
							disabled="#{materialController.comboSubLotacao}">
							<f:selectItem itemLabel="SELECIONE" itemValue="" />
							<f:selectItems value="#{materialController.subLotacoes}" />
						</h:selectOneMenu>


						<h:outputText value="Categoria: " />
						<h:selectOneMenu id="categoria"
							value="#{materialController.material.subElemento.elemento.categoria.codigo}"
							required="true" requiredMessage="Campo Categoria � obrigat�rio!">
							<f:selectItem itemLabel="SELECIONE" itemValue="" />
							<f:selectItems value="#{materialController.categorias}" />
							<a4j:support event="onchange"
								action="#{materialController.listarElementos}" ajaxSingle="true"
								reRender="elementos, material"></a4j:support>
						</h:selectOneMenu>


						<h:outputText value="Elemento: " />
						<h:selectOneMenu id="elementos"
							value="#{materialController.material.subElemento.elemento.codigo}"
							required="true" requiredMessage="Campo Elemento � obrigat�rio!">
							<f:selectItem itemLabel="SELECIONE" itemValue="" />
							<f:selectItems value="#{materialController.elementos}" />
							<a4j:support event="onchange"
								action="#{materialController.listarSubElementos}"
								ajaxSingle="true" reRender="subelementos"></a4j:support>
						</h:selectOneMenu>


						<h:outputText value="SubElemento: " id="sub"
							rendered="#{materialController.material.subElemento.elemento.codigo != 18 && materialController.material.subElemento.elemento.codigo != 01}" />
						<h:selectOneMenu id="subelementos"
							rendered="#{materialController.material.subElemento.elemento.codigo != 18 && materialController.material.subElemento.elemento.codigo != 01}"
							value="#{materialController.material.subElemento.codigo}"
							required="true"
							requiredMessage="Campo SubElemento � obrigat�rio!">
							<f:selectItem itemLabel="SELECIONE" itemValue="" />
							<f:selectItems value="#{materialController.subElementos}" />
						</h:selectOneMenu>


					</h:panelGrid>

					<h:panelGrid columns="2">


						<h:outputText value="Material / Servi�o: " />
						<h:inputText required="true"
							requiredMessage="Campo Material / Servi�o � obrigat�rio!"
							value="#{materialController.material.descricao}" size="90"
							maxlength="60">
						</h:inputText>


						<h:outputText value="Justificativa: " />
						<h:inputTextarea
							value="#{materialController.material.justificativa}" rows="5"
							cols="61" required="true"
							requiredMessage="Campo Justificativa � obrigat�rio!"
							validatorMessage="Justificativa deve ter no m�ximo 300 caracteres!">
							<f:validateLength maximum="300"></f:validateLength>
						</h:inputTextarea>

						<h:outputText value="Tipo: " />
						<h:selectOneMenu value="#{materialController.material.tipo}"
							required="true" requiredMessage="Campo Tipo � obrigat�rio!">
							<f:selectItem itemLabel="SELECIONE" itemValue="" />
							<f:selectItem itemLabel="ACAD�MICO" itemValue="ACAD�MICO" />
							<f:selectItem itemLabel="ADMINISTRATIVO"
								itemValue="ADMINISTRATIVO" />
							<f:selectItem itemLabel="AMBOS" itemValue="AMBOS" />
						</h:selectOneMenu>

						<h:outputText value="Qtd: ">
						</h:outputText>
						<h:inputText onkeypress="mascara(this,soNumeros);" required="true"
							requiredMessage="Campo Qtd. � obrigat�rio!"
							value="#{materialController.material.quantidade}" size="10"
							maxlength="11">
							<a4j:support event="onblur"
								action="#{materialController.calcularTotal}" ajaxSingle="true"
								reRender="total, salvar"></a4j:support>
						</h:inputText>

						<h:outputText value="Unidade: ">
						</h:outputText>
						<h:selectOneMenu
							value="#{materialController.material.unidade.codigo}"
							required="true" requiredMessage="Campo Unidade � obrigat�rio!">
							<f:selectItem itemLabel="SELECIONE" itemValue="" />
							<f:selectItems value="#{materialController.unidades}" />
						</h:selectOneMenu>

						<h:outputText value="Valor Unit.: " />
						<h:inputText
							value="#{materialController.material.vlrUnitarioString}"
							size="10" maxlength="13" required="true"
							converterMessage="Campo Valor Unit inv�lido"
							requiredMessage="Campo Valor Unit obrigat�rio!"
							onkeyup="mascara(this,moeda2);">
							<a4j:support event="onblur"
								action="#{materialController.calcularTotal}" ajaxSingle="true"
								reRender="total, salvar"></a4j:support>
						</h:inputText>

						<h:outputText value="Valor Total.: " />
						<h:outputText id="total"
							value="#{materialController.material.vlrTotal}">
							<f:convertNumber type="currency" />
						</h:outputText>

						<h:outputText value="Nome do Projeto: " />
						<h:inputText
							value="#{materialController.material.projetoVinculado}" size="90"
							maxlength="200" />

						<h:outputText value="Gestor do Projeto: " />
						<h:inputText
							value="#{materialController.material.responsavelProjeto}"
							size="90" maxlength="200" />

					</h:panelGrid>
					<a4j:commandButton id="salvar" value="Salvar"
						disabled="#{materialController.botao}"
						action="#{materialController.salvar}" reRender="form" />


				</rich:panel>
			</center>
		</a4j:form>
	</f:view>
</body>
</html>
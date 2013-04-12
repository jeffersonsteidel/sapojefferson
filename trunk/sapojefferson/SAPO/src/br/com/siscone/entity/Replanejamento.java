package br.com.siscone.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Replanejamento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private Material material;
	private BigDecimal vlrExecutado;
	private String vlrExecutadoString;
	private BigDecimal vlrReplanejado;
	private String vlrReplanejadoString;
	private BigDecimal vlrCancelado;
	private String vlrCanceladoString;
	private BigDecimal vlrSuplementar;
	private String vlrSuplamentarString;
	private BigDecimal vlrNovoSaldo;
	private String vlrNovoSaldoString;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getVlrNovoSaldo() {
		return vlrNovoSaldo;
	}

	public void setVlrNovoSaldo(BigDecimal vlrNovoSaldo) {
		this.vlrNovoSaldo = vlrNovoSaldo;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public BigDecimal getVlrExecutado() {
		return vlrExecutado;
	}

	public void setVlrExecutado(BigDecimal vlrExecutado) {
		this.vlrExecutado = vlrExecutado;
	}

	public String getVlrExecutadoString() {
		return vlrExecutadoString;
	}

	public void setVlrExecutadoString(String vlrExecutadoString) {
		this.vlrExecutadoString = vlrExecutadoString;
	}

	public BigDecimal getVlrReplanejado() {
		return vlrReplanejado;
	}

	public void setVlrReplanejado(BigDecimal vlrReplanejado) {
		this.vlrReplanejado = vlrReplanejado;
	}

	public String getVlrReplanejadoString() {
		return vlrReplanejadoString;
	}

	public void setVlrReplanejadoString(String vlrReplanejadoString) {
		this.vlrReplanejadoString = vlrReplanejadoString;
	}

	public BigDecimal getVlrCancelado() {
		return vlrCancelado;
	}

	public void setVlrCancelado(BigDecimal vlrCancelado) {
		this.vlrCancelado = vlrCancelado;
	}

	public String getVlrCanceladoString() {
		return vlrCanceladoString;
	}

	public void setVlrCanceladoString(String vlrCanceladoString) {
		this.vlrCanceladoString = vlrCanceladoString;
	}

	public BigDecimal getVlrSuplementar() {
		return vlrSuplementar;
	}

	public void setVlrSuplementar(BigDecimal vlrSuplementar) {
		this.vlrSuplementar = vlrSuplementar;
	}

	public String getVlrSuplamentarString() {
		return vlrSuplamentarString;
	}

	public void setVlrSuplamentarString(String vlrSuplamentarString) {
		this.vlrSuplamentarString = vlrSuplamentarString;
	}

	public String getVlrNovoSaldoString() {
		return vlrNovoSaldoString;
	}

	public void setVlrNovoSaldoString(String vlrNovoSaldoString) {
		this.vlrNovoSaldoString = vlrNovoSaldoString;
	}

}

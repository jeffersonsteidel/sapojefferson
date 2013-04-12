package br.com.siscone.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MoedaUtil {

	private static final Locale BRAZIL = new Locale("pt","BR");
	
	private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
	
	public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat(
			"###,###,##0.00", REAL);

	public static String mascaraDinheiro(BigDecimal valor, DecimalFormat moeda) {
		return moeda.format(valor);
	}

}


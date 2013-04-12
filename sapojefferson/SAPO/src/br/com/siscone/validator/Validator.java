package br.com.siscone.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validator {

	public static boolean validarCep(String cep) {
		String formato = "[0-9]{5}?\\-[0-9]{3}?";
		if ((cep == null) || (cep.length() != 9) || (!cep.matches(formato)))
			return false;
		else
			return true;
	}

	public static boolean validarTelefone(String tel) {
		String formato = "\\([0-9]{2}?\\)[0-9]{4}?\\-[0-9]{4}?";
		if ((tel == null) || (tel.length() != 13) || (!tel.matches(formato)))
			return false;
		else
			return true;
	}

	public static boolean validarCNPJCPF(final String CNPJ_CPF) {

		CNPJ_CPF.replace(".", "");
		CNPJ_CPF.replace("-", "");
		CNPJ_CPF.replace("/", "");
		if (CNPJ_CPF.length() == 11) { // CPF

			int acumulador1 = 0;
			int acumulador2 = 0;
			int resto = 0;

			StringBuffer resultado = new StringBuffer();

			String digitoVerificadorCPF = CNPJ_CPF.substring(
					(CNPJ_CPF.length() - 2), CNPJ_CPF.length());

			for (int i = 1; i < (CNPJ_CPF.length() - 1); i++) {
				acumulador1 += (11 - i)
						* Integer.valueOf(CNPJ_CPF.substring((i - 1), i));
				acumulador2 += (12 - i)
						* Integer.valueOf(CNPJ_CPF.substring((i - 1), i));
			}

			resto = acumulador1 % 11;

			if (resto < 2) {
				acumulador2 += 2;
				resultado.append(2);
			} else {
				acumulador2 += 2 * (11 - resto);
				resultado.append((11 - resto));
			}

			resto = acumulador2 % 11;

			if (resto < 2) {
				resultado.append(0);
			} else {
				resultado.append((11 - resto));
			}

			return resultado.toString().equals(digitoVerificadorCPF);
		} else if (CNPJ_CPF.length() == 14) { // CNPJ

			int acumulador = 0;
			int digito = 0;
			StringBuffer CNPJ = new StringBuffer();
			char[] CNPJCharArray = CNPJ_CPF.toCharArray();

			CNPJ.append(CNPJ_CPF.substring(0, 12));

			for (int i = 0; i < 4; i++) {
				if (((CNPJCharArray[i] - 48) >= 0)
						&& ((CNPJCharArray[i] - 48) <= 9)) {
					acumulador += (CNPJCharArray[i] - 48) * (6 - (i + 1));
				}
			}

			for (int i = 0; i < 8; i++) {
				if (((CNPJCharArray[i + 4] - 48) >= 0)
						&& ((CNPJCharArray[i + 4] - 48) <= 9)) {
					acumulador += (CNPJCharArray[i + 4] - 48) * (10 - (i + 1));
				}
			}

			digito = 11 - (acumulador % 11);

			CNPJ.append((digito == 10 || digito == 11) ? "0" : digito);

			acumulador = 0;

			for (int i = 0; i < 5; i++) {
				if (((CNPJCharArray[i] - 48) >= 0)
						&& ((CNPJCharArray[i] - 48) <= 9)) {
					acumulador += (CNPJCharArray[i] - 48) * (7 - (i + 1));
				}
			}

			for (int i = 0; i < 8; i++) {
				if (((CNPJCharArray[i + 5] - 48) >= 0)
						&& ((CNPJCharArray[i + 5] - 48) <= 9)) {
					acumulador += (CNPJCharArray[i + 5] - 48) * (10 - (i + 1));
				}
			}

			digito = 11 - (acumulador % 11);

			CNPJ.append((digito == 10 || digito == 11) ? "0" : digito);

			return CNPJ.toString().equals(CNPJ_CPF);
		}

		return false;
	}

	public static boolean validarEmail(String email) {
		if (email == null || email == "") {
			return true;
		}
		Pattern p = Pattern.compile(".+@.+\\.[A-Z]+");
		Matcher m = p.matcher(email);
		boolean matchFound = m.matches();
		if (matchFound)
			return true;
		else
			return false;
	}

	/*
	 * realiza a formatação do valor de acordo com a mascara enviada
	 */
	public static String formatar(String valor, String mascara) {

		String dado = "";
		// remove caracteres nao numericos
		for (int i = 0; i < valor.length(); i++) {
			char c = valor.charAt(i);
			if (Character.isDigit(c)) {
				dado += c;
			}
		}

		int indMascara = mascara.length();
		int indCampo = dado.length();

		for (; indCampo > 0 && indMascara > 0;) {
			if (mascara.charAt(--indMascara) == '#') {
				indCampo--;
			}
		}

		String saida = "";
		for (; indMascara < mascara.length(); indMascara++) {
			saida += ((mascara.charAt(indMascara) == '#') ? dado
					.charAt(indCampo++) : mascara.charAt(indMascara));
		}
		return saida;
	}

	public static String formatarCpf(String cpf) {
		while (cpf.length() < 11) {
			cpf = "0" + cpf;
		}
		return formatar(cpf, "###.###.###-##");
	}

	public static String formatarPis(String pis) {
		while (pis.length() < 11) {
			pis = "0" + pis;
		}
		return formatar(pis, "###.#####.##-#");
	}

	public static String formatarCep(String cep) {
		while (cep.length() < 8) {
			cep = "0" + cep;
		}
		return formatar(cep, "#####-###");
	}

	public static String formatarCnpj(String cnpj) {
		while (cnpj.length() < 14) {
			cnpj = "0" + cnpj;
		}
		return formatar(cnpj, "##.###.###/####-##");
	}

	public static String formatarData(String data) {
		if (data != null && !("00000000").equalsIgnoreCase(data)) {
			while (data.length() < 8) {
				data = "0" + data;
			}
			return formatar(data, "##/##/####");
		} else {
			return "01/01/2010";
		}
	}

	public static Date formatarDataBR(String data) {
		Date dataRetorno = null;
		if (data != null) {
			try {
				SimpleDateFormat dataFormat = new SimpleDateFormat("ddMMyyyy");
				dataRetorno = dataFormat.parse(data);
			} catch (Exception e) {
				System.err
						.println("Erro: Não foi possivel converter a data recebida ("
								+ data + ") !");
			}
		}
		return dataRetorno;
	}

	public static String formatarDataHoraBR(Date data) throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String str = fmt.format(data);
		return str;
	}

	public static boolean validaCPF(String cpf) {
		cpf = cpf.replace(".", "").replace("-", "");
		boolean ret = false;
		String base = "000000000";
		String digitos = "00";
		if (cpf.length() <= 11) {
			if (cpf.length() < 11) {
				cpf = base.substring(0, 11 - cpf.length()) + cpf;
				base = cpf.substring(0, 9);
			}
			base = cpf.substring(0, 9);
			digitos = cpf.substring(9, 11);
			int soma = 0, mult = 11;
			int[] var = new int[11];
			// Recebe os números e realiza a multiplicação e soma.
			for (int i = 0; i < 9; i++) {
				var[i] = Integer.parseInt("" + cpf.charAt(i));
				if (i < 9)
					soma += (var[i] * --mult);
			}
			// Cria o primeiro dígito verificador.
			int resto = soma % 11;
			if (resto < 2) {
				var[9] = 0;
			} else {
				var[9] = 11 - resto;
			}
			// Reinicia os valores.
			soma = 0;
			mult = 11;
			// Realiza a multiplicação e soma do segundo dígito.
			for (int i = 0; i < 10; i++)
				soma += var[i] * mult--;
			// Cria o segundo dígito verificador.
			resto = soma % 11;
			if (resto < 2) {
				var[10] = 0;
			} else {
				var[10] = 11 - resto;
			}
			if ((digitos.substring(0, 1).equalsIgnoreCase(new Integer(var[9])
					.toString()))
					&& (digitos.substring(1, 2).equalsIgnoreCase(new Integer(
							var[10]).toString()))) {
				ret = true;
			}
		}

		if (ret) {
			System.out.println("O CPF  [" + cpf + "]    é   válido.");
		} else {
			System.out.println("O CPF  [" + cpf + "]    é inválido.");
		}
		return ret;
	}

	/**
	 * Se a data vier separada por "-" Será trocado por "/"
	 */
	public static String verificaMascaraData(String data) {
		for (int i = 0; i < data.length(); i++) {
			char c = data.charAt(i);
			if (c == '-')
				data = data.replace('-', '/');
		}
		return data;
	}

	@SuppressWarnings("deprecation")
	public static Date somaData(Date data, int somaDias) {
		data.setDate(data.getDate() + somaDias);
		return data;
	}
}

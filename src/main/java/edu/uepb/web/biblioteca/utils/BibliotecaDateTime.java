package edu.uepb.web.biblioteca.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import edu.uepb.web.biblioteca.enums.TipoNivel;

/**
 * 
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 */
public class BibliotecaDateTime {
	private static final DateTimeFormatter default_format = DateTimeFormat.forPattern("dd/MM/yyyy");
	private static final int limiteDiaGraduacao = 15;
	private static final int limiteDiaPosGraduacao = 30;
	private static DateTime dateTime;

	public static String getDataCadastrado() {
		dateTime = new DateTime();
		return dateTime.toString(default_format);
	}

	public static String getDataDevolucao(TipoNivel nivel) {
		dateTime = new DateTime();
		if (nivel == TipoNivel.GRADUACAO) {
			return dateTime.plusDays(limiteDiaGraduacao).toString(default_format);
		}
		return dateTime.plusDays(limiteDiaPosGraduacao).toString(default_format);
	}

}

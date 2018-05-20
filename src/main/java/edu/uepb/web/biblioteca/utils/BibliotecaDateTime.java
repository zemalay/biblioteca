package edu.uepb.web.biblioteca.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import edu.uepb.web.biblioteca.dao.UniversidadeDAOImpl;
import edu.uepb.web.biblioteca.enums.TipoNivel;
import edu.uepb.web.biblioteca.model.Universidade;

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
	private static UniversidadeDAOImpl universidadeDAO;

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

	public static DateTime stringToDateTime(String context) {
		return dateTime = DateTime.parse(context, default_format);
	}

	public static int diasParaFimPeriodo(String dataDevolucao) {
		universidadeDAO = new UniversidadeDAOImpl();
		Universidade universidade = universidadeDAO.get();
		DateTime dateDevolucao = BibliotecaDateTime.stringToDateTime(dataDevolucao);
		DateTime dateFimPeriodo = BibliotecaDateTime.stringToDateTime(universidade.getFimPeriodo());

		int dias = Days.daysBetween(dateDevolucao.toLocalDate(), dateFimPeriodo.toLocalDate()).getDays();

		return dias;
	}

	public static String getDataRenovacao(String data, TipoNivel nivel) {
		DateTime dateTime = stringToDateTime(data);
		if (nivel == TipoNivel.GRADUACAO) {
			return dateTime.plusDays(limiteDiaGraduacao).toString(default_format);
		}
		return dateTime.plusDays(limiteDiaPosGraduacao).toString(default_format);
	}

}

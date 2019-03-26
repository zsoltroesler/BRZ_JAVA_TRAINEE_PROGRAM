package at.gv.brz.exceptions;

import java.sql.SQLException;
import java.text.ParseException;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;


/**
 * Fehlecodes fuer BRZException-Klasse
 * @author roeslerz
 *
 */
public enum FehlerCodes {

	F01("Sql Statement fehlerhaft"), // BadSqlGrammarException
	F02("Ein doppelter Primary Key wurde eingegeben"), // DuplicateKeyException
	F03("Das Ergebnis aus der Datenbank ist leer"), // EmptyResultDataAccessException
	F04("Doppelte Primary Key oder Uniqe Feld"), // DataIntegrityViolationException
	F05("SQL Fehler, muss genauer identifiziert werden"), // SQLException
	F06("Parse Exception, irgendwo falsches Datenformat"), // ParseException
	F07("Unbekannter Fehler"), F11("Error bei der Validation"),
	F08("Datei wurde nicht gefunden!");
 
	private String text;

	FehlerCodes(String text) {
		this.text = text;
	}
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Generiert einen Fehlercode anhand der Übergebenen Exception.
	 * 
	 * @param e Exception, welche geworfen wurde
	 * @return Fehlercode passend zur Exception
	 */
	public static FehlerCodes generateFehlerCode(Exception e) {

		if (e instanceof BadSqlGrammarException) {
			return FehlerCodes.F01;
		} else if (e instanceof DuplicateKeyException) {
			return FehlerCodes.F02;
		} else if (e instanceof EmptyResultDataAccessException) {
			return FehlerCodes.F03;
		} else if (e instanceof DataIntegrityViolationException) {
			return FehlerCodes.F04;
		} else if (e instanceof SQLException) {
			return FehlerCodes.F05;
		} else if (e instanceof ParseException) {
			return FehlerCodes.F06;
		} else if (e instanceof EntityNotFoundException) {
			return FehlerCodes.F08;
		} else {
			return FehlerCodes.F07;
		}

	}

}

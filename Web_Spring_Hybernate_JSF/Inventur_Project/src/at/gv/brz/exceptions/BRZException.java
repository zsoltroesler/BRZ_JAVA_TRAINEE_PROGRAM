package at.gv.brz.exceptions;

import at.gv.brz.exceptions.FehlerCodes;

/**
 * BRZException-Klasse, die FehlerCodes Enum verwendet
 *
 * @author roeslerz
 *
 */
public class BRZException extends Exception {

    private static final long serialVersionUID = 1L;
    private FehlerCodes fehlerCode;
    
	public BRZException(Exception e) {
		super(e);
		fehlerCode = FehlerCodes.generateFehlerCode(e);
	}

    public BRZException(FehlerCodes fehlerCode) {
        super(fehlerCode.name());
        this.fehlerCode = fehlerCode;

    }
	
	public String getBrzFehlercode() {
		return "Fehlercode: " + fehlerCode;
	}
	
	public String getBrzMessage() {
		return "Fehler: " + fehlerCode.getText();
	}

}

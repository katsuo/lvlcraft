package me.l5d.lcprayer;

public class AltarException extends Exception {
	private static final long serialVersionUID = -25839661393725310L;
	public String error;

	public AltarException() {
		super();
	}

	public AltarException(String error) {
		super(error);
		this.error = error;
	}

	public String getError() {
		return error;
	}
}

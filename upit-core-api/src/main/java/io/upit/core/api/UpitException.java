package io.upit.core.api;

/**
 * The Class UpitException represents a general exception in the upit code base
 */
public class UpitException extends Exception {

	/** Auto-generated by eclipse. */
	private static final long serialVersionUID = -5455231428410510798L;

	/**
	 * Instantiates a new UpitException.
	 */
	public UpitException() {
	}

	/**
	 * Instantiates a new UpitException.
	 *
	 * @param msg the msg
	 */
	public UpitException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new UpitException.
	 *
	 * @param msg the msg
	 * @param parent the parent
	 */
	public UpitException(String msg, Throwable parent) {
		super(msg, parent);
	}

}

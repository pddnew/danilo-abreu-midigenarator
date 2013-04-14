/*
 * @(#) MadureiraException      20/06/2010
 */
package br.com.madureira.util;

/**
 * Classe de Exception proprietaria
 *
 * @version 1.0
 * @author Danilo de Sousa Abreu
 */
public class MadureiraException extends Exception {

    /**
     * Constrói uma <code>MadureiraException</code> com
     * <code>null</code> para mensage detalhada do Erro.
     */
    public MadureiraException() {
	super();
    }

    /**
     *  Constrói uma <code>MadureiraException</code> com uma
     *  Com uma mensagem específica
     *
     * @param String com a mensagem com detalhes específicos do erro.
     */
    public MadureiraException(String message) {
	super(message);
    }
}

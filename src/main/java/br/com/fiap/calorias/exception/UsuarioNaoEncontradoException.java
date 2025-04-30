package br.com.fiap.calorias.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

	
	//@ExceptionHandler verificar opcoes
	public UsuarioNaoEncontradoException (String mensagem) {
		super(mensagem);
	}
	
}

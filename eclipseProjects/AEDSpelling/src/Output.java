public enum Output {

	
	UNKNOWN_COMMAND("Unknown Command"),
	UNKNOWN_WORD_TYPE("Unknow Word Type"),
	INPUT_ERROR("Invalid Input"),
	ADD_WORDS_SUCCESS("Atualizacao do dicionario com sucesso."),
	ADD_WORDS_FAILED("Lista de palavras nao contem palavras novas."),
	WORD_FOUND("Palavra correta."),
	WORD_NOT_FOUND("Erro ortografico."),
	ADD_TEXT_SUCCESS("Texto adicionado com sucesso."),
	ADD_TEXT_FAILED("Identificador de texto ja existente."),
	REMOVE_TEXT_SUCCESS("Remocao de texto com sucesso."),
	INVALID_LINE_INTERVAL("Intervalo de numero de linhas mal definido."),
	EXCERPT_NOT_FOUND("Troco inexistente."),
	LIST_ERRORS_SUCCESS("palavra ocorre %s vezes no texto referido."),
	LIST_ERRORS_FAIL("Inexistencia de erros ortograficos no texto."),	
	TEXT_NOT_FOUND("Texto inexistente.");
	
			
	
	private static final String PLACEHOLDER  = "%s";
	private String string;
	
	/**
	 * Returns the corresponding string output
	 * 
	 * @param values Values to replace the placeholder for
	 * @return The corresponding string output
	 */
	public String message(String ... values){
		String result = this.string;
				
		for(String value : values){
			result = result.replaceFirst(PLACEHOLDER, value);
		}
		
		return result;
	}
	
	/**
	 * Returns the corresponding string output
	 * 
	 * @return The corresponding string output
	 */
	public String message() {
		return this.string;
		
	}
	
	private Output(String string) {
		this.string = string;
	}		
	
	
}

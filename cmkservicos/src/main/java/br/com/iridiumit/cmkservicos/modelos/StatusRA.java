package br.com.iridiumit.cmkservicos.modelos;

public enum StatusRA {
	
	AGENDADA("Agendada"),
	AGUARDANDO("Aguardando"),
	ATENDIMENTO("Em atendimento"), 
	CANCELADA("Cancelada"),
	CONFIRMADA("Confirmada"),
	FINALIZADA("Finalizada");
	
	private String descricao;
	
	StatusRA(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}

}

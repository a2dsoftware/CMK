package br.com.iridiumit.cmkservicos.modelos;

public enum StatusRA {
	
	AGENDADA("Agendada"),
	ELABORADA("Elaborada"),
	AGUARDANDO("Aguardando Pedido"),
	ATENDIMENTO("Em atendimento"), 
	CANCELADA("Cancelada"),
	VALIDADA("Validada"),
	FINALIZADA("Finalizada");
	
	private String descricao;
	
	StatusRA(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}

}

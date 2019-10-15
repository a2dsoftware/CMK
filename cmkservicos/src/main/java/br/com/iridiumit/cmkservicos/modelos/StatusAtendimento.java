package br.com.iridiumit.cmkservicos.modelos;

public enum StatusAtendimento {
	
	AGENDADA("Agendada"),
	ELABORADA("Elaborada"),
	AGUARDANDO("Aguardando Pedido"),
	ATENDIMENTO("Em atendimento"), 
	CANCELADA("Cancelada"),
	VALIDADA("Validada"),
	FINALIZADA("Finalizada");
	
	private String descricao;
	
	StatusAtendimento(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}

}

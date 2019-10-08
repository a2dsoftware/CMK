package br.com.iridiumit.cmkservicos.modelos;

public enum TipoRA {
		
	EMERGENCIAL("Emergencial"),
	CORRETIVAPROGRAMADA("Corretiva Programada"),
	DIARIODEOBRAS("Diário de Obras"),
	PREVENTIVA("Preventiva");
	
	private String descricao;
	
	TipoRA(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}

}

package br.com.iridiumit.cmkservicos.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Atendimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;
	
	private Integer nros;
	
	@NotEmpty(message = "{tipo.not.empty}")
	private String tipo;
	
	@Column(name="data_atendimento")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	private Date dataAtendimento;
	
	@Column(name="inicio_atendimento")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	private Date inicioAtendimento;
	
	@Column(name="fim_atendimento")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	private Date fimAtendimento;
	
	@NotEmpty(message = "{status.not.empty}")
	private String status;
	
	@NotEmpty(message = "{cliente.not.empty}")
	private String cliente;
	
	private String planta;
	
	private String area;
	
	private String unidade;
	
	@NotEmpty(message = "{solicitante.not.empty}")
	private String solicitante;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipamento_id", nullable = false)
    private Equipamento equipamento;
	
	//@NotEmpty(message = "{nota.not.empty}")
	private String nota;
	
	@NotEmpty(message = "{descricao.not.empty}")
	private String descricao;
	
	//@NotEmpty(message = "{diagnostico.not.empty}")
	private String diagnostico;
	
	private boolean programada;
	
	private boolean mparada;
	
	private boolean cobrar;
	
	private String observacoes;
	
	private String resalvas;
	
	private String ObsNaoApto;
	
	@NotNull(message = "{emissor.not.null}")
	private String emissor;
	
	private String executor;
	
	private String aprovador;
	
	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Date getInicioAtendimento() {
		return inicioAtendimento;
	}

	public void setFimAtendimento(Date fimAtendimento) {
		this.fimAtendimento = fimAtendimento;
	}
	
	public Date getFimAtendimento() {
		return fimAtendimento;
	}

	public void setInicioAtendimento(Date inicioAtendimento) {
		this.inicioAtendimento = inicioAtendimento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Integer getNros() {
		return nros;
	}

	public void setNros(Integer nros) {
		this.nros = nros;
	}

	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getResalvas() {
		return resalvas;
	}

	public void setResalvas(String resalvas) {
		this.resalvas = resalvas;
	}

	public String getObsNaoApto() {
		return ObsNaoApto;
	}

	public void setObsNaoApto(String obsNaoApto) {
		ObsNaoApto = obsNaoApto;
	}

	public String getEmissor() {
		return emissor;
	}

	public void setEmissor(String emissor) {
		this.emissor = emissor;
	}

	public String getAprovador() {
		return aprovador;
	}

	public void setAprovador(String aprovador) {
		this.aprovador = aprovador;
	}

	public boolean isProgramada() {
		return programada;
	}

	public void setProgramada(boolean programada) {
		this.programada = programada;
	}

	public boolean isMparada() {
		return mparada;
	}

	public void setMparada(boolean mparada) {
		this.mparada = mparada;
	}

	public boolean isCobrar() {
		return cobrar;
	}

	public void setCobrar(boolean cobrar) {
		this.cobrar = cobrar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atendimento other = (Atendimento) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}	

}

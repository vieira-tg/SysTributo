package br.com.systributo.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_NCM")
public class Ncm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//@NotNull
	private String decricao;

	//@NotNull
	private String ncm;

	private String codigoNatureza;

	@ManyToOne
	@JoinColumn(name = "id_pis_entrada")
	private Pis cstPisEntrada;

	@ManyToOne
	@JoinColumn(name = "id_pis_saida")
	private Pis cstPisSaida;

	@ManyToOne
	@JoinColumn(name = "id_cofins_entrada")
	private Cofins cstCofinsEntrada;

	@ManyToOne
	@JoinColumn(name = "id_cofins_saida")
	private Cofins cstCofinsSaida;

	//@NotNull
	@Enumerated(EnumType.STRING)
	private Uf uf;
	private BigDecimal aliguotaIcmsNormal;
	private BigDecimal reducaoIcms;
	private BigDecimal aliquotaEfetiva;
	private String cstIcmsSaida;

	@ManyToOne
	@JoinColumn(name = "crt_id")
	//@NotNull
	private Crt crt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDecricao() {
		return decricao;
	}

	public void setDecricao(String decricao) {
		this.decricao = decricao;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public String getCodigoNatureza() {
		return codigoNatureza;
	}

	public void setCodigoNatureza(String codigoNatureza) {
		this.codigoNatureza = codigoNatureza;
	}

	public Pis getCstPisEntrada() {
		return cstPisEntrada;
	}

	public void setCstPisEntrada(Pis cstPisEntrada) {
		this.cstPisEntrada = cstPisEntrada;
	}

	public Pis getCstPisSaida() {
		return cstPisSaida;
	}

	public void setCstPisSaida(Pis cstPisSaida) {
		this.cstPisSaida = cstPisSaida;
	}

	public Cofins getCstCofinsEntrada() {
		return cstCofinsEntrada;
	}

	public void setCstCofinsEntrada(Cofins cstCofinsEntrada) {
		this.cstCofinsEntrada = cstCofinsEntrada;
	}

	public Cofins getCstCofinsSaida() {
		return cstCofinsSaida;
	}

	public void setCstCofinsSaida(Cofins cstCofinsSaida) {
		this.cstCofinsSaida = cstCofinsSaida;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public BigDecimal getAliguotaIcmsNormal() {
		return aliguotaIcmsNormal;
	}

	public void setAliguotaIcmsNormal(BigDecimal aliguotaIcmsNormal) {
		this.aliguotaIcmsNormal = aliguotaIcmsNormal;
	}

	public BigDecimal getReducaoIcms() {
		return reducaoIcms;
	}

	public void setReducaoIcms(BigDecimal reducaoIcms) {
		this.reducaoIcms = reducaoIcms;
	}

	public BigDecimal getAliquotaEfetiva() {
		return aliquotaEfetiva;
	}

	public void setAliquotaEfetiva(BigDecimal aliquotaEfetiva) {
		this.aliquotaEfetiva = aliquotaEfetiva;
	}

	public String getCstIcmsSaida() {
		return cstIcmsSaida;
	}

	public void setCstIcmsSaida(String cstIcmsSaida) {
		this.cstIcmsSaida = cstIcmsSaida;
	}

	public Crt getCrt() {
		return crt;
	}

	public void setCrt(Crt crt) {
		this.crt = crt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Ncm other = (Ncm) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

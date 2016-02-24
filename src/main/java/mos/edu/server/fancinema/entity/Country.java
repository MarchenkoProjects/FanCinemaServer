package mos.edu.server.fancinema.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mos.edu.server.fancinema.Constants;

@Entity
@Table(name = Constants.TABLE_COUNTRIES)
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String COLUMN_ID_COUNTRY = "id_country";
	public static final String COLUMN_COUNTRY_EN = "country_en";
	public static final String COLUMN_COUNTRY_RU = "country_ru";
	
	private static final String COUNTRIES_MAPPED_FILMS = "countries";
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", 
					  strategy = "increment")
	@Column(name = COLUMN_ID_COUNTRY, nullable = false, columnDefinition = "SMALLINT(3) UNSIGNED")
	private short idCountry;
	
	@JsonIgnore
	@Column(name = COLUMN_COUNTRY_EN, length = 40, nullable = true)
	private String countryEn;
	
	@Column(name = COLUMN_COUNTRY_RU, length = 40, nullable = false)
	private String countryRu;
	
	@JsonIgnore
	@ManyToMany(mappedBy = COUNTRIES_MAPPED_FILMS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Film> films;
	
	protected Country() {}

	public short getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(short idCountry) {
		this.idCountry = idCountry;
	}

	public String getCountryEn() {
		return countryEn;
	}

	public void setCountryEn(String countryEn) {
		this.countryEn = countryEn;
	}

	public String getCountryRu() {
		return countryRu;
	}

	public void setCountryRu(String countryRu) {
		this.countryRu = countryRu;
	}

	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}
	
}

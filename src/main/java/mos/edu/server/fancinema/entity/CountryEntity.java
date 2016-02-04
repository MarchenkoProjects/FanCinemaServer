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

import mos.edu.server.fancinema.Constants;

@Entity
@Table(name = Constants.TABLE_COUNTRIES)
public class CountryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String COLUMN_ID_COUNTRY = "id_country";
	public static final String COLUMN_COUNTRY_EN = "country_en";
	public static final String COLUMN_COUNTRY_RU = "country_ru";
	
	private static final String COUNTRIES_MAPPED_FILMS = "countries";
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", 
					  strategy = "increment")
	@Column(name = COLUMN_ID_COUNTRY, nullable = false, columnDefinition = "TINYINT(3) UNSIGNED")
	private byte idCountry;
	
	@Column(name = COLUMN_COUNTRY_EN, length = 40, nullable = true)
	private String countryEn;
	
	@Column(name = COLUMN_COUNTRY_RU, length = 40, nullable = false)
	private String countryRu;
	
	@ManyToMany(mappedBy = COUNTRIES_MAPPED_FILMS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<FilmEntity> films;
	
	protected CountryEntity() {}

	public CountryEntity(byte idCountry, String countryEn, String countryRu, Set<FilmEntity> films) {
		this.idCountry = idCountry;
		this.countryEn = countryEn;
		this.countryRu = countryRu;
		this.films = films;
	}

	public byte getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(byte idCountry) {
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

	public Set<FilmEntity> getFilms() {
		return films;
	}

	public void setFilms(Set<FilmEntity> films) {
		this.films = films;
	}
	
}

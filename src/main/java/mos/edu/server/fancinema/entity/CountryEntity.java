package mos.edu.server.fancinema.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import mos.edu.server.fancinema.Constants;

@Entity
@Table(name = Constants.TABLE_COUNTRIES)
public class CountryEntity {
	private static final String COLUMN_ID_COUNTRY = "id_country";
	private static final String COLUMN_COUNTRY_EN = "country_en";
	private static final String COLUMN_COUNTRY_RU = "country_ru";
	
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
	
	@ManyToMany(mappedBy = COUNTRIES_MAPPED_FILMS)
	private Set<FilmEntity> films;
	
	protected CountryEntity() {}
}

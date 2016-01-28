package mos.edu.server.fancinema.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "countries")
public class CountryEntity {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", 
					  strategy = "increment")
	@Column(name = "id_country",
			columnDefinition = "TINYINT(3) UNSIGNED")
	private byte idGenre;
	
	@Column(name = "country_en",
			length = 40,
			nullable = true)
	private String countryEn;
	
	@Column(name = "country_ru",
			length = 40,
			nullable = false)
	private String countryRu;
	
	@ManyToMany(mappedBy = "countries")
	private Set<FilmEntity> films;
	
	protected CountryEntity() {}
	
}

package mos.edu.server.fancinema.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.compound_key.RatingKey;

@Entity
@Table(name = Constants.TABLE_RATING_FILM)
public class RatingFilmEntity {
	private static final String COLUMN_RATING = "rating";
	
	@EmbeddedId
	@AssociationOverrides({
		@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "user_id")),
		@AssociationOverride(name = "film", joinColumns = @JoinColumn(name = "film_id"))
	})
	private RatingKey ratingFilmKey = new RatingKey();
	
	@Column(name = COLUMN_RATING, nullable = false, columnDefinition = "TINYINT(2) UNSIGNED")
	private byte rating;
	
	protected RatingFilmEntity() {}
}

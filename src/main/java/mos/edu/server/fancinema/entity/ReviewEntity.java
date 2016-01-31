package mos.edu.server.fancinema.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.compound_key.ReviewKey;

@Entity
@Table(name = Constants.TABLE_REVIEWS)
public class ReviewEntity {
	private static final String COLUMN_REVIEW = "review";

	@EmbeddedId
	@AssociationOverrides({
		@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "user_id")),
		@AssociationOverride(name = "film", joinColumns = @JoinColumn(name = "film_id"))
	})
	private ReviewKey reviewKey = new ReviewKey();
	
	@Column(name = COLUMN_REVIEW, nullable = false, columnDefinition = "TEXT")
	private String review;
	
	protected ReviewEntity() {}
	
}

package mos.edu.server.fancinema.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.compound_key.FavoriteKey;

@Entity
@Table(name = Constants.TABLE_FAVORITE)
public class FavoriteEntity {
	private static final String COLUMN_LOOKED = "looked";

	@EmbeddedId
	@AssociationOverrides({
		@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "user_id")),
		@AssociationOverride(name = "film", joinColumns = @JoinColumn(name = "film_id"))
	})
	private FavoriteKey favoriteKey = new FavoriteKey();
	
	@Column(name = COLUMN_LOOKED, nullable = false, columnDefinition = "TINYINT(1) UNSIGNED")
	private boolean looked;
	
	protected FavoriteEntity() {}
	
}

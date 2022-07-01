package com.park.spacemng.model.space.space;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.park.spacemng.model.mogo.MongoBaseEntity;
import com.park.spacemng.model.user.owner.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "spaces")
public class Space extends MongoBaseEntity {

	private String batchId;

	private String title;

	private String description;

	private String address;

	@GeoSpatialIndexed(name = "position_index")
	private Point position;

	private Owner owner;

	private Status status;

	@AllArgsConstructor
	public enum Status {

		FREE(0), PROCESSING(1), TAKEN(2);

		private final int value;

		@JsonCreator
		public static Status fromValue(int value) {
			return Stream.of(Status.values()).filter(status -> status.value == value).findFirst()
					.orElseThrow(() -> new IllegalArgumentException("invalid space statue found: " + value));
		}

		@JsonValue
		public int toValue() {
			return value;
		}

	}

	public boolean equalsById(Space space) {
		return space.getId().equals(this.getId());
	}

}
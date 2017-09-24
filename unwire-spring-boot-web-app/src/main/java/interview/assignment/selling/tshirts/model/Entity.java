package interview.assignment.selling.tshirts.model;

import java.util.Objects;
import java.util.UUID;

public class Entity {

	private String id;

	// Constructors
	public Entity() {

		if (this.id == null) {
			this.id = UUID.randomUUID().toString();
		}
	}

	/*
	 * Are two entities the same?
	 */
	public boolean isSame(Entity entity) {

		return entity != null && Objects.equals(this.getId(), entity.getId());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

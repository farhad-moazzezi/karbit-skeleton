package org.karbit.skeleton.mongo.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;


@Data
public abstract class BaseMongoEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 2297296355814166563L;

	@Id
	private String id;

	@Indexed(unique = true)
	private String uniqueId = UUID.randomUUID().toString();

	@Indexed
	private long creationDate = new Date().getTime();

	public BaseMongoEntity() {
	}

	public String toString() {
		return "MongoBaseEntity(super=" + super.toString() + ", id=" + this.getId() + ")";
	}
}

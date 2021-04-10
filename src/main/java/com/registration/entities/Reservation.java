package com.registration.entities;

import org.bson.types.ObjectId;

public class Reservation {

	private ObjectId _id;
	private String id;
	private String name;
	private String surname;
	private String date;

	public ObjectId getOid() {
		return _id;
	}

	public void setOid(ObjectId _id) {
		this._id = _id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "[date=" + date +", id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}

}

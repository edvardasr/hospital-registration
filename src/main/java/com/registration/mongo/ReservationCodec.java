package com.registration.mongo;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import com.registration.entities.Reservation;

public class ReservationCodec implements CollectibleCodec<Reservation> {

	private Codec<Document> documentCodec;

	public ReservationCodec() {

		this.documentCodec = new DocumentCodec();
	}

	public ReservationCodec(Codec<Document> codec) {
		this.documentCodec = codec;
	}

	@Override
	public void encode(BsonWriter writer, Reservation value, EncoderContext encoderContext) {
		Document document = new Document();

		ObjectId oid = value.getOid();
		String id = value.getId();
		String name = value.getName();
		String surname = value.getSurname();
		String date = value.getDate();

		if (null != oid) {
			document.put("_id", oid);
		}

		if (null != id) {
			document.put("id", id);
		}

		if (null != name) {
			document.put("name", name);
		}

		if (null != surname) {
			document.put("surname", surname);
		}

		if (null != date) {
			document.put("date", date);
		}

		documentCodec.encode(writer, document, encoderContext);

	}

	@Override
	public Class<Reservation> getEncoderClass() {
		return Reservation.class;
	}

	@Override
	public Reservation decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);

		Reservation reservation = new Reservation();

		reservation.setOid(document.getObjectId("_id"));

		reservation.setId(document.getString("id"));

		reservation.setName(document.getString("name"));

		reservation.setSurname(document.getString("surname"));

		reservation.setDate(document.getString("date"));

		return reservation;
	}

	@Override
	public Reservation generateIdIfAbsentFromDocument(Reservation reservation) {
		if (!documentHasId(reservation)) {
			reservation.setOid(new ObjectId());
		}

		return reservation;
	}

	@Override
	public boolean documentHasId(Reservation reservation) {
		return null == reservation.getOid();
	}

	@Override
	public BsonValue getDocumentId(Reservation reservation) {
		if (!documentHasId(reservation)) {
			throw new IllegalStateException("The document does not contain an _id");
		}

		return new BsonString(reservation.getOid().toHexString());
	}
}
package com.registration.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.registration.entities.Reservation;

import static com.mongodb.client.model.Filters.eq;

@Component
public class Repository {

	private static final Logger logger = LoggerFactory.getLogger(Repository.class);

	private MongoCollection<Reservation> collection;

	@SuppressWarnings("resource")
	public Repository() {

		CodecRegistry codecRegistry = MongoClient.getDefaultCodecRegistry();
		Codec<Document> documentCodec = codecRegistry.get(Document.class);
		Codec<Reservation> dataCodec = new ReservationCodec(documentCodec);

		codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromCodecs(documentCodec, dataCodec));

		MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();

		MongoClient mongo = new MongoClient(new ServerAddress("localhost", 27017), options);

		collection = mongo.getDatabase("db").getCollection("reservation", Reservation.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Reservation> findAllById(String id) {

		logger.debug("Entering findAll");

		List<Reservation> list = (List<Reservation>) collection.find(eq("id", id)).into(new ArrayList());

		logger.debug("Leaving findAll");

		return list;
	}

	public void insertOne(Reservation reservation) {

		logger.debug("Entering insertOne");

		collection.insertOne(reservation);

		logger.debug("Leaving insertOne");
	}

}

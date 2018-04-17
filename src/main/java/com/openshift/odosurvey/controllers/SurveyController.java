package com.openshift.odosurvey.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Configuration
public class SurveyController {
    private static final String COLLECTION = "odo";

    @Autowired
	private Environment env;

	private MongoDatabase mongoDB = null;
	
     

	public void saveDocument(String inDocument) {
		String mongoHost = env.getProperty("MONGODB_SERVICE_HOST", "127.0.0.1"); // env var MONGODB_SERVER_HOST takes precedence
        String mongoPort = env.getProperty("MONGODB_SERVICE_PORT", "27017"); // env var MONGODB_SERVER_PORT takes precedence
        String mongoUser = env.getProperty("mongodb_user", "mongodb"); // env var MONGODB_USER takes precedence
        String mongoPassword = env.getProperty("mongodb_password", "mongodb"); // env var MONGODB_PASSWORD takes precedence
        String mongoDBName = env.getProperty("mongodb_database", "mongodb"); // env var MONGODB_DATABASE takes precedence

        try {
            String mongoURI = "mongodb://" + mongoUser + ":" + mongoPassword + "@" + mongoHost + ":" + mongoPort + "/" + mongoDBName;
            System.out.println("[INFO] Connection string: " + mongoURI);
            MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoURI));
            mongoDB = mongoClient.getDatabase(mongoDBName);
        } catch (Exception e) {
            System.out.println("[ERROR] Creating the mongoDB. " + e.getMessage());
            mongoDB = null;
        }
    
		mongoDB.getCollection(COLLECTION).insertOne(Document.parse(inDocument));
	}

}
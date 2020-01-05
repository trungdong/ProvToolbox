package org.openprovenance.prov.storage.mongodb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.openprovenance.prov.service.core.NonDocumentResourceStorage;

import java.io.*;
/* Documentation
https://howtodoinjava.com/mongodb/java-mongodb-getsave-image-using-gridfs-apis/
*/


public class MongoNonDocumentResourceStorage implements NonDocumentResourceStorage {

    public static final String COLLECTION_FILES = "files";
    private static final String KEY_EXTENSION = "EXTENSION";
    private static final String KEY_MIME_TYPE = "MIME_TYPE";
    private static final String KEY_CONTENTS = "CONTENTS";
    private final DB db;

    MongoNonDocumentResourceStorage() {

        System.out.println("Creating a client");
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        DB db = mongoClient.getDB("provservice");
        this.db=db;
        mongoClient.getDatabaseNames().forEach(System.out::println);
        db.createCollection(COLLECTION_FILES, null);
        db.getCollectionNames().forEach(System.out::println);
    }

    MongoNonDocumentResourceStorage(DB db) {
        this.db=db;
    }
    
    @Override
    public String newStore(String suggestedExtension, String mimeType) throws IOException {
        DBCollection collection = db.getCollection(COLLECTION_FILES);
        BasicDBObject document = new BasicDBObject();
        document.put(KEY_EXTENSION,suggestedExtension);
        document.put(KEY_MIME_TYPE,mimeType);
        WriteResult result=collection.insert(document);
        ObjectId id = (ObjectId)document.get( "_id" );
        return id.toHexString();
    }

    @Override
    public void copyInputStreamToStore(InputStream inputStream, String id) throws IOException {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        IOUtils.copy(inputStream,baos);
        copyStringToStore(baos.toString(),id);
    }

    public DBObject findDocumentById(DBCollection collection, String id) {

        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));

        DBObject dbObj = collection.findOne(query);
        return dbObj;
    }

    @Override
    public void copyStringToStore(CharSequence str, String id) throws IOException {
        DBCollection collection = db.getCollection(COLLECTION_FILES);

        BasicDBObject query = new BasicDBObject();
        query.put("_id",new ObjectId(id));

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put(KEY_CONTENTS,str);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        collection.update(query, updateObject);
    }

    @Override
    public void serializeObjectToStore(ObjectMapper om, Object o, String id) throws IOException {
        throw new UnsupportedOperationException("yet!");
    }

    @Override
    public void copyStoreToOutputStream(String id, OutputStream outputStream) throws IOException {
        DBCollection collection = db.getCollection(COLLECTION_FILES);

        BasicDBObject query = new BasicDBObject();
        query.put("_id",new ObjectId(id));

        DBCursor result=collection.find(query);
        String contents=(String) result.next().get(KEY_CONTENTS);

        new PrintStream(outputStream).print(contents);
    }

    @Override
    public <T> T deserializeObjectFromStore(ObjectMapper om, String id, Class<T> clazz) throws IOException {
        throw new UnsupportedOperationException("yet!");
    }

    @Override
    public boolean delete(String storageId) {
        DBCollection collection = db.getCollection(COLLECTION_FILES);

        BasicDBObject query = new BasicDBObject();
        query.put("_id",new ObjectId(storageId));

        WriteResult res=collection.remove(query);
        return res!=null;
    }
}

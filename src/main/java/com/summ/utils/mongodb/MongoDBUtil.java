package com.summ.utils.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import com.summ.utils.StringUtil;
import com.summ.utils.mongodb.model.MongoConfig;
import com.summ.utils.mongodb.model.MongoResponse;
import org.bson.Document;
import sun.security.jca.ServiceId;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author johnson
 */
public class MongoDBUtil {

    private String SERVER_ADDR;
    private int SERVER_PORT;
    private String USER_NAME;
    private String DB_NAME;
    private String DB_PWD;

    private MongoClient mongoClient = null;
    private MongoDatabase mongoDatabase = null;

    private MongoDBUtil(MongoConfig mongoConfig) {
        this.SERVER_ADDR = mongoConfig.getSERVER_ADDR();
        this.SERVER_PORT = mongoConfig.getSERVER_PORT();
        this.USER_NAME = mongoConfig.getUSER_NAME();
        this.DB_NAME = mongoConfig.getDB_NAME();
        this.DB_PWD = mongoConfig.getDB_PWD();

        setup();
    }

    private static MongoDBUtil instance = null;

    public static synchronized MongoDBUtil getInstance(MongoConfig mongoConfig) {
        if (instance == null) {
            instance = new MongoDBUtil(mongoConfig);
        }
        return instance;
    }

    /**
     * establish the connection with mongoDB server
     */
    public void setup() {
        ServerAddress addr = new ServerAddress(SERVER_ADDR, SERVER_PORT);

        MongoCredential credential = MongoCredential.createScramSha1Credential(USER_NAME, DB_NAME,
                DB_PWD.toCharArray());

        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential);

        MongoClientOptions options = MongoClientOptions.builder().serverSelectionTimeout(1000).build();
        mongoClient = new MongoClient(addr, Arrays.asList(credential), options);

        mongoDatabase = mongoClient.getDatabase(DB_NAME);
    }

    /**
     * insert
     *
     * @param collectionName
     * @param obj
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public <T> void insert(String collectionName, T obj)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        synchronized (MongoDBUtil.class) {
            MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
            Document document = new Document();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                String key = field.getName();
                field.setAccessible(true);
                Object value = field.get(obj);
                document.append(key, value);
            }

            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            System.out.println(">>>>>>>>>>documents>>>>>>>>>>>>" + documents);
            collection.insertMany(documents);
        }
    }

    public void insert(Map map, String collectionName) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        Document document = new Document();
        document.putAll(map);
        System.out.println(">>>>>>>>>>document>>>>>>>>>>>>" + document);
        collection.insertOne(document);

    }

    /**
     * query
     *
     * @param collectionName
     * @param startTime
     * @param endTime
     * @param skip
     * @param limit
     * @return
     */
    public MongoResponse query(String collectionName, String startTime, String endTime, int skip, int limit) {
        MongoResponse result = new MongoResponse();
        List<Document> docs = new ArrayList<Document>();

        synchronized (MongoDBUtil.class) {
            MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
            result.setTotal(collection.count(new Document("dateTime", new Document("$gte", startTime))
                    .append("dateTime", new Document("$lt", endTime))));

            FindIterable<Document> findIterable = collection
                    .find(new Document("dateTime", new Document("$gte", startTime)).append("dateTime",
                            new Document("$lt", endTime)))
                    .skip(skip).limit(limit).sort(new Document("dateTime", -1));
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                Document doc = mongoCursor.next();
                System.out.println(doc);
                docs.add(doc);
            }
        }

        result.setDocs(docs);
        result.setCount(docs.size());
        return result;
    }

    /**
     * query
     *
     * @param collectionName
     * @param queryDoc
     * @param skip
     * @param limit
     * @return
     */
    public MongoResponse query(String collectionName, Document queryDoc, int skip, int limit, Document sortDoc) {
        MongoResponse result = new MongoResponse();
        List<Document> docs = new ArrayList<Document>();

        synchronized (MongoDBUtil.class) {
            MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
            result.setTotal(collection.count(queryDoc));

            FindIterable<Document> findIterable = collection.find(queryDoc).skip(skip).limit(limit).sort(sortDoc);
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                Document doc = mongoCursor.next();
                System.out.println(doc);
                docs.add(doc);
            }
        }

        result.setDocs(docs);
        result.setCount(docs.size());
        return result;
    }

    /**
     * get by map
     *
     * @param collectionName
     * @param map
     * @return
     */
    public String query(String collectionName, Map map) {
        MongoResponse result = new MongoResponse();
        List<Document> docs = new ArrayList<Document>();

        synchronized (MongoDBUtil.class) {
            MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

            FindIterable<Document> findIterable = collection.find(Filters.eq("serviceId", map.get("serviceId")));
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                Document doc = mongoCursor.next();
                System.out.println(doc);
                return (String) doc.get("serviceDetail");
            }
        }
        return "";
    }

    /**
     * delete by map
     *
     * @param collectionName
     * @param map
     * @return
     */
    public String delete(String collectionName, Map map) {
        MongoResponse result = new MongoResponse();
        List<Document> docs = new ArrayList<Document>();

        synchronized (MongoDBUtil.class) {
            MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
            collection.deleteOne(Filters.eq("serviceId", map.get("serviceId")));
        }
        return "";
    }
    /**
     * update by map
     *
     * @param collectionName
     * @param map
     * @return
     */
    public String update(String collectionName, Map map) {
        Document filter = new Document();
        filter.putAll(map);

        //注意update文档里要包含"$set"字段
        Document update = new Document();
        update.append("serviceId", map.get("serviceId"));

        synchronized (MongoDBUtil.class) {
            MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
            UpdateResult res =collection.replaceOne(update, filter);
            System.out.println("matched count = " + res.getMatchedCount());
        }
        return "";
    }

    /**
     * query by map
     *
     * @param collectionName
     * @param queryDoc
     * @param sortDoc
     * @return
     */
    public MongoResponse query(String collectionName, Document queryDoc, Document sortDoc) {
        MongoResponse result = new MongoResponse();
        List<Document> docs = new ArrayList<Document>();

        synchronized (MongoDBUtil.class) {
            MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
            result.setTotal(collection.count(queryDoc));

            FindIterable<Document> findIterable = collection.find(queryDoc).sort(sortDoc);
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                Document doc = mongoCursor.next();
                System.out.println(doc);
                docs.add(doc);
            }
        }

        result.setDocs(docs);
        result.setCount(docs.size());
        return result;
    }



    /**
     * @param collectionName
     */
    public void clear(String collectionName) {
        mongoDatabase.getCollection(collectionName).drop();
    }

    /**
     * close the connection with mongoDB server
     */
    public void destory() {
        // if (mongoClient != null)
        // mongoClient.close();
    }

}

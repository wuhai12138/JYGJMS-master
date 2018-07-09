package com.summ.utils.mongodb.model;

import org.bson.Document;

import java.util.List;

/**
 * 
 * @author johnson
 *
 */
public class MongoResponse {
	long total;
	long count;
	List<Document> docs;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<Document> getDocs() {
		return docs;
	}

	public void setDocs(List<Document> docs) {
		this.docs = docs;
	}

}

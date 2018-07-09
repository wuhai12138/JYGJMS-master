package com.summ.utils.mongodb.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author johnson
 *
 */
@Configuration
public class MongoConfig {
//	@Value("${mongo_addr}")
	public String SERVER_ADDR = "106.14.179.115";
//	@Value("${mongo_port}")
	public int SERVER_PORT = 27017;
//	@Value("${mongo_user}")
	public String USER_NAME = "root";
//	@Value("${mongo_db}")
	public String DB_NAME = "admin";
//	@Value("${mongo_pwd}")
	public String DB_PWD = "Ilikedh123";

	public MongoConfig() {
	}

	public MongoConfig(String SERVER_ADDR, int SERVER_PORT, String USER_NAME, String DB_NAME, String DB_PWD) {
		this.SERVER_ADDR = SERVER_ADDR;
		this.SERVER_PORT = SERVER_PORT;
		this.USER_NAME = USER_NAME;
		this.DB_NAME = DB_NAME;
		this.DB_PWD = DB_PWD;
	}

	public String getSERVER_ADDR() {
		return SERVER_ADDR;
	}

	public void setSERVER_ADDR(String sERVER_ADDR) {
		SERVER_ADDR = sERVER_ADDR;
	}

	public int getSERVER_PORT() {
		return SERVER_PORT;
	}

	public void setSERVER_PORT(int sERVER_PORT) {
		SERVER_PORT = sERVER_PORT;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}

	public String getDB_NAME() {
		return DB_NAME;
	}

	public void setDB_NAME(String dB_NAME) {
		DB_NAME = dB_NAME;
	}

	public String getDB_PWD() {
		return DB_PWD;
	}

	public void setDB_PWD(String dB_PWD) {
		DB_PWD = dB_PWD;
	}

}

package com.summ.utils.mongodb.query;


import com.summ.utils.mongodb.model.*;

/**
 * 
 * @author johnson
 *
 */
public class QueryFactory {

	/**
	 * factory method
	 * 
	 * @param type
	 * @param mongoConfig
	 * @return
	 */
	public static QueryUtil getInstance(QueryType type, MongoConfig mongoConfig) {
		switch (type) {
		case operate:
			return new QueryUtil(mongoConfig, OperateLog.class, OperateResponse.class, "operate_log");
		case debug:
			return new QueryUtil(mongoConfig, DebugLog.class, DebugResponse.class, "debug_log");
		case error:
			return new QueryUtil(mongoConfig, ErrorLog.class, ErrorResponse.class, "error_log");
		default:
			return null;
		}
	}

}

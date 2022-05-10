package com.mongo.crud.repository;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongo.crud.model.RegisteredDevices;

public interface RegisteredDevicesRepository extends MongoRepository<RegisteredDevices, Integer> {

	@Query(value = "{'_id' : ?0}")
	RegisteredDevices findItemByDeviceId(String deviceId);

	@Query(value = "{'_id' : ?0}", delete = true)
	public void deleteByDeviceId(String deviceId);
	
	@Aggregation(pipeline = {
			"{$match: { $or: [{ orgId: { $in: db.Organizations.aggregate([ " + 
			"{ $match : { _id: ?0 } },  { $project: {  _id: 1 } },  { $sort: { _id: 1 }}]).map(function(doc){ return doc._id})}}, " +
			"{ orgId: { $in: db.Organizations.aggregate([  { $match: { parentOrgId: \"Org1\" } }, " +
			"{ $project: {  _id: 1 } },  { $sort: { _id: 1 }}]).map(function(doc){ return doc._id}) } }] } }, " +
			"{ $project: { _id: 1, orgId: 1 } }, { $sort: { _id: 1 } }, { $limit: 100 }])"
			
	})
	AggregationResults<RegisteredDevices> getCustomerAccountsByOrgId(String orgId);
}

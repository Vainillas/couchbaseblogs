package ar.edu.unrn.couchbaseblogs.services;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import couchbaseblogspringboot.exceptions.BusinessException;
import java.util.function.Function;

public abstract class CouchBaseService {
    final Cluster cluster;
    final Bucket bucket;

    public CouchBaseService(Cluster couchBaseCluster) {
        this.cluster = couchBaseCluster;
        this.bucket = cluster.bucket("couchbase-blogs");
    }

    public <T> T executeOperation(Function<Collection, T> function, String collectionName) {
        try {
            Collection collection = bucket.collection(collectionName);
            return function.apply(collection);
        } catch (Exception e) {
            throw new BusinessException("Error executing operation", e);
        }
    }

    public <T> T executeOperation(Function<Collection, T> function, String collectionName, String bucketName) {
        try {
            Bucket bucket = cluster.bucket(bucketName);
            Collection collection = bucket.defaultCollection();
            return function.apply(collection);
        } catch (Exception e) {
            throw new BusinessException("Error executing operation", e);
        }
    }
}

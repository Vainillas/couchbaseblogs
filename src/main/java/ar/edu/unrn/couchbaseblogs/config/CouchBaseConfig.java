package ar.edu.unrn.couchbaseblogs.config;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.env.ClusterEnvironment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchBaseConfig extends AbstractCouchbaseConfiguration {

  private final ClusterEnvironment environment;


  @Override
  public String getConnectionString() {
    return "couchbase://127.0.0.1";
  }

  @Override
  public String getUserName() {
    return "Administrator";
  }

  @Override
  public String getPassword() {
    return "password";
  }

  @Override
  public String getBucketName() {
    return "couchbase-blogs";
  }
  public CouchBaseConfig(@Qualifier("couchbaseEnvironment") ClusterEnvironment environment) {
      this.environment = environment;
  }

  @Bean
  public Cluster couchBaseCluster(){
    return Cluster.connect(getConnectionString(),
            ClusterOptions.clusterOptions(getUserName(), getPassword()).environment(environment));
  }
}

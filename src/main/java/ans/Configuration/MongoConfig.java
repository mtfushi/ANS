package ans.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;


@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${ans.mongo.db-name}")
    private String dbName;

    @Value("${ans.mongo.uri}")
    private String mongoUri;

    @Bean
    @Override
    public MongoClient mongoClient() {
        MongoClientURI uri = new MongoClientURI(mongoUri);
        return new MongoClient(uri);
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() throws Exception{

        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }

    @Bean
    @Override
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Override
    public String getDatabaseName(){
        return dbName;
    }

}

package ans.Service;

import ans.Model.Zip;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionsService {

    private MongoTemplate mongoTemplate;
    private String collectionName = "zipsexample";
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    public PermissionsService(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    public List<Zip> getCity(String state){

      List<Document> document = mongoTemplate.find(Query.query(Criteria.where("state").is(state)), Document.class, collectionName);
      List<Zip> zip = document.stream().map(p -> objectMapper.convertValue(p, Zip.class)).collect(Collectors.toList());
      return zip;
    }

    public String addZip(Zip zip){

        Zip insertedZip = mongoTemplate.insert(zip, collectionName);
        return insertedZip.getId();
    }

}

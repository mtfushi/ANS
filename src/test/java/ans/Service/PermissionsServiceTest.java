package ans.Service;

import ans.Model.Zip;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class PermissionsServiceTest {

    private MongoTemplate mongoTemplate;
    private PermissionsService permissionsService;
    private Query query;
    private Document document;
    private String collectionName;
    private List<Document> documentList;
    private Zip zip;
    private Zip insertedZip;

    @Before
    public void BeforeTest(){
        mongoTemplate = mock(MongoTemplate.class);
        permissionsService = new PermissionsService(mongoTemplate);
        document = new Document();
        document.append("city", "CHESTER");
        documentList = new ArrayList<>();
        documentList.add(document);
        query = new Query();
        collectionName = "zipsexample";
        zip = new Zip();
        zip.setState("yeeet");
        insertedZip = new Zip();
        insertedZip.setId("id");
    }

    @Test
    public void Return_Field_Includes_City_Test_Success(){
        //make sure that the query runs correctly
        when(mongoTemplate.find(any(Query.class), eq(Document.class), eq(collectionName))).thenReturn(documentList);

        List<Zip> zipList = permissionsService.getCity("MA");

        Assert.assertEquals(zipList.get(0).getCity(), documentList.get(0).get("city"));

        ArgumentCaptor<Query> queryArgumentCaptor = ArgumentCaptor.forClass(Query.class);
        verify(mongoTemplate, times(1)).find((queryArgumentCaptor.capture()), any(), any());
        Query capturedQuery = queryArgumentCaptor.getValue();

        Assert.assertEquals("MA", capturedQuery.getQueryObject().get("state"));
    }

    @Test
    public void Validate_Post_New_Zip_Test_Success(){
        when(mongoTemplate.insert(zip, collectionName)).thenReturn(insertedZip);
        String returnedId = permissionsService.addZip(zip);

        Assert.assertEquals(returnedId, insertedZip.getId());

    }
}

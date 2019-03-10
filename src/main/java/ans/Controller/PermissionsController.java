package ans.Controller;

import ans.Model.Zip;
import ans.Service.PermissionsService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/permissions")
public class PermissionsController {

    @Autowired
    PermissionsService permissionsService;

//    @GetMapping()
//    public Callable<String> getCity(){
//        return () -> {
//            return permissionsService.getCity();
//        };
//    }

    @RequestMapping(value = "/{state}", method = RequestMethod.GET)
    public Callable<List<Zip>> getCity(@PathVariable("state") String state){
        return () -> {
            System.out.println("INSIDE");
            List<Zip> yeet = permissionsService.getCity(state);
            return yeet;
        };
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Callable<String> postCity( @RequestBody Zip zip){
        return () -> {
            return permissionsService.addZip(zip);
        };
    }


}

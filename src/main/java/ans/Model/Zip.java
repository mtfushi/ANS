package ans.Model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Zip {

    @Id
   @JsonProperty(value ="_id", access = JsonProperty.Access.READ_WRITE) //write only is only get back from the db and stay in the code. Not serialize into JSON to print out
    public String id;

    public String city;

    @JsonProperty(value = "loc", access = JsonProperty.Access.READ_WRITE)
    public ArrayList<Double> location;

    public int pop;
    public String state;

    public Zip(){
    }

//   @JsonGetter(value = "id")
//    public String getId(){
//        return id;
//    }
//
//    @JsonSetter(value = "_id")
//    public void setId(String id){
//        this.id = id;
//    }

    public Zip(String _id, String city, ArrayList<Double> loc, int pop, String state){
        this.id = _id;
        this.city = city;
        this.location = loc;
        this.pop = pop;
        this.state = state;
    }

}

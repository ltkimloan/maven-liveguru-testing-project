package testdata.jSonData.liveGuru;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import commons.GlobalConstants;

import java.io.File;
import java.util.List;
import java.util.Random;

public class UserInfor {
    public static UserInfor getUserInformation(int index){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            List<UserInfor> list = mapper.readValue(new File(GlobalConstants.DATA_TEST_PATH +"userInfor.json"), new TypeReference<List<UserInfor>>() {
            });
            return list.get(index);
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public String getEmailAddress() {
        return "tonydang" + getRandomNumber() +"@gmail.com";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password = "123456";
    }

    String emailAddress;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    @JsonProperty("password")
    String password;

    protected String getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(9999) +"";
    }
}

package testdata.jSonData.liveGuru;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import commons.GlobalConstants;

import java.io.File;
import java.util.List;

public class BillInformation {
    public static BillInformation getBillInformation(int index){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            List<BillInformation> list = mapper.readValue(new File(GlobalConstants.DATA_TEST_PATH + "billInformation.json"), new TypeReference<List<BillInformation>>() {
            });
            return list.get(index);
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }

    @JsonProperty("country")
    String country;
    @JsonProperty("state")
    String state;
    @JsonProperty("zipCode")
    String zipCode;
    @JsonProperty("telephone")
    String telephone;
    @JsonProperty("address")
    String address;


}

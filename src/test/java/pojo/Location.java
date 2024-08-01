package pojo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

@Data
public class Location {
    private String country;
    private String state;

    @SerializedName("state abbreviation")
    private String stateAbbreviation;

    @SerializedName("place name")
    private String placeName;

    @SerializedName("country abbreviation")
    private String countryAbbreviation;

    private List<Place> places;
}


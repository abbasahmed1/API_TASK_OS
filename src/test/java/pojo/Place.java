package pojo;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Place {
    @SerializedName("place name")
    private String placeName;
    private String longitude;

    @SerializedName("post code")
    private String postCode;
    private String latitude;
}

package pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PlacesItem{
	private String latitude;
	private String state;
	@SerializedName("state abbreviation")
	private String stateAbbreviation;
	@SerializedName("place name")
	private String placeName;
	private String longitude;
}
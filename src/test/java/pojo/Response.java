package pojo;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Response{
	private String country;
	private List<PlacesItem> places;
	@SerializedName("state abbreviation")
	private String countryAbbreviation;
	@SerializedName("post code")
	private String postCode;
}
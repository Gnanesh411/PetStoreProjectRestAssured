package api.endpoints;

//userendpoint.java

//Created for perform Create,Retrive,Update,Delete requests to the User API

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {

	//method created for getting URL's from properties file
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes");//Load properties file
		return routes;
	}
	
	
	public static Response createUser(User payload){
		
		String post_url = getURL().getString("post_url");
		Response res=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(post_url);
		
		return res;
		
	}
	
	public static Response readUser(String userName){
		
		String get_url = getURL().getString("get_url");
		Response res=given()
		.pathParam("username", userName)
		.get(get_url);
		
		return res;
	}
	
	public static Response updateUser(String userName,User payload){
		
		String update_url = getURL().getString("update_url");
		Response res=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload)
		.when()
		.put(update_url);
		
		return res;
		
	}
	
	public static Response deleteUser(String userName){
		
		String delete_url = getURL().getString("delete_url");
		Response res=given()
		.pathParam("username", userName)
		.delete(delete_url);
		
		return res;
	}
}

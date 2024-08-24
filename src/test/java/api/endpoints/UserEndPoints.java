package api.endpoints;

//userendpoint.java
//Created for perform Create,Retrive,Update,Delete requests to the User API

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createUser(User payload){
		
		Response res=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.post_url);
		
		return res;
		
	}
	
	public static Response readUser(String userName){
		
		Response res=given()
		.pathParam("username", userName)
		.get(Routes.get_url);
		
		return res;
	}
	
	public static Response updateUser(String userName,User payload){
		
		Response res=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload)
		.when()
		.put(Routes.update_url);
		
		return res;
		
	}
	
	public static Response deleteUser(String userName){
		
		Response res=given()
		.pathParam("username", userName)
		.delete(Routes.delete_url);
		
		return res;
	}
}

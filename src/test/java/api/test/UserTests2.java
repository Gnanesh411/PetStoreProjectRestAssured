package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;

	public Logger logger;// for logs

	@BeforeClass // before starting all test methods
	public void setupData() {

		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		// Logs
		logger = LogManager.getLogger(this.getClass());

		logger.debug("debugging.......");

	}

	@Test(priority = 1)
	public void testPostUser() {

		logger.info("*****Creating User*******");
		Response res = UserEndPoints2.createUser(userPayload);
		res.then().log().all();

		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("*****Created User*******");
	}

	@Test(priority = 2)
	public void testGetUserByName() {

		logger.info("*****Reading UserInfo*******");
		Response res = UserEndPoints2.readUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("***** User info is displayed *******");
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {

		logger.info("*****Updating User*******");
		// update by using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response res = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		res.then().log().body();

		Assert.assertEquals(res.getStatusCode(), 200);

		logger.info("*****User is Updated*******");

		// checking data after update

		Response resAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
		Assert.assertEquals(resAfterUpdate.getStatusCode(), 200);

	}

	@Test(priority = 4)
	void testDeleteUserByName() {

		logger.info("*****Deleing User*******");
		Response res = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("*****User Deleted*******");
	}

}

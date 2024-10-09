package com.unit_test_exercise;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unit_test_exercise.controllers.Controller;
import com.unit_test_exercise.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UnitTestExerciseApplicationTests {

	// Parameters
	@Autowired
	private Controller controller;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;



	@Test
	public void contextLoads() {
          assertThat(controller).isNotNull();
	}
// test to create a new user
	@Test
	public void postUserTest() throws Exception{
		// first cfrate a user and set the values
		User newUser = new User();
		newUser.setFullName("iresha fernando");
		newUser.setAge(23);
		newUser.setAddress("via san mandato 89834");
		// use mockmvc to perform a post request to the /user endpoint
		this.mockMvc.perform(post("/user/newuser")
				// set the content type to JSON the same way we do it in POSTMAN
				.contentType(MediaType.APPLICATION_JSON)
				// convert the user object to json
				.content(objectMapper.writeValueAsString(newUser)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.fullName").value("iresha fernando"))
				.andExpect(jsonPath("$.age").value(23));
	}

// test to read a new user
	@Test
	public void readUserTest() throws Exception{
		// assuming user with id 1 exists in the data base
		this.mockMvc.perform(get("/user/userinfo/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1));
	}

	// test to get list of users
	@Test
	public void updateUser() throws Exception{
		User updateUser = new User();
		updateUser.setFullName("madushan fernando");
		updateUser.setAge(27);
		updateUser.setAddress("via cercariolo 2574");
		this.mockMvc.perform(put("/user/datiuser/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(updateUser)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("madushan fernando"))
				.andExpect(jsonPath("$.age").value(27))
		        .andExpect(jsonPath("$.address").value("via cercariolo 2574"));

	}

	// test to delete user
	public void deleteUser() throws Exception{
		this.mockMvc.perform(delete("/user/1"))
				.andExpect(status().isOk());
	}
}

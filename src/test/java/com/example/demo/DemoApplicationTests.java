package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.user.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {
@Autowired
    private MockMvc mockMvc;

    @Test
	public void shouldCreateUser() throws Exception {
        UserDto userDetails = new UserDto(
            "name", 
            "username", 
            "email@email.com", 
            "+358408315293"
        );

        MockHttpServletRequestBuilder postCall = post("/users")
            .content(asJsonString(userDetails))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(postCall)
            .andDo(print()).andExpect(status().isOk());
	}

    @Test
	public void shouldFailCreateUserPhoneMissing() throws Exception {
        UserDto userDetails = new UserDto(
            "name", 
            "username", 
            "email@email.com", 
            null
        );

        MockHttpServletRequestBuilder postCall = post("/users")
            .content(asJsonString(userDetails))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(postCall)
            .andDo(print()).andExpect(status().isBadRequest());
	}

    @Test
	public void shouldFailCreateUserUsernameTooShort() throws Exception {
        UserDto userDetails = new UserDto(
            "name", 
            "a", 
            "email@email.com", 
            "+358408315293"
        );

        MockHttpServletRequestBuilder postCall = post("/users")
            .content(asJsonString(userDetails))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(postCall)
            .andDo(print()).andExpect(status().isBadRequest());
	}
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

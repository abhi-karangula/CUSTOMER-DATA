package com.pk.customerdata;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.pk.customerdata.bean.CustomerCriteria;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerDataControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@Test
	public void testFindAllCustomers() throws Exception {
		mvc.perform(post("/rest/cust/findAllCustomers", new CustomerCriteria())
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}
}

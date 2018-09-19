package com.productmicro.micro;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.productmicro.micro.controllers.productController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;




import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import com.productmicro.micro.controllers.productController;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(productController.class)
@RunWith(SpringRunner.class)

public class TestProducts  {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private productController service;





    @Test
    public void testEmployee() throws Exception {
        String input = "[{\"id\":1,\"productId\":\"prod\",\"name\":\"gagan\",\"brand\":\"apple\",\"categoryName\":\"Electics\",\"rating\":3,\"imageName\":\"image\",\"description\":\"myDescription\",\"price\":99,\"discount\":2}]";        // String input = "[{\"catId\":1,\"name\":\"Electronic\"}]";
//        String input = "";
        // Expect status 200 ok
        int expectStatus = 200;

        MvcResult result = mvc.perform(post("/products/add").content(input).contentType(MediaType.asMediaType(MediaType.APPLICATION_JSON_UTF8))).andExpect(status().isCreated()).andReturn();
        int status = result.getResponse().getStatus();

       // assertThat(HttpStatus.CREATED).isEqualTo(status);
;
    }
}

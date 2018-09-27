package com.myapp.card.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.card.model.CardDetails;
import com.myapp.card.service.CreditCardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.xml.transform.OutputKeys;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Biju Pillai
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = CreditCardController.class, secure = false)
@WebAppConfiguration
public class CreditCardControllerTest {

    @InjectMocks
    CreditCardController controller;

    @MockBean
    CreditCardService service;

    @Autowired
    WebApplicationContext context;


    private MockMvc mockMvc;

    @Mock
    private List<CardDetails> mockList;


    private static final String RESOURCE_LOCATION_PATTERN_POST = "http://localhost:8080/card/add/";
    private static final String RESOURCE_LOCATION_PATTERN_GET = "http://localhost:8080/card/list/";

    private static final String SampleJson = "{\"name\": \"visa\",\"cardNumber\": \"4242424242424242\",\"limit\": 0 }";
    private static final String SampleJsonworng = "{\"name\": \"visa\",\"cardNumber\": \"8424242424242\",\"limit\": 0 }";
    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
       mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }



    @Test
    public void when_ever_credit_card_enter_getting_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                RESOURCE_LOCATION_PATTERN_POST)
                .accept(MediaType.APPLICATION_JSON).content(SampleJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void whenEverCustomerEnterWrongCreditCard_shouldReturnFailed() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                RESOURCE_LOCATION_PATTERN_POST)
                .accept(MediaType.APPLICATION_JSON).content(SampleJsonworng)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

    }

    @Test
    public void whenEverGettingRequestForAllData_shouldReturnAllEntries() throws Exception {
        List<CardDetails> allCards = Arrays.asList(mockCard());
        Mockito.when(service.getAll()).thenReturn(allCards);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                RESOURCE_LOCATION_PATTERN_GET)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        mockMvc.perform(get(RESOURCE_LOCATION_PATTERN_GET)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
                //.andExpect(mvcResult -> );

    }

    private CardDetails mockCard(){
        CardDetails cardDetails = new CardDetails("visa","4242424242424242",0);
        return cardDetails;
    }

}

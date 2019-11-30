package com.lindeco;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.lendico.util.DateUtils;
import com.lendico.web.dto.RepaymentPlanDto;

public class PlanGeneratorControllerTest extends AbstractTest {
	String uri = "/plan-generator/monthlyRepayment";
	   @Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	   
	   /**
	    * Handle Correct Request to get Ok Request
	    * @throws Exception
	    */
	   @Test
	   public void generateRepayment() throws Exception {
	     
	      RepaymentPlanDto repaymentPlanDto= new RepaymentPlanDto(BigDecimal.valueOf(5000), BigDecimal.valueOf(5), 24, DateUtils.getDate("2018-01-01T00:00:01Z"));
	      String inputJson = super.mapToJson(repaymentPlanDto);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	   }
	   /*
	    * Handle varies requests with validation fields error
	    * @throws Exception
	    */
	   @Test
	   public void generateRepaymen_validationError() throws Exception {
	      //Handle  null or empty loadAmount
	      RepaymentPlanDto repaymentPlanDto= new RepaymentPlanDto(null, BigDecimal.valueOf(5), 24, DateUtils.getDate("2018-01-01T00:00:01Z"));
	      String inputJson = super.mapToJson(repaymentPlanDto);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(400, status);
	      
	      //Handle loadAmount should be greater than 1
	      repaymentPlanDto= new RepaymentPlanDto(BigDecimal.valueOf(0.5), BigDecimal.valueOf(5), 24, DateUtils.getDate("2018-01-01T00:00:01Z"));
	      inputJson = super.mapToJson(repaymentPlanDto);
	      mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      status = mvcResult.getResponse().getStatus();
	      assertEquals(400, status);
	      
	    //Handle nominalRate should be greater than 1
	      repaymentPlanDto= new RepaymentPlanDto(BigDecimal.valueOf(500), BigDecimal.valueOf(0.5), 24, DateUtils.getDate("2018-01-01T00:00:01Z"));
	      inputJson = super.mapToJson(repaymentPlanDto);
	      mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      status = mvcResult.getResponse().getStatus();
	      assertEquals(400, status);
	      
	    //Handle nominalRate not null or empty
	      repaymentPlanDto= new RepaymentPlanDto(BigDecimal.valueOf(500), null, 24, DateUtils.getDate("2018-01-01T00:00:01Z"));
	      inputJson = super.mapToJson(repaymentPlanDto);
	      mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      status = mvcResult.getResponse().getStatus();
	      assertEquals(400, status);
	      
	    //Handle duration not null or empty
	      repaymentPlanDto= new RepaymentPlanDto(BigDecimal.valueOf(500), BigDecimal.valueOf(5), null, DateUtils.getDate("2018-01-01T00:00:01Z"));
	      inputJson = super.mapToJson(repaymentPlanDto);
	      mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      status = mvcResult.getResponse().getStatus();
	      assertEquals(400, status);
	    
	      //Handle duration greater than 1
	      repaymentPlanDto= new RepaymentPlanDto(BigDecimal.valueOf(500), BigDecimal.valueOf(5), 0, DateUtils.getDate("2018-01-01T00:00:01Z"));
	      inputJson = super.mapToJson(repaymentPlanDto);
	      mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      status = mvcResult.getResponse().getStatus();
	      assertEquals(400, status);
	      
	      
	    //Handle date not null or empty
	      repaymentPlanDto= new RepaymentPlanDto(BigDecimal.valueOf(500), BigDecimal.valueOf(5), 24, null);
	      inputJson = super.mapToJson(repaymentPlanDto);
	      mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      status = mvcResult.getResponse().getStatus();
	      assertEquals(400, status);
	   }
}

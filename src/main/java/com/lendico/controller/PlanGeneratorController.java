package com.lendico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lendico.service.RepaymentPlanService;
import com.lendico.web.dto.MonthlyRepaymentDto;
import com.lendico.web.dto.RepaymentPlanDto;



import javax.validation.Valid;

/**
 * 
 * @author rehab.sayed
 * rest controller to handle the plan generator  for client to give him/her final repayment schedule
 *
 */

@RestController
@RequestMapping(value = "/plan-generator")
public class PlanGeneratorController {

	@Autowired
	private RepaymentPlanService repaymentPlanService;
	
	@PostMapping("/monthlyRepayment")
	@ResponseStatus(code = HttpStatus.OK)
	public  @ResponseBody List<MonthlyRepaymentDto> getRepaymentPlan (@Valid @RequestBody RepaymentPlanDto repaymentPlandDto)
	{
		return repaymentPlanService.getPaymentPlan(repaymentPlandDto);
	}
	
	
	
}

package com.lendico.service;

import java.util.List;

import com.lendico.web.dto.MonthlyRepaymentDto;
import com.lendico.web.dto.RepaymentPlanDto;

/**
 * 
 * @author rehab.sayed
 * interface for getting payment plan for clients
 *
 */
public interface RepaymentPlanService {
	 List<MonthlyRepaymentDto> getPaymentPlan(final RepaymentPlanDto repaymentPlandDto);
}

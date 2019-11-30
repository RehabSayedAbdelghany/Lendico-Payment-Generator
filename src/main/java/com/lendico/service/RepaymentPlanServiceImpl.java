package com.lendico.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lendico.util.DateUtils;
import com.lendico.web.dto.MonthlyRepaymentDto;
import com.lendico.web.dto.RepaymentPlanDto;
/**
 * 
 * @author rehab.sayed
 * Service for getting payment plan for clients
 *
 */
@Service
public class RepaymentPlanServiceImpl implements RepaymentPlanService{
	
	@Autowired
	private CalculationService calculationService;

	/**
	 * Getting a repayment plan for a sample request.
	 * 
	 * @param repaymentPlanDto
	 *            the RepaymentPlan request DTO that contains all the needed data for calculation like duration, nominalRate, loanamount ...
	 * @return The List monthly repayment plan
	 */
	@Override
	public List<MonthlyRepaymentDto> getPaymentPlan(RepaymentPlanDto repaymentPlanDto) {
		
		repaymentPlanDto.setLocalStartDate(DateUtils.getLocalDateTime(repaymentPlanDto.getStartDate()));
		
		final List<MonthlyRepaymentDto> monthlyRepaymentDtos =  new ArrayList<>();
		BigDecimal initialOutstandingPrincipal = repaymentPlanDto.getLoanAmount();
		
		// calculate the borrowerPaymentAmount once as it is fixed for the duration and use it in other calcuation
		BigDecimal borrowerPaymentAmount = calculationService.calculateBorrowerPaymentAmount(repaymentPlanDto.getDuration(),repaymentPlanDto.getNominalRate(),repaymentPlanDto.getLoanAmount());

		
		for (int monthIndex = 0; monthIndex < repaymentPlanDto.getDuration(); monthIndex++) {
			MonthlyRepaymentDto monthlyRepaymentDto = generateRepayment(repaymentPlanDto, initialOutstandingPrincipal, monthIndex,borrowerPaymentAmount);
			initialOutstandingPrincipal = monthlyRepaymentDto.getRemainingOutstandingPrincipal();
			monthlyRepaymentDto.setBorrowerPaymentAmount(borrowerPaymentAmount);
			monthlyRepaymentDtos.add(monthlyRepaymentDto);
		}
		
		return monthlyRepaymentDtos;
	}
	
	private  MonthlyRepaymentDto generateRepayment(RepaymentPlanDto repaymentPlanDto,
			BigDecimal initialOutstandingPrincipal,
			int monthIndex,BigDecimal borrowerPaymentAmount) {

		MonthlyRepaymentDto repayment = new MonthlyRepaymentDto();

		LocalDateTime date = repaymentPlanDto.getLocalStartDate().plusMonths(monthIndex);
		repayment.setDate(date);

		BigDecimal interest = calculationService.calculateInterest(repaymentPlanDto.getNominalRate(),
				initialOutstandingPrincipal);
		repayment.setInterest(interest);

		
		/* Principal. */
		BigDecimal principal = calculationService.calculatePrincipal(interest,borrowerPaymentAmount);
		/**
		 * if, calculated principal amount exceeds the initial outstanding principal amount, take initial outstanding principal amount instead 
		 */
		if (principal.compareTo(initialOutstandingPrincipal) > 0) {
			principal = initialOutstandingPrincipal;
		}
		repayment.setPrincipal(principal);
		
		
		/* Initial outstanding principal. */
		repayment.setInitialOutstandingPrincipal(initialOutstandingPrincipal);

		/* Remaining outstanding principal. */
		BigDecimal remainingOutstandingPrincipal = calculationService
				.calculateRemainingOutstandingPrincipal(initialOutstandingPrincipal, principal);
		repayment.setRemainingOutstandingPrincipal(
				remainingOutstandingPrincipal);

		return repayment;
	}

}

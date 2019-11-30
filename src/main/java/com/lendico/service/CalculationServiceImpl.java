package com.lendico.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
@Service
public class CalculationServiceImpl implements CalculationService{

	final BigDecimal DAYS_PER_MONTH = BigDecimal.valueOf(30);
	final BigDecimal DAYS_PER_YEAR = BigDecimal.valueOf(360);
	@Override
	public BigDecimal calculateInterest(BigDecimal nominalRate, BigDecimal initialOutstandingPrincipal) {
		BigDecimal interest = (nominalRate.multiply(DAYS_PER_MONTH).multiply(initialOutstandingPrincipal))
               .divide(DAYS_PER_YEAR, BigDecimal.ROUND_HALF_EVEN);
		
		return interest.divide(new BigDecimal(100)).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);
		
  	}
	/**
	 * Calculating the annuity amount = borrower payment amount
	 * 
	 * @param duration
	 *            which is the duration in months of the loan
	 * @param nominalRate
	 *            The nominal interest rate
	 * @param loanAmount
	 *            The amount of the loan
	 * @return  borrower payment amount
	 */
	@Override
	public BigDecimal calculateBorrowerPaymentAmount(final int duration,final BigDecimal nominalRate,final BigDecimal loanAmount) {

		//Divide the nominal rate by the number of months in a year and 100 to get it with percent. 

		double monthlyNomialRatePercent = nominalRate.doubleValue()/(12*100);
		//calculating the annuity based on  http://financeformulas.net/Annuity_Payment_Formula.html 
		double annuity = (loanAmount.doubleValue() * monthlyNomialRatePercent) /(1 - Math.pow(1 + monthlyNomialRatePercent, -duration));

		return BigDecimal.valueOf(annuity);
	}
	
	/**
	 * Calculating the Principal
	 * 
	 * @param annuity
	 * 			amount that the client pay fixed every installment
	 * @param interest
	 *            The amount of the loan
	 * @return  Principal
	 */
	@Override
	public BigDecimal calculatePrincipal(BigDecimal interest, BigDecimal annuity) {
		// Principal = Annuity - Interest 
		BigDecimal principal = annuity.subtract(interest);
		principal = principal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return principal;
	}
	
	/**
	 * Calculating the RemainingOutstandingPrincipal
	 * 
	 * @param principal
	 * 				
	 * @param initialOutstandingPrincipal
	 *            The amount of the loan
	 * @return  RemainingOutstandingPrincipal
	 */
	
	@Override
	public BigDecimal calculateRemainingOutstandingPrincipal(BigDecimal initialOutstandingPrincipal,
			BigDecimal principal) {

		/* Calculates the remaining outstanding principal. */
		BigDecimal remainingOutstandingPrincipal;
		remainingOutstandingPrincipal = initialOutstandingPrincipal
				.subtract(principal);
		return remainingOutstandingPrincipal;
	}

}

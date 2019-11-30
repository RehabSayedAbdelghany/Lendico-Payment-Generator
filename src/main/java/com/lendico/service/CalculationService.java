package com.lendico.service;

import java.math.BigDecimal;

public interface CalculationService {
	
	BigDecimal calculateInterest(final BigDecimal nominalRate,final BigDecimal initialOutstandingPrincipal);
	BigDecimal calculateBorrowerPaymentAmount(final int  duration,final BigDecimal nominalRate,final BigDecimal loanAmount);
	BigDecimal calculatePrincipal(BigDecimal interest, BigDecimal annuity);
	BigDecimal calculateRemainingOutstandingPrincipal(BigDecimal initialOutstandingPrincipal, BigDecimal principal);
}

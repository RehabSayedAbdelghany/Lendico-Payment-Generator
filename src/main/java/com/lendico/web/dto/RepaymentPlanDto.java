package com.lendico.web.dto;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDateTime;


import org.springframework.format.annotation.DateTimeFormat;
/**
 * 
 * @author rehab.sayed
 * the Repayment Plan DTO model  used as a request
 *
 */
public class RepaymentPlanDto {
	
	@NotNull(message = "Please provide a loanAmount")
	@DecimalMin("1.00")
	private BigDecimal  loanAmount;
	
	@NotNull(message = "Please provide a nominalRate")
	@DecimalMin("1.00")
    private BigDecimal  nominalRate;
	
	@NotNull(message = "Please provide a duration")
	@Min(1)
	private Integer duration;
	
	@NotNull(message = "Please provide a Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private Date  startDate;
	
	private LocalDateTime localStartDate;
	public RepaymentPlanDto() { }

    public RepaymentPlanDto(final BigDecimal loanAmount, final BigDecimal nominalRate, final Integer duration, final Date startDate) {
        this.loanAmount = loanAmount;
        this.nominalRate = nominalRate;
        this.duration = duration;
        this.startDate = startDate;
    }
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public BigDecimal getNominalRate() {
		return nominalRate;
	}
	public void setNominalRate(BigDecimal nominalRate) {
		this.nominalRate = nominalRate;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getLocalStartDate() {
		return localStartDate;
	}
	public void setLocalStartDate(LocalDateTime localStartDate) {
		this.localStartDate = localStartDate;
	}
	
}

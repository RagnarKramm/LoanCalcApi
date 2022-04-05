package com.example.loancalcapi;

public class LoanCalcBrain {

    private final int minLoanAmount;
    private final int maxLoanAmount;
    private final int minLoanPeriodInMonths;
    private final int maxLoanPeriodInMonths;

    public LoanCalcBrain(int minLoanAmount, int maxLoanAmount, int minLoanPeriodInMonths, int maxLoanPeriodInMonths){

        this.minLoanAmount = minLoanAmount;
        this.maxLoanAmount = maxLoanAmount;
        this.minLoanPeriodInMonths = minLoanPeriodInMonths;
        this.maxLoanPeriodInMonths = maxLoanPeriodInMonths;
    }

    public LoanRequest CalculateLoan(int creditModifier, LoanRequest loanRequest){
        int initialLoanAmount = loanRequest.getRequestedAmount();
        int initialLoanPeriod = loanRequest.getRequestedPeriod();
        CheckData(initialLoanAmount, initialLoanPeriod);
        loanRequest.setMessage("Approved");

        int newLoanAmount = GetMaximumLoanAmountForPeriod(creditModifier, initialLoanPeriod);

        if (newLoanAmount > minLoanAmount){
            loanRequest.setAvailableAmount(newLoanAmount);
            return loanRequest;
        }

        int newLoanPeriod = GetMinimumViableLoanPeriod(creditModifier, initialLoanAmount);

        if (IsWithinConstraints(initialLoanAmount, newLoanPeriod)) {
            loanRequest.setAvailablePeriod(newLoanPeriod);
            return loanRequest;
        }

        newLoanAmount = GetMaximumLoanAmountForPeriod(creditModifier, maxLoanPeriodInMonths);
        newLoanPeriod = GetMinimumViableLoanPeriod(creditModifier, newLoanAmount);

        if (IsWithinConstraints(newLoanAmount, newLoanPeriod)) {
            loanRequest.setAvailableAmount(newLoanAmount);
            loanRequest.setAvailablePeriod(newLoanPeriod);
            return loanRequest;
        }
        throw new LoanCalculationException("Impossible calculation outcome!");
    }

    private void CheckData(int initialLoanAmount, int initialLoanPeriod) {
        if (initialLoanAmount > maxLoanAmount) {
            throw new LoanCalculationException("Requested amount is too big! Max amount: " + maxLoanAmount);
        }
        if (initialLoanAmount < minLoanAmount){
            throw new LoanCalculationException("Requested amount is too small! Min amount: " + minLoanAmount);
        }
        if (initialLoanPeriod > maxLoanPeriodInMonths){
            throw new LoanCalculationException("Requested period is too long! Max period: " + maxLoanPeriodInMonths );
        }
        if (initialLoanPeriod < minLoanPeriodInMonths){
            throw new LoanCalculationException("Requested period is too short! Min period: " + minLoanPeriodInMonths);
        }
    }

    private int GetMaximumLoanAmountForPeriod(int creditModifier, int loanPeriod){
        int loanAmount = creditModifier * loanPeriod;
        return Math.min(loanAmount, maxLoanAmount);
    }

    private int GetMinimumViableLoanPeriod(float creditModifier, int loanAmount){
        return (int) Math.ceil(1.0f / (creditModifier / loanAmount));
    }

    private boolean IsWithinConstraints(int loanAmount, int loanPeriod){
        return !(maxLoanPeriodInMonths < loanPeriod || loanPeriod < minLoanPeriodInMonths ||
                        maxLoanAmount < loanAmount || loanAmount < minLoanAmount);
    }
}

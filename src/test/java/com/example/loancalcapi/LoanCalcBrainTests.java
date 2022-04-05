package com.example.loancalcapi;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class LoanCalcBrainTests {
    @Test
    public void testLoanCalcBrainRegular() {
        LoanCalcBrain brain = new LoanCalcBrain(2000, 10000, 12, 60);
        int creditModifier = 100;
        LoanRequest result = brain.CalculateLoan(creditModifier, new LoanRequest(2691, 31));

        assertEquals(2691, result.getRequestedAmount());
        assertEquals(31, result.getRequestedPeriod());
        assertEquals(31, result.getAvailablePeriod());
        assertEquals(3100, result.getAvailableAmount());
    }

    @Test
    public void testLoanCalcBrainGetLowerAmountThanRequested() {
        LoanCalcBrain brain = new LoanCalcBrain(2000, 10000, 12, 60);
        int creditModifier = 100;
        LoanRequest result = brain.CalculateLoan(creditModifier, new LoanRequest(2691, 22));

        assertEquals(2691, result.getRequestedAmount());
        assertEquals(22, result.getRequestedPeriod());
        assertEquals(22, result.getAvailablePeriod());
        assertEquals(2200, result.getAvailableAmount());
    }
    @Test
    public void testLoanCalcBrainGetLongerPeriodThanRequested() {
        LoanCalcBrain brain = new LoanCalcBrain(2000, 10000, 12, 60);
        int creditModifier = 100;
        LoanRequest result = brain.CalculateLoan(creditModifier, new LoanRequest(2691, 12));

        assertEquals(2691, result.getRequestedAmount());
        assertEquals(12, result.getRequestedPeriod());
        assertEquals(27, result.getAvailablePeriod());
        assertEquals(2691, result.getAvailableAmount());
    }
    @Test
    public void testLoanCalcBrainGetMinimumAmountAndLongerPeriodWhenAmountExceedsConstraints() {
        LoanCalcBrain brain = new LoanCalcBrain(2000, 10000, 12, 60);
        int creditModifier = 100;
        LoanRequest result = brain.CalculateLoan(creditModifier, new LoanRequest(6040, 12));

        assertEquals(6040, result.getRequestedAmount());
        assertEquals(12, result.getRequestedPeriod());
        assertEquals(60, result.getAvailablePeriod());
        assertEquals(6000, result.getAvailableAmount());
    }
    @Test
    public void testLoanCalcBrainThrowsExceptionWithBigCreditModifier() {
        LoanCalcBrain brain = new LoanCalcBrain(2000, 10000, 12, 60);
        int creditModifier = 70;
        LoanRequest result = brain.CalculateLoan(creditModifier, new LoanRequest(10000, 12));

        assertEquals(10000, result.getRequestedAmount());
        assertEquals(12, result.getRequestedPeriod());
        assertEquals(60, result.getAvailablePeriod());
        assertEquals(4200, result.getAvailableAmount());
    }
    @Test
    public void testLoanCalcBrainThrowsExceptionWithBigAmount() {
        LoanCalcBrain brain = new LoanCalcBrain(2000, 10000, 12, 60);
        int creditModifier = 100;

        assertThrows(LoanCalculationException.class, () -> brain.CalculateLoan(creditModifier, new LoanRequest(333333, 31)));
    }
    @Test
    public void testLoanCalcBrainThrowsExceptionWithSmallAmount() {
        LoanCalcBrain brain = new LoanCalcBrain(2000, 10000, 12, 60);
        int creditModifier = 100;

        assertThrows(LoanCalculationException.class, () -> brain.CalculateLoan(creditModifier, new LoanRequest(500, 30)));
    }
    @Test
    public void testLoanCalcBrainThrowsExceptionWithSmallPeriod() {
        LoanCalcBrain brain = new LoanCalcBrain(2000, 10000, 12, 60);
        int creditModifier = 100;

        assertThrows(LoanCalculationException.class, () -> brain.CalculateLoan(creditModifier, new LoanRequest(4000, 6)));
    }
    @Test
    public void testLoanCalcBrainThrowsExceptionWithBigPeriod() {
        LoanCalcBrain brain = new LoanCalcBrain(2000, 10000, 12, 60);
        int creditModifier = 100;

        assertThrows(LoanCalculationException.class, () -> brain.CalculateLoan(creditModifier, new LoanRequest(5912, 72)));
    }
}

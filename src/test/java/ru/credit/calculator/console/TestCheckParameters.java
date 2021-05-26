package ru.credit.calculator.console;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.credit.calculator.console.service.Listener;
import ru.credit.calculator.console.service.Logs;
import ru.credit.calculator.library.model.InitialParameters;
import ru.credit.calculator.library.service.ParsingXML;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.time.YearMonth;

public class TestCheckParameters {

    public InitialParameters expectedParameter = new InitialParameters();

    @Before
    public void addParameter() {
        expectedParameter.setModelCredit("authentic");
        expectedParameter.setLoanSum(new BigDecimal(120000));
        expectedParameter.setNumberOfPeriods(new BigDecimal(12));
        expectedParameter.setInterestRate(new BigDecimal("7.9"));
        expectedParameter.setDate(YearMonth.of(2020, 7));
    }

    @Test
    public void testModelCreditAuthentic() throws JAXBException {
        InitialParameters parameterActual = ParsingXML.unmarshalXML("src/test/resources/parameters/parameter-modelCredit-authentic.xml");
        boolean flagActual = Listener.checkParameters(parameterActual);
        Assert.assertFalse(flagActual);
        Assert.assertEquals(expectedParameter, parameterActual);
    }

    @Test
    public void testModelCreditDifferentiated() throws JAXBException {
        InitialParameters parameterActual = ParsingXML.unmarshalXML("src/test/resources/parameters/parameter-modelCredit-differentiated.xml");
        boolean flagActual = Listener.checkParameters(parameterActual);
        expectedParameter.setModelCredit("differentiated");
        Assert.assertFalse(flagActual);
        Assert.assertEquals(expectedParameter, parameterActual);
    }

    @Test
    public void testModelCreditNull() throws JAXBException {
        InitialParameters parameterActual = ParsingXML.unmarshalXML("src/test/resources/parameters/parameter-modelCredit-null.xml");
        boolean flagActual = Listener.checkParameters(parameterActual);
        Assert.assertTrue(flagActual);
        Assert.assertNull(parameterActual.getModelCredit());
        Assert.assertTrue(Logs.getLogs().get(0).contains("ModelCredit отсуствует"));
        Logs.clearLogs();
    }

    @Test
    public void testModelCreditError() throws JAXBException {
        InitialParameters parameterActual = ParsingXML.unmarshalXML("src/test/resources/parameters/parameter-modelCredit-error.xml");
        boolean flagActual = Listener.checkParameters(parameterActual);
        Assert.assertTrue(flagActual);
        Assert.assertTrue(Logs.getLogs().get(0).contains("ModelCredit error"));
        Logs.clearLogs();
    }

    @Test
    public void testLoanSumNull() throws JAXBException {
        InitialParameters parameterActual = ParsingXML.unmarshalXML("src/test/resources/parameters/parameter-loanSum-null.xml");
        boolean flagActual = Listener.checkParameters(parameterActual);
        Assert.assertTrue(flagActual);
        Assert.assertNull(parameterActual.getLoanSum());
        Assert.assertTrue(Logs.getLogs().get(0).contains("LoanSum отсуствует"));
        Logs.clearLogs();
    }

    @Test
    public void testLoanSumError() throws JAXBException {
        InitialParameters parameterActual = ParsingXML.unmarshalXML("src/test/resources/parameters/parameter-loanSum-error.xml");
        boolean flagActual = Listener.checkParameters(parameterActual);
        Assert.assertTrue(flagActual);
        Assert.assertTrue(Logs.getLogs().get(0).contains("LoanSum отсуствует"));
        Logs.clearLogs();
    }

    @Test
    public void testNumberOfPeriodsNull() throws JAXBException {
        InitialParameters parameterActual = ParsingXML.unmarshalXML("src/test/resources/parameters/parameter-numberOfPeriods-null.xml");
        boolean flagActual = Listener.checkParameters(parameterActual);
        Assert.assertTrue(flagActual);
        Assert.assertNull(parameterActual.getNumberOfPeriods());
        Assert.assertTrue(Logs.getLogs().get(0).contains("NumberOfPeriods отсуствует"));
        Logs.clearLogs();
    }

    @Test
    public void testNumberOfPeriodsError() throws JAXBException {
        InitialParameters parameterActual = ParsingXML.unmarshalXML("src/test/resources/parameters/parameter-numberOfPeriods-error.xml");
        boolean flagActual = Listener.checkParameters(parameterActual);
        Assert.assertTrue(flagActual);
        Assert.assertTrue(Logs.getLogs().get(0).contains("NumberOfPeriods отсуствует"));
        Logs.clearLogs();
    }

    @Test
    public void testInterestRateNull() throws JAXBException {
        InitialParameters parameterActual = ParsingXML.unmarshalXML("src/test/resources/parameters/parameter-interestRate-null.xml");
        boolean flagActual = Listener.checkParameters(parameterActual);
        Assert.assertTrue(flagActual);
        Assert.assertNull(parameterActual.getInterestRate());
        Assert.assertTrue(Logs.getLogs().get(0).contains("InterestRate отсуствует"));
        Logs.clearLogs();
    }

    @Test
    public void testInterestRateError() throws JAXBException {
        InitialParameters parameterActual = ParsingXML.unmarshalXML("src/test/resources/parameters/parameter-interestRate-error.xml");
        boolean flagActual = Listener.checkParameters(parameterActual);
        Assert.assertTrue(flagActual);
        Assert.assertTrue(Logs.getLogs().get(0).contains("InterestRate отсуствует"));
        Logs.clearLogs();
    }

    @Test
    public void testDateNull() throws JAXBException {
        InitialParameters parameterActual = ParsingXML.unmarshalXML("src/test/resources/parameters/parameter-date-null.xml");
        boolean flagActual = Listener.checkParameters(parameterActual);
        Assert.assertTrue(flagActual);
        Assert.assertNull(parameterActual.getDate());
        Assert.assertTrue(Logs.getLogs().get(0).contains("date format error"));
        Logs.clearLogs();
    }

    @Test
    public void testDateError() throws JAXBException {
        InitialParameters parameterActual = ParsingXML.unmarshalXML("src/test/resources/parameters/parameter-date-error.xml");
        boolean flagActual = Listener.checkParameters(parameterActual);
        Assert.assertTrue(flagActual);
        Assert.assertNull(parameterActual.getDate());
        Assert.assertTrue(Logs.getLogs().get(0).contains("date format error"));
        Logs.clearLogs();
    }



}

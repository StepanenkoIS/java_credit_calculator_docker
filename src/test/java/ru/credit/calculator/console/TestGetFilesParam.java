package ru.credit.calculator.console;

import org.junit.Assert;
import org.junit.Test;
import ru.credit.calculator.console.model.FileList;
import ru.credit.calculator.console.service.Listener;

import java.util.ArrayList;
import java.util.List;

public class TestGetFilesParam {

    @Test
    public void testGetFilesParamSize() {
        List<FileList> fileLists = Listener.getFilesParam("src/test/resources/parameters/");
        Assert.assertEquals(12, fileLists.size());
    }

    @Test
    public void testGetFilesParamNames() {
        List<FileList> fileLists = Listener.getFilesParam("src/test/resources/parameters/");
        List<String> nameFiles = new ArrayList<>();
        for (FileList file : fileLists) {
            nameFiles.add(file.getName());
        }

        Assert.assertTrue(nameFiles.contains("parameter-date-error.xml"));
        Assert.assertTrue(nameFiles.contains("parameter-date-null.xml"));
        Assert.assertTrue(nameFiles.contains("parameter-interestRate-error.xml"));
        Assert.assertTrue(nameFiles.contains("parameter-interestRate-null.xml"));
        Assert.assertTrue(nameFiles.contains("parameter-loanSum-error.xml"));
        Assert.assertTrue(nameFiles.contains("parameter-loanSum-null.xml"));
        Assert.assertTrue(nameFiles.contains("parameter-modelCredit-authentic.xml"));
        Assert.assertTrue(nameFiles.contains("parameter-modelCredit-differentiated.xml"));
        Assert.assertTrue(nameFiles.contains("parameter-modelCredit-error.xml"));
        Assert.assertTrue(nameFiles.contains("parameter-modelCredit-null.xml"));
        Assert.assertTrue(nameFiles.contains("parameter-numberOfPeriods-error.xml"));
        Assert.assertTrue(nameFiles.contains("parameter-numberOfPeriods-null.xml"));

    }


}


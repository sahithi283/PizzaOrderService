package com.sample.test.demo.utilities;

import com.sample.test.demo.Pages.interfaces.SampleRunnable;
import org.openqa.selenium.By;

import java.io.*;
import java.util.*;

public class ReadDataUtilityClass {
    private static final Map<String, By> map = new LinkedHashMap<>();
    private static final Map<String, SampleRunnable> locatorsMap = new LinkedHashMap<>();

    public static void setLocatorsMap() {
        locatorsMap.put("xpath", By::xpath);
        locatorsMap.put("ID", By::id);
        locatorsMap.put("css", By::cssSelector);
    }

    public static void readDataFromTextFile() {
        String line;
        try (
                FileReader fileReader = new FileReader(new File("src/test/resources/files/locators.txt"));
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                if (!(line.trim().isEmpty())) {
                    List<String> list = Arrays.asList((line.split("\\s+")));
                    map.put(list.get(0), (locatorsMap.get(list.get(1))).runner(list.get(2).replaceAll("\"", "")));
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static Map<String, By> getLocatorsMap() {
        setLocatorsMap();
        readDataFromTextFile();
        return map;
    }
}

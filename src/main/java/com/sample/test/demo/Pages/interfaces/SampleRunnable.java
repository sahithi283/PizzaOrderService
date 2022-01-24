package com.sample.test.demo.Pages.interfaces;

import org.openqa.selenium.By;

@FunctionalInterface
public interface SampleRunnable {
    By runner(String locator);
}

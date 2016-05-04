package com.zoha.test.integration.suite;

import com.zoha.test.integration.controller.rest.AuthenticationControllerTest;
import com.zoha.test.integration.controller.rest.ProtectedControllerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  AuthenticationControllerTest.class,
  ProtectedControllerTest.class
})
public class IntegrationTestSuite {

}

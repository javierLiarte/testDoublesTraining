/*
 * Copyright (c) 2006-2013 Rogério Liesenfeld
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.integration.logging;

import mockit.*;
import org.slf4j.*;

/**
 * A mock class containing mocks and stubs for the SLF4j API.
 * <p/>
 * When a test class is annotated as {@code @UsingMocksAndStubs(Slf4jMocks.class)}, all production code touched by the
 * tests in that class will receive mock {@code Logger} instances instead of real ones, when one of the factory methods
 * in class {@code org.slf4j.LoggerFactory} is called.
 * <p/>
 * <a href="http://jmockit.googlecode.com/svn/trunk/www/tutorial/UsingMocksAndStubs.html">Tutorial</a>
 */
public final class Slf4jMocks extends MockUp<LoggerFactory>
{
   private static final Logger MOCK_LOGGER = new MockUp<Logger>(){}.getMockInstance();

   private Slf4jMocks() {}

   @Mock public static ILoggerFactory getILoggerFactory() { return null; }

   /**
    * Returns a singleton mock {@code Logger} instance, whose methods do nothing.
    */
   @Mock public static Logger getLogger(String name) { return MOCK_LOGGER; }

   /**
    * Returns a singleton mock {@code Logger} instance, whose methods do nothing.
    */
   @Mock public static Logger getLogger(Class<?> clazz) { return MOCK_LOGGER; }
}

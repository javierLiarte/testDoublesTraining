/*
 * Copyright (c) 2006-2013 Rogério Liesenfeld
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package powermock.examples.simple;

import org.junit.*;

import mockit.*;

import static mockit.Deencapsulation.*;
import static org.junit.Assert.*;

/**
 * <a href="http://code.google.com/p/powermock/source/browse/trunk/examples/simple/src/test/java/demo/org/powermock/examples/simple/GreeterTest.java">PowerMock version</a>
 */
public final class Greeter_JMockit_Test
{
   @Test
   public void testGetMessage(@Mocked(stubOutClassInitialization = true) final SimpleConfig unused)
   {
      new Expectations() {{
         SimpleConfig.getGreeting(); result = "Hi";
         SimpleConfig.getTarget(); result = "All";
      }};

      assertEquals("Hi All", invoke(Greeter.class, "getMessage"));
      assertFalse(SimpleConfig.wasInitialized);
   }

   @Test
   public void testRun(@Mocked final Logger logger)
   {
      new Expectations() {{
         new Logger();
         logger.log("Hello"); times = 10;
      }};

      invoke(new Greeter(), "run", 10, "Hello");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testRunWhenLoggerThrowsUnexpectedRuntimeException(@Mocked Logger mock)
   {
      new Expectations() {{
         new Logger(); result = new IllegalArgumentException("Unexpected exception");
      }};

      invoke(new Greeter(), "run", 10, "Hello");
   }
}

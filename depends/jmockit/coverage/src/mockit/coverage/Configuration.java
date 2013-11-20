/*
 * Copyright (c) 2006-2013 Rogério Liesenfeld
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.coverage;

public final class Configuration
{
   private static final String COVERAGE_PREFIX1 = "jmockit-coverage-";
   private static final String COVERAGE_PREFIX2 = "coverage-";

   public static String getProperty(String nameSuffix)
   {
      return getProperty(nameSuffix, null);
   }

   public static String getProperty(String nameSuffix, String defaultValue)
   {
      String property = System.getProperty(COVERAGE_PREFIX1 + nameSuffix);

      if (property != null) {
         return property;
      }

      return System.getProperty(COVERAGE_PREFIX2 + nameSuffix, defaultValue);
   }

   public static void setProperty(String name, String value)
   {
      String prefixToUse = COVERAGE_PREFIX1;

      if (System.getProperty(prefixToUse + name) == null) {
         prefixToUse = COVERAGE_PREFIX2;
      }

      System.setProperty(prefixToUse + name, value);
   }
}

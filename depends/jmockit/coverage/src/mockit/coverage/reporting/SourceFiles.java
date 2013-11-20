/*
 * Copyright (c) 2006-2013 Rogério Liesenfeld
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.coverage.reporting;

import java.io.*;
import java.util.*;

final class SourceFiles
{
   private final List<File> srcDirs = new ArrayList<File>();

   List<File> buildListOfSourceDirectories(String[] sourceDirs)
   {
      if (sourceDirs.length > 0) {
         buildListWithSpecifiedDirectories(sourceDirs);
      }
      else {
         buildListWithAllSrcSubDirectories();
      }

      return srcDirs;
   }

   private void buildListWithSpecifiedDirectories(String[] dirs)
   {
      for (String dir : dirs) {
         File srcDir = new File(dir);

         if (srcDir.isDirectory()) {
            srcDirs.add(srcDir);
         }
      }

      if (srcDirs.isEmpty()) {
         throw new IllegalStateException("None of the specified source directories exist");
      }
   }

   private void buildListWithAllSrcSubDirectories()
   {
      String curDirName = new File(System.getProperty("user.dir")).getName();
      addSrcSubDirs(new File("../" + curDirName));
   }

   private void addSrcSubDirs(File dir)
   {
      File[] files = dir.listFiles();
      if (files == null) return;

      for (File subDir : files) {
         if (subDir.isDirectory()) {
            String subDirName = subDir.getName();

            if (!"test generated-sources".contains(subDirName)) {
               if ("src".equals(subDirName)) {
                  srcDirs.add(subDir);
               }
               else {
                  addSrcSubDirs(subDir);
               }
            }
         }
      }
   }
}

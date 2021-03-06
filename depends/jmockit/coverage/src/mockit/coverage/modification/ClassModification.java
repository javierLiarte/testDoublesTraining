/*
 * Copyright (c) 2006-2013 Rogério Liesenfeld
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.coverage.modification;

import java.lang.instrument.*;
import java.security.*;
import java.util.*;

import mockit.coverage.standalone.*;
import mockit.external.asm4.*;

public final class ClassModification
{
   final Set<String> modifiedClasses;
   final Set<ProtectionDomain> protectionDomains;
   private final ClassSelection classSelection;

   public ClassModification()
   {
      modifiedClasses = new HashSet<String>();
      protectionDomains = new HashSet<ProtectionDomain>();
      classSelection = new ClassSelection();
      redefineClassesAlreadyLoadedForCoverage();
   }

   private void redefineClassesAlreadyLoadedForCoverage()
   {
      Class<?>[] loadedClasses = Startup.instrumentation().getAllLoadedClasses();

      for (Class<?> loadedClass : loadedClasses) {
         if (
            loadedClass.getClassLoader() != null && !loadedClass.isAnnotation() && !loadedClass.isSynthetic() &&
            isToBeConsideredForCoverage(loadedClass.getName(), loadedClass.getProtectionDomain())
         ) {
            redefineClassForCoverage(loadedClass);
         }
      }
   }

   private void redefineClassForCoverage(Class<?> loadedClass)
   {
      byte[] modifiedClassfile = readAndModifyClassForCoverage(loadedClass);

      if (modifiedClassfile != null) {
         redefineClassForCoverage(loadedClass, modifiedClassfile);
         registerModifiedClass(loadedClass.getName(), loadedClass.getProtectionDomain());
      }
   }

   private void registerModifiedClass(String className, ProtectionDomain pd)
   {
      modifiedClasses.add(className);

      if (pd.getClassLoader() != null && pd.getCodeSource() != null && pd.getCodeSource().getLocation() != null) {
         protectionDomains.add(pd);
      }
   }

   private byte[] readAndModifyClassForCoverage(Class<?> aClass)
   {
      try {
         return modifyClassForCoverage(aClass);
      }
      catch (VisitInterruptedException ignore) {
         // Ignore the class if the modification was refused for some reason.
      }
      catch (RuntimeException e) {
         e.printStackTrace();
      }
      catch (AssertionError e) {
         e.printStackTrace();
      }

      return null;
   }

   private byte[] modifyClassForCoverage(Class<?> aClass)
   {
      String className = aClass.getName();
      byte[] modifiedBytecode = CoverageModifier.recoverModifiedByteCodeIfAvailable(className);

      if (modifiedBytecode != null) {
         return modifiedBytecode;
      }

      ClassReader cr = CoverageModifier.createClassReader(aClass);

      return cr == null ? null : modifyClassForCoverage(cr);
   }

   private byte[] modifyClassForCoverage(ClassReader cr)
   {
      CoverageModifier modifier = new CoverageModifier(cr);
      cr.accept(modifier, 0);
      return modifier.toByteArray();
   }

   private void redefineClassForCoverage(Class<?> loadedClass, byte[] modifiedClassfile)
   {
      ClassDefinition[] classDefs = {new ClassDefinition(loadedClass, modifiedClassfile)};

      try {
         Startup.instrumentation().redefineClasses(classDefs);
      }
      catch (ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
      catch (UnmodifiableClassException e) {
         throw new RuntimeException(e);
      }
   }

   boolean isToBeConsideredForCoverage(String className, ProtectionDomain protectionDomain)
   {
      return
         protectionDomain != null && !modifiedClasses.contains(className) &&
         classSelection.isSelected(className, protectionDomain);
   }

   public byte[] modifyClass(String className, ProtectionDomain protectionDomain, byte[] originalClassfile)
   {
      boolean modifyClassForCoverage = isToBeConsideredForCoverage(className, protectionDomain);

      if (modifyClassForCoverage) {
         try {
            byte[] modifiedClassfile = modifyClassForCoverage(className, originalClassfile);
            registerModifiedClass(className, protectionDomain);
            return modifiedClassfile;
         }
         catch (VisitInterruptedException ignore) {
            // Ignore the class if the modification was refused for some reason.
         }
         catch (RuntimeException e) {
            e.printStackTrace();
         }
         catch (AssertionError e) {
            e.printStackTrace();
         }
      }

      return null;
   }

   private byte[] modifyClassForCoverage(String className, byte[] classBytecode)
   {
      byte[] modifiedBytecode = CoverageModifier.recoverModifiedByteCodeIfAvailable(className);

      if (modifiedBytecode != null) {
         return modifiedBytecode;
      }

      ClassReader cr = new ClassReader(classBytecode);
      return modifyClassForCoverage(cr);
   }
}

/*
 * Copyright 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.lang.reflect.*;
public class Main {
  public static void main(String[] args) {
    Class<?> c = null;
    try {
      c = Class.forName("ConcreteClass");
    } catch (Exception e) {
      System.out.println("Could not load class");
      e.printStackTrace();
      return;
    }
    try {
      Method m = c.getMethod("runReal");
      System.out.println((String)m.invoke(c.newInstance(), new Object[0]));
    } catch (Exception e) {
      System.out.println("Unknown exception occurred");
      e.printStackTrace();
    }
    try {
      Method m = c.getMethod("runConflict");
      try {
        System.out.println((String)m.invoke(c.newInstance(), new Object[0]));
      } catch (InvocationTargetException e) {
        throw e.getCause();
      }
    } catch (AbstractMethodError e) {
      System.out.println("Unexpected AME caught");
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.out.println("Unexpected NSME caught");
      e.printStackTrace();
    } catch (IncompatibleClassChangeError e) {
      System.out.println("Expected ICCE caught");
    } catch (Throwable e) {
      System.out.println("Unknown exception caught!");
      e.printStackTrace();
    }
  }
}

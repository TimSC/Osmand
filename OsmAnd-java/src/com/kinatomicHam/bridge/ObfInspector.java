/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.7
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.kinatomicWsus.bridge;

public class ObfInspector {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected ObfInspector(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ObfInspector obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        CoreOsmAndJNI.delete_ObfInspector(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public static int inspector(StringVector argv) {
    return CoreOsmAndJNI.ObfInspector_inspector(StringVector.getCPtr(argv), argv);
  }

  public ObfInspector() {
    this(CoreOsmAndJNI.new_ObfInspector(), true);
  }

}

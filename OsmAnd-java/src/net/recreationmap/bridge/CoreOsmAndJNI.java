/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.7
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.kinatomicHamp.bridge;

public class CoreOsmAndJNI {
  public final static native long new_StringVector__SWIG_0();
  public final static native long new_StringVector__SWIG_1(long jarg1);
  public final static native long StringVector_size(long jarg1, StringVector jarg1_);
  public final static native long StringVector_capacity(long jarg1, StringVector jarg1_);
  public final static native void StringVector_reserve(long jarg1, StringVector jarg1_, long jarg2);
  public final static native boolean StringVector_isEmpty(long jarg1, StringVector jarg1_);
  public final static native void StringVector_clear(long jarg1, StringVector jarg1_);
  public final static native void StringVector_add(long jarg1, StringVector jarg1_, String jarg2);
  public final static native String StringVector_get(long jarg1, StringVector jarg1_, int jarg2);
  public final static native void StringVector_set(long jarg1, StringVector jarg1_, int jarg2, String jarg3);
  public final static native void delete_StringVector(long jarg1);
  public final static native int ObfInspector_inspector(long jarg1, StringVector jarg1_);
  public final static native long new_ObfInspector();
  public final static native void delete_ObfInspector(long jarg1);
  public final static native int get31TileNumberX(double jarg1);
  public final static native int get31TileNumberY(double jarg1);
  public final static native double get31LongitudeX(int jarg1);
  public final static native double get31LatitudeY(int jarg1);
  public final static native double getTileNumberX(float jarg1, double jarg2);
  public final static native double getTileNumberY(float jarg1, double jarg2);
  public final static native double checkLatitude(double jarg1);
  public final static native double checkLongitude(double jarg1);
  public final static native double getPowZoom(float jarg1);
  public final static native double getLongitudeFromTile(float jarg1, double jarg2);
  public final static native double getLatitudeFromTile(float jarg1, double jarg2);
}

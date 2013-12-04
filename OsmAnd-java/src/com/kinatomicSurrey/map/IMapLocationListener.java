package com.kinatomicSurrey.map;

public interface IMapLocationListener {
	void locationChanged(double newLatitude, double newLongitude, Object source);
}

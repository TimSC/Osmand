package com.kinatomicHam.plus.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.kinatomicHam.NativeLibrary;
import com.kinatomicHam.ResultMatcher;
import com.kinatomicHam.data.Amenity;
import com.kinatomicHam.plus.PoiFilter;
import com.kinatomicHam.plus.TargetPointsHelper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

public interface InternalOsmAndAPI {

	public XmlSerializer newSerializer();

	public XmlPullParser newPullParser();
	
	public String getPackageName();
	
	public String getVersionName();
	
	public int getVersionCode();

	public InputStream openAsset(String name) throws IOException;
	
	public NativeLibrary getNativeLibrary();

	public boolean accessibilityEnabled();
	
	public boolean accessibilityExtensions();
	
	public List<Amenity> searchAmenities(PoiFilter filter,
			double topLatitude, double leftLongitude, double bottomLatitude, double rightLongitude,
			double lat, double lon, ResultMatcher<Amenity> matcher);
	
	public List<Amenity> searchAmenitiesByName(String searchQuery,
			double topLatitude, double leftLongitude, double bottomLatitude, double rightLongitude, 
			double lat, double lon, ResultMatcher<Amenity> matcher);

	public String getDeviceName();

	public String getBrandName();

	public String getModelName();
	
	public TargetPointsHelper getTargetPointsHelper();
	
	public boolean isNavigationServiceStarted();
	
	public boolean isNavigationServiceStartedForNavigation();
	
	public void startNavigationService(boolean forNavigation);
	
	public void stopNavigationService();
	
	
}
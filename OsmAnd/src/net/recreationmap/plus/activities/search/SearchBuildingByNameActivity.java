package com.kinatomicHamp.plus.activities.search;

import java.util.Comparator;
import java.util.List;

import com.kinatomicHamp.ResultMatcher;
import com.kinatomicHamp.data.Building;
import com.kinatomicHamp.data.City;
import com.kinatomicHamp.data.LatLon;
import com.kinatomicHamp.data.Street;
import com.kinatomicHamp.plus.OsmandApplication;
import com.kinatomicHamp.plus.R;
import com.kinatomicHamp.plus.resources.RegionAddressRepository;
import com.kinatomicHamp.util.Algorithms;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

public class SearchBuildingByNameActivity extends SearchByNameAbstractActivity<Building> {
	private RegionAddressRepository region;
	private City city;
	private Street street;
	
	@Override
	protected Comparator<? super Building> createComparator() {
		return new Comparator<Building>() {
			@Override
			public int compare(Building o1, Building o2) {
				int i1 = Algorithms.extractFirstIntegerNumber(o1.getName());
				int i2 = Algorithms.extractFirstIntegerNumber(o2.getName());
				return i1 - i2;
			}
		};
	}
	
	@Override
	public AsyncTask<Object, ?, ?> getInitializeTask() {
		return new AsyncTask<Object, Void, List<Building>>(){
			@Override
			protected void onPostExecute(List<Building> result) {
				setLabelText(R.string.incremental_search_building);
				progress.setVisibility(View.INVISIBLE);
				finishInitializing(result);
				if (result == null || result.isEmpty()) {
					Toast.makeText(SearchBuildingByNameActivity.this, 
							R.string.no_buildings_found, Toast.LENGTH_LONG).show();
				}
			}
			
			@Override
			protected void onPreExecute() {
				setLabelText(R.string.loading_streets_buildings);
				progress.setVisibility(View.VISIBLE);
			}
			@Override
			protected List<Building> doInBackground(Object... params) {
				region = ((OsmandApplication)getApplication()).getResourceManager().getRegionRepository(settings.getLastSearchedRegion());
				if(region != null){
					city = region.getCityById(settings.getLastSearchedCity(), settings.getLastSearchedCityName());
					street = region.getStreetByName(city, settings.getLastSearchedStreet());
				}
				if(street != null){
					// preload here to avoid concurrent modification
					region.preloadBuildings(street, new ResultMatcher<Building>() {
						@Override
						public boolean isCancelled() {
							return false;
						}

						@Override
						public boolean publish(Building object) {
							addObjectToInitialList(object);
							return true;
						}
					});
					return street.getBuildings();
				}
				return null;
			}
		};
	}
	
	
	@Override
	public String getText(Building obj) {
		if(obj.getInterpolationInterval() > 0 || obj.getInterpolationType() != null){
			String hno = getCurrentFilter();
			if(hno.length() > 0 && obj.belongsToInterpolation(hno)) {
				return hno + " [" + obj.getName(region.useEnglishNames())+"]";
			}
		}
		return obj.getName(region.useEnglishNames());
	}
	
	@Override
	public String getShortText(Building obj) {
		if(obj.getInterpolationInterval() > 0 || obj.getInterpolationType() != null){
			return "";
		}
		return super.getShortText(obj);
	}
	
	
	@Override
	public void itemSelected(Building obj) {
		String text = getText(obj);
		String hno = getCurrentFilter();
		LatLon loc = obj.getLocation();
		float interpolation = obj.interpolation(hno);
		if(interpolation >= 0) {
			loc = obj.getLocation(interpolation);
			text = hno;
		}
		settings.setLastSearchedBuilding(text, loc);
		finish();
		
	}
	
	@Override
	public boolean filterObject(Building obj, String filter){
		if(super.filterObject(obj, filter)){
			return true;
		}
		if(obj.belongsToInterpolation(filter)){
			return true;
		}
		return false;
		
	}



}

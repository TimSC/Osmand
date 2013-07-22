package com.kinatomicHamp.plus.routing;

import java.util.List;

import com.kinatomicHamp.Location;
import com.kinatomicHamp.data.LatLon;
import com.kinatomicHamp.plus.ApplicationMode;
import com.kinatomicHamp.plus.ClientContext;
import com.kinatomicHamp.plus.routing.RouteProvider.GPXRouteParams;
import com.kinatomicHamp.plus.routing.RouteProvider.RouteService;
import com.kinatomicHamp.router.RouteCalculationProgress;

public class RouteCalculationParams {

	public Location start;
	public LatLon end;
	public List<LatLon> intermediates;
	
	public ClientContext ctx;
	public ApplicationMode mode;
	public RouteService type;
	public GPXRouteParams gpxRoute;
	public RouteCalculationResult previousToRecalculate;
	public boolean fast;
	public boolean optimal;
	public boolean leftSide;
	public RouteCalculationProgress calculationProgress;
	public boolean preciseRouting;
}

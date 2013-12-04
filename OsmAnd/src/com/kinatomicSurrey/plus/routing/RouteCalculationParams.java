package com.kinatomicSurrey.plus.routing;

import java.util.List;

import com.kinatomicSurrey.Location;
import com.kinatomicSurrey.data.LatLon;
import com.kinatomicSurrey.plus.ApplicationMode;
import com.kinatomicSurrey.plus.ClientContext;
import com.kinatomicSurrey.plus.routing.RouteProvider.GPXRouteParams;
import com.kinatomicSurrey.plus.routing.RouteProvider.RouteService;
import com.kinatomicSurrey.router.RouteCalculationProgress;

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

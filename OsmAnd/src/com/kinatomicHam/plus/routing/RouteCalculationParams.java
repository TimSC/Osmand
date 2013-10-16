package com.kinatomicHam.plus.routing;

import java.util.List;

import com.kinatomicHam.Location;
import com.kinatomicHam.data.LatLon;
import com.kinatomicHam.plus.ApplicationMode;
import com.kinatomicHam.plus.ClientContext;
import com.kinatomicHam.plus.routing.RouteProvider.GPXRouteParams;
import com.kinatomicHam.plus.routing.RouteProvider.RouteService;
import com.kinatomicHam.router.RouteCalculationProgress;

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

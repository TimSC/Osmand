package com.kinatomicEsus.plus.routing;

import java.util.List;

import com.kinatomicEsus.Location;
import com.kinatomicEsus.data.LatLon;
import com.kinatomicEsus.plus.ApplicationMode;
import com.kinatomicEsus.plus.ClientContext;
import com.kinatomicEsus.plus.routing.RouteProvider.GPXRouteParams;
import com.kinatomicEsus.plus.routing.RouteProvider.RouteService;
import com.kinatomicEsus.router.RouteCalculationProgress;

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

package com.kinatomicWsus.plus.routing;

import java.util.List;

import com.kinatomicWsus.Location;
import com.kinatomicWsus.data.LatLon;
import com.kinatomicWsus.plus.ApplicationMode;
import com.kinatomicWsus.plus.ClientContext;
import com.kinatomicWsus.plus.routing.RouteProvider.GPXRouteParams;
import com.kinatomicWsus.plus.routing.RouteProvider.RouteService;
import com.kinatomicWsus.router.RouteCalculationProgress;

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

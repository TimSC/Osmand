package net.recreationmap.plus.routing;

import java.util.List;

import net.recreationmap.Location;
import net.recreationmap.data.LatLon;
import net.recreationmap.plus.ApplicationMode;
import net.recreationmap.plus.ClientContext;
import net.recreationmap.plus.routing.RouteProvider.GPXRouteParams;
import net.recreationmap.plus.routing.RouteProvider.RouteService;
import net.recreationmap.router.RouteCalculationProgress;

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

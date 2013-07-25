package com.kinatomicHam.plus;

import java.io.File;

import com.kinatomicHam.Location;
import com.kinatomicHam.plus.api.ExternalServiceAPI;
import com.kinatomicHam.plus.api.InternalOsmAndAPI;
import com.kinatomicHam.plus.api.InternalToDoAPI;
import com.kinatomicHam.plus.api.SQLiteAPI;
import com.kinatomicHam.plus.api.SettingsAPI;
import com.kinatomicHam.plus.render.RendererRegistry;
import com.kinatomicHam.plus.routing.RoutingHelper;


/*
 * In Android version ClientContext should be cast to Android.Context for backward compatibility
 */
public interface ClientContext {
	
	public String getString(int resId, Object... args);
	
	public File getAppPath(String extend);
	
	public void showShortToastMessage(int msgId, Object... args);
	
	public void showToastMessage(int msgId, Object... args);
	
	public void showToastMessage(String msg);
	
	public RendererRegistry getRendererRegistry();

	public OsmandSettings getSettings();
	
	public SettingsAPI getSettingsAPI();
	
	public ExternalServiceAPI getExternalServiceAPI();
	
	public InternalToDoAPI getTodoAPI();
	
	public InternalOsmAndAPI getInternalAPI();
	
	public SQLiteAPI getSQLiteAPI();
	
	// public RendererAPI getRendererAPI();
	
	public void runInUIThread(Runnable run);

	public void runInUIThread(Runnable run, long delay);
	
	public RoutingHelper getRoutingHelper();
	
	public Location getLastKnownLocation();

}

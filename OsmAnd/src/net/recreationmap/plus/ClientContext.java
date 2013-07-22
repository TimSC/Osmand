package com.kinatomicHamp.plus;

import java.io.File;

import com.kinatomicHamp.Location;
import com.kinatomicHamp.plus.api.ExternalServiceAPI;
import com.kinatomicHamp.plus.api.InternalOsmAndAPI;
import com.kinatomicHamp.plus.api.InternalToDoAPI;
import com.kinatomicHamp.plus.api.SQLiteAPI;
import com.kinatomicHamp.plus.api.SettingsAPI;
import com.kinatomicHamp.plus.render.RendererRegistry;
import com.kinatomicHamp.plus.routing.RoutingHelper;


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

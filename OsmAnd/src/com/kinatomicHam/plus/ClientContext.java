package com.kinatomicWsus.plus;

import java.io.File;

import com.kinatomicWsus.Location;
import com.kinatomicWsus.plus.api.ExternalServiceAPI;
import com.kinatomicWsus.plus.api.InternalOsmAndAPI;
import com.kinatomicWsus.plus.api.InternalToDoAPI;
import com.kinatomicWsus.plus.api.SQLiteAPI;
import com.kinatomicWsus.plus.api.SettingsAPI;
import com.kinatomicWsus.plus.render.RendererRegistry;
import com.kinatomicWsus.plus.routing.RoutingHelper;


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

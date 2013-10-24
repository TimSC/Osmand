package com.kinatomicEsus.plus.api;

import com.kinatomicEsus.plus.ClientContext;

public interface ExternalServiceAPI {
	
	public boolean isWifiConnected();
	
	public boolean isInternetConnected();
	

	public boolean isLightSensorEnabled();
	
	public String getExternalStorageDirectory();
	
	public AudioFocusHelper getAudioFocuseHelper();
	
	public interface AudioFocusHelper {
		
		public boolean requestFocus(ClientContext context, int streamType);
		
		public boolean abandonFocus(ClientContext context, int streamType);
	}
}

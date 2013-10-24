package com.kinatomicHam.plus.activities;

import java.io.File;
import java.text.MessageFormat;
import java.util.Random;

import com.kinatomicHam.plus.ExtensionAlarmReceiver;
import com.kinatomicHam.plus.ProgressDialogImplementation;
import com.kinatomicHam.plus.ExtensionDownloaderService;
import com.google.android.vending.expansion.downloader.Constants;
import com.google.android.vending.expansion.downloader.DownloadProgressInfo;
import com.google.android.vending.expansion.downloader.DownloaderClientMarshaller;
import com.google.android.vending.expansion.downloader.DownloaderServiceMarshaller;
import com.google.android.vending.expansion.downloader.Helpers;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.google.android.vending.expansion.downloader.IDownloaderService;
import com.google.android.vending.expansion.downloader.IStub;

import com.kinatomicHam.access.AccessibleAlertBuilder;
import com.kinatomicHam.data.LatLon;
import com.kinatomicHam.plus.OsmandApplication;
import com.kinatomicHam.plus.R;
import com.kinatomicHam.plus.Version;
import com.kinatomicHam.plus.activities.search.SearchActivity;
import com.kinatomicHam.plus.render.MapRenderRepositories;
import com.kinatomicHam.plus.resources.ResourceManager;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Messenger;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.util.Log;

public class MainMenuActivity extends Activity implements IDownloaderClient{

	private static final String FIRST_TIME_APP_RUN = "FIRST_TIME_APP_RUN"; //$NON-NLS-1$
	private static final String VECTOR_INDEXES_CHECK = "VECTOR_INDEXES_CHECK"; //$NON-NLS-1$
	private static final String TIPS_SHOW = "TIPS_SHOW"; //$NON-NLS-1$
	private static final String VERSION_INSTALLED = "VERSION_INSTALLED"; //$NON-NLS-1$
	private static final String EXCEPTION_FILE_SIZE = "EXCEPTION_FS"; //$NON-NLS-1$
	
	private static final String CONTRIBUTION_VERSION_FLAG = "CONTRIBUTION_VERSION_FLAG";
	
	public static final int APP_EXIT_CODE = 4;
	public static final String APP_EXIT_KEY = "APP_EXIT_KEY";
	
	private ProgressDialog startProgressDialog;

	//Downloader variables
    private static final String LOG_TAG = "LVLDownloader";
    private ProgressBar mPB;

    private TextView mStatusText;
    private TextView mProgressFraction;
    private TextView mProgressPercent;
    private TextView mAverageSpeed;
    private TextView mTimeRemaining;

    private View mDashboard;
    private View mCellMessage;

    private Button mPauseButton;
    private Button mWiFiSettingsButton;

    private boolean mStatePaused;
    private int mState;

    private IDownloaderService mRemoteService;

    private IStub mDownloaderClientStub;

/**
     * This is a little helper class that demonstrates simple testing of an
     * Expansion APK file delivered by Market. You may not wish to hard-code
     * things such as file lengths into your executable... and you may wish to
     * turn this code off during application development.
     */
    private static class XAPKFile {
        public final boolean mIsMain;
        public final int mFileVersion;
        public final long mFileSize;

        XAPKFile(boolean isMain, int fileVersion, long fileSize) {
            mIsMain = isMain;
            mFileVersion = fileVersion;
            mFileSize = fileSize;
        }
    }

    /**
     * Here is where you place the data that the validator will use to determine
     * if the file was delivered correctly. This is encoded in the source code
     * so the application can easily determine whether the file has been
     * properly delivered without having to talk to the server. If the
     * application is using LVL for licensing, it may make sense to eliminate
     * these checks and to just rely on the server.
     */
    private static final XAPKFile[] xAPKS = {
            new XAPKFile(
                    true, // true signifies a main file
                    4, // the version of the APK that the file was uploaded
                       // against
                    44980579L // the length of the file in bytes
            ),
            new XAPKFile(
                    false, // true signifies a main file
                    7, // the version of the APK that the file was uploaded
                       // against
                    6571166L // the length of the file in bytes
            ),
    };

	public void checkPreviousRunsForExceptions(boolean firstTime) {
		long size = getPreferences(MODE_WORLD_READABLE).getLong(EXCEPTION_FILE_SIZE, 0);
		final OsmandApplication app = ((OsmandApplication) getApplication());
		final File file = app.getAppPath(OsmandApplication.EXCEPTION_PATH);
		if (file.exists() && file.length() > 0) {
			if (size != file.length() && !firstTime) {
				String msg = MessageFormat.format(getString(R.string.previous_run_crashed), OsmandApplication.EXCEPTION_PATH);
				Builder builder = new AccessibleAlertBuilder(MainMenuActivity.this);
				builder.setMessage(msg).setNeutralButton(getString(R.string.close), null);
				builder.setPositiveButton(R.string.send_report, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(Intent.ACTION_SEND);
						intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@kinatomic.com" }); //$NON-NLS-1$
						intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
						intent.setType("vnd.android.cursor.dir/email"); //$NON-NLS-1$
						intent.putExtra(Intent.EXTRA_SUBJECT, "OsmAnd bug"); //$NON-NLS-1$
						StringBuilder text = new StringBuilder();
						text.append("\nDevice : ").append(Build.DEVICE); //$NON-NLS-1$
						text.append("\nBrand : ").append(Build.BRAND); //$NON-NLS-1$
						text.append("\nModel : ").append(Build.MODEL); //$NON-NLS-1$
						text.append("\nProduct : ").append(Build.PRODUCT); //$NON-NLS-1$
						text.append("\nBuild : ").append(Build.DISPLAY); //$NON-NLS-1$
						text.append("\nVersion : ").append(Build.VERSION.RELEASE); //$NON-NLS-1$
						text.append("\nApp Version : ").append(Version.getAppName(app)); //$NON-NLS-1$
						try {
							PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
							if (info != null) {
								text.append("\nApk Version : ").append(info.versionName).append(" ").append(info.versionCode); //$NON-NLS-1$ //$NON-NLS-2$
							}
						} catch (NameNotFoundException e) {
						}
						intent.putExtra(Intent.EXTRA_TEXT, text.toString());
						startActivity(Intent.createChooser(intent, getString(R.string.send_report)));
					}

				});
				builder.show();
			}
			getPreferences(MODE_WORLD_WRITEABLE).edit().putLong(EXCEPTION_FILE_SIZE, file.length()).commit();
		} else {
			if (size > 0) {
				getPreferences(MODE_WORLD_WRITEABLE).edit().putLong(EXCEPTION_FILE_SIZE, 0).commit();
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == APP_EXIT_CODE){
			getMyApplication().closeApplication(this);
		}
	}
	
	public static Animation getAnimation(int left, int top){
		Animation anim = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, left, 
				TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, top, TranslateAnimation.RELATIVE_TO_SELF, 0);
		anim.setDuration(700);
		anim.setInterpolator(new AccelerateInterpolator());
		return anim;
	}
	
	public static void onCreateMainMenu(Window window, final Activity activity){

		View head = (View) window.findViewById(R.id.Headliner);
		head.startAnimation(getAnimation(0, -1));
		
		View leftview = (View) window.findViewById(R.id.MapButton);
		leftview.startAnimation(getAnimation(-1, 0));
		leftview = (View) window.findViewById(R.id.FavoritesButton);
		leftview.startAnimation(getAnimation(-1, 0));
		
		View rightview = (View) window.findViewById(R.id.SettingsButton);
		rightview.startAnimation(getAnimation(1, 0));
		rightview = (View) window.findViewById(R.id.SearchButton);
		rightview.startAnimation(getAnimation(1, 0));
		
		String textVersion = Version.getAppVersion(((OsmandApplication) activity.getApplication()));
		final TextView textVersionView = (TextView) window.findViewById(R.id.TextVersion);
		textVersionView.setText(textVersion);
		SharedPreferences prefs = activity.getApplicationContext().getSharedPreferences("com.kinatomicHam.settings", MODE_WORLD_READABLE);
		
		// only one commit should be with contribution version flag
//		 prefs.edit().putBoolean(CONTRIBUTION_VERSION_FLAG, true).commit();
		if (prefs.contains(CONTRIBUTION_VERSION_FLAG)) {
			SpannableString content = new SpannableString(textVersion);
			content.setSpan(new ClickableSpan() {
				
				@Override
				public void onClick(View widget) {
					final Intent mapIntent = new Intent(activity, ContributionVersionActivity.class);
					activity.startActivityForResult(mapIntent, 0);
					// test geo activity
//					String uri = "geo:0,0?q=Amsterdamseweg";
//					Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
//					activity.startActivity(intent);
				}
			}, 0, content.length(), 0);
			textVersionView.setText(content);
			textVersionView.setMovementMethod(LinkMovementMethod.getInstance());
		}
		View helpButton = window.findViewById(R.id.HelpButton);
		helpButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TipsAndTricksActivity tactivity = new TipsAndTricksActivity(activity);
				Dialog dlg = tactivity.getDialogToShowTips(false, true);
				dlg.show();
			}
		});
	}
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		((OsmandApplication) getApplication()).applyTheme(this);
		super.onCreate(savedInstanceState);
		boolean exit = false;
		if(getIntent() != null){
			Intent intent = getIntent();
			if(intent.getExtras() != null && intent.getExtras().containsKey(APP_EXIT_KEY)){
				exit = true;
			}
		}
		requestWindowFeature(Window.FEATURE_NO_TITLE);

        /**
         * Before we do anything, are the files we expect already here and
         * delivered (presumably by Market) For free titles, this is probably
         * worth doing. (so no Market request is necessary)
         */
        if (!expansionFilesDelivered()) {

			initializeDownloadUI();

            try {
                Intent launchIntent = MainMenuActivity.this
                        .getIntent();
                Intent intentToLaunchThisActivityFromNotification = new Intent(
                        MainMenuActivity
                        .this, MainMenuActivity.this.getClass());
                intentToLaunchThisActivityFromNotification.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentToLaunchThisActivityFromNotification.setAction(launchIntent.getAction());

                if (launchIntent.getCategories() != null) {
                    for (String category : launchIntent.getCategories()) {
                        intentToLaunchThisActivityFromNotification.addCategory(category);
                    }
                }

                // Build PendingIntent used to open this activity from
                // Notification
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        MainMenuActivity.this,
                        0, intentToLaunchThisActivityFromNotification,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                // Request to start the download
                int startResult = DownloaderClientMarshaller.startDownloadServiceIfRequired(this,
                        pendingIntent, ExtensionDownloaderService.class);

                if (startResult != DownloaderClientMarshaller.NO_DOWNLOAD_REQUIRED) {
                    // The DownloaderService has started downloading the files,
                    // show progress

                } // otherwise, download not needed so we fall through to
                  // starting the movie
            } catch (NameNotFoundException e) {
                Log.e(LOG_TAG, "Cannot find own package! MAYDAY!");
                e.printStackTrace();
            }

        } else {
            onCreateFinalMenu(exit);
        }
	}

	protected void onCreateFinalMenu(boolean exit)
	{
		setContentView(R.layout.menu);
		
		onCreateMainMenu(getWindow(), this);

		Window window = getWindow();
		final Activity activity = this;
		View showMap = window.findViewById(R.id.MapButton);
		showMap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent mapIndent = new Intent(activity, OsmandIntents.getMapActivity());
				activity.startActivityForResult(mapIndent, 0);
			}
		});
		View settingsButton = window.findViewById(R.id.SettingsButton);
		settingsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent settings = new Intent(activity, OsmandIntents.getSettingsActivity());
				activity.startActivity(settings);
			}
		});

		View favouritesButton = window.findViewById(R.id.FavoritesButton);
		favouritesButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent favorites = new Intent(activity, OsmandIntents.getFavoritesActivity());
				favorites.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				activity.startActivity(favorites);
			}
		});

		final View closeButton = window.findViewById(R.id.CloseButton);
		closeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getMyApplication().closeApplication(activity);
			}
		});
		View searchButton = window.findViewById(R.id.SearchButton);
		searchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent search = new Intent(activity, OsmandIntents.getSearchActivity());
				search.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				activity.startActivity(search);
			}
		});
		if(exit){
			getMyApplication().closeApplication(activity);
			return;
		}
		OsmandApplication app = getMyApplication();
		// restore follow route mode
		if(app.getSettings().FOLLOW_THE_ROUTE.get() && !app.getRoutingHelper().isRouteCalculated()){
			final Intent mapIndent = new Intent(this, OsmandIntents.getMapActivity());
			startActivityForResult(mapIndent, 0);
			return;
		}
		startProgressDialog = new ProgressDialog(this);
		getMyApplication().checkApplicationIsBeingInitialized(this, startProgressDialog);
		SharedPreferences pref = getPreferences(MODE_WORLD_WRITEABLE);
		boolean firstTime = false;
		if(!pref.contains(FIRST_TIME_APP_RUN)){
			firstTime = true;
			pref.edit().putBoolean(FIRST_TIME_APP_RUN, true).commit();
			pref.edit().putString(VERSION_INSTALLED, Version.getFullVersion(app)).commit();
			
			applicationInstalledFirstTime();

			TipsAndTricksActivity tipsActivity = new TipsAndTricksActivity(this);
			Dialog dlg = tipsActivity.getDialogToShowTips(true, false);
			dlg.show();
		} else {
			int i = pref.getInt(TIPS_SHOW, 0);
			if (i < 7){
				pref.edit().putInt(TIPS_SHOW, ++i).commit();
			}
			boolean appVersionChanged = false;
			if(!Version.getFullVersion(app).equals(pref.getString(VERSION_INSTALLED, ""))){
				pref.edit().putString(VERSION_INSTALLED, Version.getFullVersion(app)).commit();
				appVersionChanged = true;
			}
						
			if (i == 1 || i == 5 || appVersionChanged) {
				TipsAndTricksActivity tipsActivity = new TipsAndTricksActivity(this);
				Dialog dlg = tipsActivity.getDialogToShowTips(!appVersionChanged, false);
				dlg.show();
			} else {
				if (startProgressDialog.isShowing()) {
					startProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
						@Override
						public void onDismiss(DialogInterface dialog) {
							checkVectorIndexesDownloaded();
						}
					});
				} else {
					checkVectorIndexesDownloaded();
				}
			}
		}
		checkPreviousRunsForExceptions(firstTime);
	}

	private void applicationInstalledFirstTime() {
		boolean netOsmandWasInstalled = false;
		try {
			ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo("com.kinatomicHam", PackageManager.GET_META_DATA);
			netOsmandWasInstalled = applicationInfo != null && !Version.isFreeVersion(getMyApplication());
		} catch (NameNotFoundException e) {
			netOsmandWasInstalled = false;
		}
		
		if(netOsmandWasInstalled){
			Builder builder = new AccessibleAlertBuilder(this);
			builder.setMessage(R.string.osmand_net_previously_installed);
			builder.setPositiveButton(R.string.default_buttons_ok, null);
			builder.show();
		} else {
			//Builder builder = new AccessibleAlertBuilder(this);
			//builder.setMessage(R.string.first_time_msg);
			//builder.setPositiveButton(R.string.first_time_download, new DialogInterface.OnClickListener() {

			//	@Override
			//	public void onClick(DialogInterface dialog, int which) {
			//		startActivity(new Intent(MainMenuActivity.this, OsmandIntents.getDownloadIndexActivity()));
			//	}

			//});
			//builder.setNegativeButton(R.string.first_time_continue, null);
			//builder.show();
		}
	}
	
	protected void checkVectorIndexesDownloaded() {
		MapRenderRepositories maps = getMyApplication().getResourceManager().getRenderer();
		SharedPreferences pref = getPreferences(MODE_WORLD_WRITEABLE);
		boolean check = pref.getBoolean(VECTOR_INDEXES_CHECK, true);
		
	}

	private OsmandApplication getMyApplication() {
		return (OsmandApplication) getApplication();
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		if(id == OsmandApplication.PROGRESS_DIALOG){
			return startProgressDialog;
		}
		return super.onCreateDialog(id);
	}
	

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SEARCH
                && event.getRepeatCount() == 0) {
			final Intent search = new Intent(MainMenuActivity.this, SearchActivity.class);
			search.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(search);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    public static void backToMainMenuDialog(final Activity a, final LatLon searchLocation) {
		final Dialog dlg = new Dialog(a, R.style.Dialog_Fullscreen);
		final View menuView = (View) a.getLayoutInflater().inflate(R.layout.menu, null);
		menuView.setBackgroundColor(Color.argb(200, 150, 150, 150));
		dlg.setContentView(menuView);
		MainMenuActivity.onCreateMainMenu(dlg.getWindow(), a);
		Animation anim = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				ColorDrawable colorDraw = ((ColorDrawable) menuView.getBackground());
				colorDraw.setAlpha((int) (interpolatedTime * 200));
			}
		};
		anim.setDuration(700);
		anim.setInterpolator(new AccelerateInterpolator());
		menuView.setAnimation(anim);

		View showMap = dlg.findViewById(R.id.MapButton);
		showMap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dlg.dismiss();
			}
		});
		View settingsButton = dlg.findViewById(R.id.SettingsButton);
		settingsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent settings = new Intent(a, OsmandIntents.getSettingsActivity());
				a.startActivity(settings);
				dlg.dismiss();
			}
		});

		View favouritesButton = dlg.findViewById(R.id.FavoritesButton);
		favouritesButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent favorites = new Intent(a, OsmandIntents.getFavoritesActivity());
				favorites.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				a.startActivity(favorites);
				dlg.dismiss();
			}
		});

		View closeButton = dlg.findViewById(R.id.CloseButton);
		closeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dlg.dismiss();
				// 1. Work for almost all cases when user open apps from main menu
				Intent newIntent = new Intent(a, OsmandIntents.getMainMenuActivity());
				newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				newIntent.putExtra(MainMenuActivity.APP_EXIT_KEY, MainMenuActivity.APP_EXIT_CODE);
				a.startActivity(newIntent);
				// 2. good analogue but user will come back to the current activity onResume()
				// so application is not reloaded !!!
				// moveTaskToBack(true);
				// 3. bad results if user comes from favorites
				// a.setResult(MainMenuActivity.APP_EXIT_CODE);
				// a.finish();
			}
		});

		View searchButton = dlg.findViewById(R.id.SearchButton);
		searchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent search = new Intent(a, OsmandIntents.getSearchActivity());
				LatLon loc = searchLocation;
				search.putExtra(SearchActivity.SEARCH_LAT, loc.getLatitude());
				search.putExtra(SearchActivity.SEARCH_LON, loc.getLongitude());
				// causes wrong position caching:  search.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				search.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				a.startActivity(search);
				dlg.dismiss();
			}
		});
		menuView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dlg.dismiss();
			}
		});

		dlg.show();
		// Intent newIntent = new Intent(a, MainMenuActivity.class);
		// newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// startActivity(newIntent);
	}
	
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(0, 0, 0, R.string.exit_Button);
    	return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 0) {
			getMyApplication().closeApplication(this);
			return true;
		}
		return false;
	}


	/**
     * If the download isn't present, we initialize the download UI. This ties
     * all of the controls into the remote service calls.
     */
    private void initializeDownloadUI() {
        mDownloaderClientStub = DownloaderClientMarshaller.CreateStub
                (this, ExtensionDownloaderService.class);
        setContentView(R.layout.lvl_download);

        mPB = (ProgressBar) findViewById(R.id.progressBar);
        mStatusText = (TextView) findViewById(R.id.statusText);
        mProgressFraction = (TextView) findViewById(R.id.progressAsFraction);
        mProgressPercent = (TextView) findViewById(R.id.progressAsPercentage);
        mAverageSpeed = (TextView) findViewById(R.id.progressAverageSpeed);
        mTimeRemaining = (TextView) findViewById(R.id.progressTimeRemaining);
        mDashboard = findViewById(R.id.downloaderDashboard);
        mCellMessage = findViewById(R.id.approveCellular);
        mPauseButton = (Button) findViewById(R.id.pauseButton);
        mWiFiSettingsButton = (Button) findViewById(R.id.wifiSettingsButton);

        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mStatePaused) {
                    mRemoteService.requestContinueDownload();
                } else {
                    mRemoteService.requestPauseDownload();
                }
                setButtonPausedState(!mStatePaused);
            }
        });

        mWiFiSettingsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });

        Button resumeOnCell = (Button) findViewById(R.id.resumeOverCellular);
        resumeOnCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRemoteService.setDownloadFlags(IDownloaderService.FLAGS_DOWNLOAD_OVER_CELLULAR);
                mRemoteService.requestContinueDownload();
                mCellMessage.setVisibility(View.GONE);
            }
        });

    }


    boolean expansionFilesDelivered() {
        for (XAPKFile xf : xAPKS) {
            String fileName = Helpers.getExpansionAPKFileName(this, xf.mIsMain, xf.mFileVersion);
            if (!Helpers.doesFileExist(this, fileName, xf.mFileSize, false))
                return false;
        }
        return true;
    }


    /**
     * Connect the stub to our service on start.
     */
    @Override
    protected void onStart() {
        if (null != mDownloaderClientStub) {
            mDownloaderClientStub.connect(this);
        }
        super.onStart();
    }

    /**
     * Disconnect the stub from our service on stop
     */
    @Override
    protected void onStop() {
        if (null != mDownloaderClientStub) {
            mDownloaderClientStub.disconnect(this);
        }
        super.onStop();
    }

    /**
     * Critical implementation detail. In onServiceConnected we create the
     * remote service and marshaler. This is how we pass the client information
     * back to the service so the client can be properly notified of changes. We
     * must do this every time we reconnect to the service.
     */
    @Override
    public void onServiceConnected(Messenger m) {
        mRemoteService = DownloaderServiceMarshaller.CreateProxy(m);
        mRemoteService.onClientUpdated(mDownloaderClientStub.getMessenger());
    }


    /**
     * The download state should trigger changes in the UI --- it may be useful
     * to show the state as being indeterminate at times. This sample can be
     * considered a guideline.
     */
    @Override
    public void onDownloadStateChanged(int newState) {
        setState(newState);
        boolean showDashboard = true;
        boolean showCellMessage = false;
        boolean paused;
        boolean indeterminate;
        switch (newState) {
            case IDownloaderClient.STATE_IDLE:
                // STATE_IDLE means the service is listening, so it's
                // safe to start making calls via mRemoteService.
                paused = false;
                indeterminate = true;
                break;
            case IDownloaderClient.STATE_CONNECTING:
            case IDownloaderClient.STATE_FETCHING_URL:
                showDashboard = true;
                paused = false;
                indeterminate = true;
                break;
            case IDownloaderClient.STATE_DOWNLOADING:
                paused = false;
                showDashboard = true;
                indeterminate = false;
                break;

            case IDownloaderClient.STATE_FAILED_CANCELED:
            case IDownloaderClient.STATE_FAILED:
            case IDownloaderClient.STATE_FAILED_FETCHING_URL:
            case IDownloaderClient.STATE_FAILED_UNLICENSED:
                paused = true;
                showDashboard = false;
                indeterminate = false;
                break;
            case IDownloaderClient.STATE_PAUSED_NEED_CELLULAR_PERMISSION:
            case IDownloaderClient.STATE_PAUSED_WIFI_DISABLED_NEED_CELLULAR_PERMISSION:
                showDashboard = false;
                paused = true;
                indeterminate = false;
                showCellMessage = true;
                break;

            case IDownloaderClient.STATE_PAUSED_BY_REQUEST:
                paused = true;
                indeterminate = false;
                break;
            case IDownloaderClient.STATE_PAUSED_ROAMING:
            case IDownloaderClient.STATE_PAUSED_SDCARD_UNAVAILABLE:
                paused = true;
                indeterminate = false;
                break;
            case IDownloaderClient.STATE_COMPLETED:
                showDashboard = false;
                paused = false;
                indeterminate = false;
				onCreateFinalMenu(false);
				ResourceManager manager = getMyApplication().getResourceManager();
				ProgressDialogImplementation startDialog = new ProgressDialogImplementation(this, null, false);
				manager.reloadIndexes(startDialog);
                return;
            default:
                paused = true;
                indeterminate = true;
                showDashboard = true;
        }
        int newDashboardVisibility = showDashboard ? View.VISIBLE : View.GONE;
        if (mDashboard.getVisibility() != newDashboardVisibility) {
            mDashboard.setVisibility(newDashboardVisibility);
        }
        int cellMessageVisibility = showCellMessage ? View.VISIBLE : View.GONE;
        if (mCellMessage.getVisibility() != cellMessageVisibility) {
            mCellMessage.setVisibility(cellMessageVisibility);
        }

        mPB.setIndeterminate(indeterminate);
        setButtonPausedState(paused);
    }

    /**
     * Sets the state of the various controls based on the progressinfo object
     * sent from the downloader service.
     */
    @Override
    public void onDownloadProgress(DownloadProgressInfo progress) {
        mAverageSpeed.setText(getString(R.string.kilobytes_per_second,
                Helpers.getSpeedString(progress.mCurrentSpeed)));
        mTimeRemaining.setText(getString(R.string.time_remaining,
                Helpers.getTimeRemaining(progress.mTimeRemaining)));

        progress.mOverallTotal = progress.mOverallTotal;
        mPB.setMax((int) (progress.mOverallTotal >> 8));
        mPB.setProgress((int) (progress.mOverallProgress >> 8));
        mProgressPercent.setText(Long.toString(progress.mOverallProgress
                * 100 /
                progress.mOverallTotal) + "%");
        mProgressFraction.setText(Helpers.getDownloadProgressString
                (progress.mOverallProgress,
                        progress.mOverallTotal));
    }

    private void setButtonPausedState(boolean paused) {
        mStatePaused = paused;
        int stringResourceID = paused ? R.string.text_button_resume :
                R.string.text_button_pause;
        mPauseButton.setText(stringResourceID);
    }

    private void setState(int newState) {
        if (mState != newState) {
            mState = newState;
            mStatusText.setText(Helpers.getDownloaderStringResourceIDFromState(newState));
        }
    }

	
}

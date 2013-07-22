package com.kinatomicHamp.plus.activities;

import com.kinatomicHamp.plus.R;

import com.bidforfix.andorid.BidForFixActivity;
import com.bidforfix.andorid.BidForFixHelper;

public class OsmandBidForFixActivity extends BidForFixActivity {

	@Override
	public BidForFixHelper getBidForFixHelper() {
		return new BidForFixHelper("osmand.net", getString(R.string.default_buttons_support),
				getString(R.string.default_buttons_cancel));
	}
}

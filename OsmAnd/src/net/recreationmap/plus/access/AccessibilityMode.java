package com.kinatomicHamp.plus.access;

import com.kinatomicHamp.plus.ClientContext;
import com.kinatomicHamp.plus.R;

public enum AccessibilityMode {

    ON(R.string.accessibility_on),
    OFF(R.string.accessibility_off),
    DEFAULT(R.string.accessibility_default);

    private final int key;

    AccessibilityMode(int key) {
        this.key = key;
    }

    public String toHumanString(ClientContext ctx) {
        return ctx.getString(key);
    }

}

package com.kinatomicHamp.plus.access;

import com.kinatomicHamp.plus.ClientContext;
import com.kinatomicHamp.plus.R;

public enum RelativeDirectionStyle {

    SIDEWISE(R.string.direction_style_sidewise),
    CLOCKWISE(R.string.direction_style_clockwise);

    private final int key;

    RelativeDirectionStyle(int key) {
        this.key = key;
    }

    public String toHumanString(ClientContext ctx) {
        return ctx.getString(key);
    }

}

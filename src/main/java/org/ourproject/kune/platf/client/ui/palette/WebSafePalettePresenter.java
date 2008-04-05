/*
 *
 * Copyright (C) 2007-2008 The kune development team (see CREDITS for details)
 * This file is part of kune.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.ourproject.kune.platf.client.ui.palette;

import java.util.ArrayList;
import java.util.Iterator;

public class WebSafePalettePresenter {

    private final ArrayList<ColorSelectListener> colorSelectListeners;

    public WebSafePalettePresenter() {
        colorSelectListeners = new ArrayList<ColorSelectListener>();
    }

    public void addColorSelectListener(final ColorSelectListener listener) {
        colorSelectListeners.add(listener);
    }

    protected void fireColorSelectListeners(final String color) {
        for (Iterator<ColorSelectListener> it = colorSelectListeners.iterator(); it.hasNext();) {
            (it.next()).onColorSelected(color);
        }
    }

    public void onColorSelected(final int row, final int col) {
        String color = getColor(row, col);
        fireColorSelectListeners(color);
    }

    private String getColor(final int row, final int col) {
        String color = null;
        int pd = row * WebSafePaletteView.COLS + col;
        int da = pd / 6;
        int ra = pd % 6;
        int aa = da - ra / 6;
        int db = aa / 6;
        int rb = aa % 6;
        int rc = (db - rb / 6) % 6;
        color = "rgb(" + ra * 51 + ", " + rc * 51 + ", " + rb * 51 + ")";
        return color;
    }

    public void reset() {
        colorSelectListeners.clear();
    }

}

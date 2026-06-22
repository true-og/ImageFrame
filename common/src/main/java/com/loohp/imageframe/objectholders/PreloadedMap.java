/*
 * This file is part of ImageFrame.
 *
 * Copyright (C) 2025. LoohpJames <jamesloohp@gmail.com>
 * Copyright (C) 2025. Contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.loohp.imageframe.objectholders;

public class PreloadedMap {

    private final String name;
    private final String url;
    private final int width;
    private final int height;
    private final DitheringType ditheringType;

    public PreloadedMap(String name, String url, int width, int height, DitheringType ditheringType) {
        this.name = name;
        this.url = url;
        this.width = width;
        this.height = height;
        this.ditheringType = ditheringType;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public DitheringType getDitheringType() {
        return ditheringType;
    }
}

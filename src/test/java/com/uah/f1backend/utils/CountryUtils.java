package com.uah.f1backend.utils;

import com.uah.f1backend.model.CountryModel;

public class CountryUtils {
    public static CountryModel dummyCountryModel() {
        return new CountryModel(1, "CO", "country");
    }
}

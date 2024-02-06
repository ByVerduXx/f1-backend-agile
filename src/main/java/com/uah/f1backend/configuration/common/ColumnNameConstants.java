package com.uah.f1backend.configuration.common;

public class ColumnNameConstants {
    // Team
    public static final String TEAM_ID = "id";
    public static final String TEAM_NAME = "name";
    public static final String TEAM_LOGO = "logo";
    public static final String TEAM_TWITTER = "twitter";

    // Country
    public static final String COUNTRY_ID = "id";
    public static final String COUNTRY_CODE = "code";
    public static final String COUNTRY_NAME = "name";

    // Driver
    public static final String DRIVER_ID = "id";
    public static final String DRIVER_NAME = "name";
    public static final String DRIVER_LAST_NAME = "last_name";
    public static final String DRIVER_INITIAL = "initial";
    public static final String DRIVER_DORSAL = "dorsal";
    public static final String DRIVER_PHOTO = "photo";
    public static final String DRIVER_TWITTER = "twitter";
    public static final String DRIVER_ID_COUNTRY = "id_country";
    public static final String DRIVER_ID_TEAM = "id_team";
    public static final String DRIVER_SURVEYS_MAPPING = "drivers";
    public static final String DRIVER_VOTES_MAPPING = "driver";

    // Car
    public static final String CAR_ID = "id";
    public static final String CAR_TEAM_ID = "id_team";
    public static final String CAR_NAME = "name";
    public static final String CAR_CODE = "code";
    public static final String CAR_ERS_GAIN_SLOW = "ers_gain_slow";
    public static final String CAR_RS_GAIN_MEDIUM = "ers_gain_medium";
    public static final String CAR_ERS_GAIN_FAST = "ers_gain_fast";
    public static final String CAR_CONSUMPTION = "consumption";

    // Circuit
    public static final String CIRCUIT_ID = "id";
    public static final String CIRCUIT_NAME = "name";
    public static final String CIRCUIT_CITY = "city";
    public static final String CIRCUIT_COUNTRY = "id_country";
    public static final String CIRCUIT_LAPS = "laps";
    public static final String CIRCUIT_LENGTH = "length";
    public static final String CIRCUIT_IMAGE = "image";
    public static final String CIRCUIT_SLOW_TURNS = "slow_turns";
    public static final String CIRCUIT_MEDIUM_TURNS = "medium_turns";
    public static final String CIRCUIT_FAST_TURNS = "fast_turns";

    // News
    public static final String NEWS_ID = "id";
    public static final String NEWS_PERMALINK = "permalink";
    public static final String NEWS_TITLE = "title";
    public static final String NEWS_IMAGE = "image";
    public static final String NEWS_TEXT = "text";
    public static final String NEWS_PUBLICATION_DATE = "publication_date";

    // Survey
    public static final String SURVEY_ID = "id";
    public static final String SURVEY_PERMALINK = "permalink";
    public static final String SURVEY_TITLE = "title";
    public static final String SURVEY_DESCRIPTION = "description";
    public static final String SURVEY_LIMIT_DATE = "limit_date";
    public static final String SURVEY_VOTES_MAPPING = "survey";

    // Survey Driver
    public static final String SURVEY_DRIVER_SURVEY_ID = "id_survey";
    public static final String SURVEY_DRIVER_DRIVER_ID = "id_driver";

    // Vote
    public static final String VOTE_ID = "id";
    public static final String VOTE_SURVEY_ID = "id_survey";
    public static final String VOTE_DRIVER_ID = "id_driver";
    public static final String VOTE_VOTER_NAME = "voter_name";
    public static final String VOTE_VOTER_EMAIL = "voter_email";
}

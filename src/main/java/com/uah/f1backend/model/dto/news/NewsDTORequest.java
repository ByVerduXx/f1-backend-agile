package com.uah.f1backend.model.dto.news;

import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class NewsDTORequest {

    private final String permalink;
    private final String title;
    private final String image;
    private final String text;
    private final Date publicationDate;
}

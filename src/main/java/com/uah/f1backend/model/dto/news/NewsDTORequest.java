package com.uah.f1backend.model.dto.news;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class NewsDTORequest {

    private final String permalink;
    private final String title;
    private final String image;
    private final String text;
    private final String publication_date;
}
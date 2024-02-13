package com.uah.f1backend.model.dto.news;

import java.util.Date;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NewsDTORequest {

    private String permalink;
    private String title;
    private String image;
    private String text;
    private Date publicationDate;
}

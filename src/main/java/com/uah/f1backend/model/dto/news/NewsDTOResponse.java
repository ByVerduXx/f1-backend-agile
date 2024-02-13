package com.uah.f1backend.model.dto.news;

import java.util.Date;
import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTOResponse {

    private Integer id;
    private String permalink;
    private String title;
    private String image;
    private String text;
    private Date publicationDate;
}

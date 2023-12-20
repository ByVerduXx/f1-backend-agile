package com.uah.f1backend.model;

import com.uah.f1backend.configuration.common.ColumnNameConstants;
import com.uah.f1backend.configuration.common.TableNameConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = TableNameConstants.NEWS_TABLE)
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class NewsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Column(name = ColumnNameConstants.NEWS_ID)
    private Integer id;

    @Column(name = ColumnNameConstants.NEWS_PERMALINK)
    private String permalink;

    @Column(name = ColumnNameConstants.NEWS_TITLE)
    private String title;

    @Column(name = ColumnNameConstants.NEWS_IMAGE)
    private String image;

    @Column(name = ColumnNameConstants.NEWS_TEXT)
    private String text;

    @Column(name = ColumnNameConstants.NEWS_PUBLICATION_DATE)
    private String publication_date;
}

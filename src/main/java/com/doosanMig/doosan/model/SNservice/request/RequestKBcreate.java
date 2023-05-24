package com.doosanMig.doosan.model.SNservice.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RequestKBcreate {

    String article_type;

    String text;

    String short_description;

    String valid_to;

    String published;

    @Builder
    public RequestKBcreate(
            String article_type,
            String text,
            String short_description,
            String valid_to,
            String published
    ) {
        this.article_type = article_type;
        this.text = text;
        this.short_description = short_description;
        this.valid_to = valid_to;
        this.published = published;
    }
}

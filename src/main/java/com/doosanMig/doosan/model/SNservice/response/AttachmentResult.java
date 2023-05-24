package com.doosanMig.doosan.model.SNservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentResult {

    @JsonProperty("file_name")
    String file_name;
}

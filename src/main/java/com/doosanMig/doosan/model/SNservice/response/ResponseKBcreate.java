package com.doosanMig.doosan.model.SNservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseKBcreate {

    @JsonProperty("result")
    Result result;
}

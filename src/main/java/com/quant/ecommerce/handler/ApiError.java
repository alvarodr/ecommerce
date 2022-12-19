package com.quant.ecommerce.handler;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
@ApiModel(description = "Model message API error manage")
public class ApiError {
    @ApiModelProperty(
        value = "Status returned",
        dataType = "String",
        name = "status",
        example = "ACCEPTED"
    )
    private HttpStatus status;
    @ApiModelProperty(
        value = "Path invoked",
        dataType = "String",
        name = "path",
        example = "ecommerce/product/34564?dateTimeApplied="
    )
    private String path;
    @ApiModelProperty(
        value = "Detail error message",
        dataType = "String",
        name = "message",
        example = "Price not found"
    )
    private String message;
}

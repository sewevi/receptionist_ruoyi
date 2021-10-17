package com.ruoyi.receptionist.dto.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author dzhiqin@linewell.com
 * @since 2018-12-21
 */
@Data
@JsonIgnoreProperties(value = {"handler"})
public class BasePageDTO<T> {
    private Long total;
    private T rows;
}

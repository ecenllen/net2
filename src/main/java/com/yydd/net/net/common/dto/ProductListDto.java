package com.yydd.net.net.common.dto;


import com.yydd.net.net.BaseDto;
import com.yydd.net.net.constants.FeatureEnum;

/**
 * @Author: liaohaiping
 * @Description:
 * @Date: Created in 2019/6/11 0011 18:09
 */
public class ProductListDto extends BaseDto {
    public FeatureEnum feature;

    public ProductListDto( FeatureEnum feature) {
        this.feature = feature;
    }
}

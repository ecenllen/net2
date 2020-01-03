package com.yydd.net.net.common.vo;

import java.math.BigDecimal;
import java.util.List;

public class ProductVO {
  private long id;
  private String application; //应用程序
  private String sku; //sku ;
  private String name;
  private String description;
  private BigDecimal oldPrice;
  private BigDecimal price;
  private int amount;
  private boolean customPrice; //是否用户指定金额
  private List<ProductFeatureVO> productFeatures;
  private boolean isChecked;

  public boolean isChecked() {
    return isChecked;
  }

  public ProductVO setChecked(boolean checked) {
    isChecked = checked;
    return this;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getOldPrice() {
    return oldPrice;
  }

  public void setOldPrice(BigDecimal oldPrice) {
    this.oldPrice = oldPrice;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public List<ProductFeatureVO> getProductFeatures() {
    return productFeatures;
  }

  public void setProductFeatures(List<ProductFeatureVO> productFeatures) {
    this.productFeatures = productFeatures;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public boolean isCustomPrice() {
    return customPrice;
  }

  public void setCustomPrice(boolean customPrice) {
    this.customPrice = customPrice;
  }
}

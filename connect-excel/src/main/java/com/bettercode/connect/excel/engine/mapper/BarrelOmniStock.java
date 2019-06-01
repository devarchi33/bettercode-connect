package com.bettercode.connect.excel.engine.mapper;

public class BarrelOmniStock {

    private String skuCode;
    private String styleCode;
    private String styleName;
    private Integer qty;

    public BarrelOmniStock() {
    }

    public BarrelOmniStock(String skuCode, String styleCode, String styleName, Integer qty) {
        this.skuCode = skuCode;
        this.styleCode = styleCode;
        this.styleName = styleName;
        this.qty = qty;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public void setStyleCode(String styleCode) {
        this.styleCode = styleCode;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public String getStyleCode() {
        return styleCode;
    }

    public String getStyleName() {
        return styleName;
    }

    public Integer getQty() {
        return qty;
    }

    @Override
    public String toString() {
        return "{"
                + "\"skuCode\":\"" + skuCode + "\""
                + ", \"styleCode\":\"" + styleCode + "\""
                + ", \"styleName\":\"" + styleName + "\""
                + ", \"qty\":\"" + qty + "\""
                + "}";
    }
}

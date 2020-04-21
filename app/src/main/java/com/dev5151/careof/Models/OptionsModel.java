package com.dev5151.careof.Models;

import java.util.List;

public class OptionsModel {
    private String imageUrl;
    private String option;
    private String subTitle;

    public OptionsModel() {
    }

    public OptionsModel(String imageUrl, String option, String subTitle) {
        this.imageUrl = imageUrl;
        this.option = option;
        this.subTitle = subTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}

package com.dev5151.careof.Models;

import android.widget.LinearLayout;

import java.util.List;

public class LifeStyleModel {
    private List<OptionsModel> optionsList;
    private String ques;
    private String subText;
    private String viewType;

    public LifeStyleModel() {
    }

    public LifeStyleModel(String ques, String subText, String viewType, List<OptionsModel> optionsList) {
        this.ques = ques;
        this.subText = subText;
        this.viewType = viewType;
        this.optionsList = optionsList;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public List<OptionsModel> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<OptionsModel> optionsList) {
        this.optionsList = optionsList;
    }
}

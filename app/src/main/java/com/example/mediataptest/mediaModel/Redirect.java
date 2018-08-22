
package com.example.mediataptest.mediaModel;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Redirect implements Serializable {

    @SerializedName("index")
    public Integer index;
    @SerializedName("from")
    public String from;
    @SerializedName("to")
    public String to;
    private final static long serialVersionUID = 7579567209236243675L;

}

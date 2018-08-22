
package com.example.mediataptest.mediaModel;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Thumbnail implements Serializable {

    @SerializedName("source")
    public String source;
    @SerializedName("width")
    public Integer width;
    @SerializedName("height")
    public Integer height;
    private final static long serialVersionUID = -8505064834486772899L;

}


package com.example.mediataptest.mediaModel;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Continue implements Serializable {

    @SerializedName("gpsoffset")
    public Integer gpsoffset;
    @SerializedName("continue")
    public String _continue;
    private final static long serialVersionUID = -7486201571218701225L;

}

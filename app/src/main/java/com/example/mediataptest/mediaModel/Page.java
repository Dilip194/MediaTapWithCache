
package com.example.mediataptest.mediaModel;

import android.databinding.BaseObservable;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page extends BaseObservable implements Serializable
{

    @SerializedName("pageid")
    public Integer pageid;
    @SerializedName("ns")
    public Integer ns;
    @SerializedName("title")
    public String title;
    @SerializedName("index")
    public Integer index;
    @SerializedName("thumbnail")
    public Thumbnail thumbnail;
    @SerializedName("terms")
    public Terms terms;
    private final static long serialVersionUID = -225294960645516968L;

}

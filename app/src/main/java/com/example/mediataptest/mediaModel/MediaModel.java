
package com.example.mediataptest.mediaModel;

import android.databinding.BaseObservable;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaModel extends BaseObservable implements Serializable {

    @SerializedName("batchcomplete")
    public Boolean batchcomplete;
    @SerializedName("continue")
    public Continue _continue;
    @SerializedName("query")
    public Query query;
    private final static long serialVersionUID = -138838347696162812L;

}

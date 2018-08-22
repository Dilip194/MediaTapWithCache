
package com.example.mediataptest.mediaModel;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Terms implements Serializable {
    @SerializedName("description")
    public List<String> description = null;
    private final static long serialVersionUID = -7004401313648036129L;

}

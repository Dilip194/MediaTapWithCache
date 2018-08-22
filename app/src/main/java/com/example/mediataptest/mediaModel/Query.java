
package com.example.mediataptest.mediaModel;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Query implements Serializable {

    @SerializedName("redirects")
    public List<Redirect> redirects = null;
    @SerializedName("pages")
    public List<Page> pages = null;
    private final static long serialVersionUID = 8902528212140973266L;

}

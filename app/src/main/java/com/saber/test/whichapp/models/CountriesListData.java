package com.saber.test.whichapp.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Entity
@Generated("org.jsonschema2pojo")
public class CountriesListData {

    @NonNull
    @SerializedName("iso")
    @Expose
    private String iso;

    @NonNull
    @SerializedName("name")
    @PrimaryKey
    @Expose
    private String name;

    @SerializedName("phone")
    @Expose
    private String phone;

    /**
     * @return The iso
     */
    public String getIso() {
        return iso;
    }

    /**
     * @param iso The iso
     */
    public void setIso(String iso) {
        this.iso = iso;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }


}
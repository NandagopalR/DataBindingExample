package com.nanda.databindingexample.data.dbmodels;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PlantModel extends RealmObject {

    @PrimaryKey
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

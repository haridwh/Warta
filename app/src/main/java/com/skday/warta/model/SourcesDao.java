package com.skday.warta.model;

import java.util.List;

/**
 * Created by skday on 4/18/17.
 */

public class SourcesDao {
    private String status;
    private List<Source> sources;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}

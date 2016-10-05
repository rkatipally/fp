package com.fp.datafeed;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fp.models.Company;

import java.util.List;

/**
 * Created by raj on 10/4/16.
 */
public class CompanyResponse {

    @JsonProperty("result_count")
    private long count;
    @JsonProperty("page_size")
    private int pageSize;
    @JsonProperty("current_page")
    private int currentPage;
    @JsonProperty("total_pages")
    private int toalPages;
    @JsonProperty("api_call_credits")
    private int apiCallCredits;
    @JsonProperty("data")
    private List<Company> data;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getToalPages() {
        return toalPages;
    }

    public void setToalPages(int toalPages) {
        this.toalPages = toalPages;
    }

    public int getApiCallCredits() {
        return apiCallCredits;
    }

    public void setApiCallCredits(int apiCallCredits) {
        this.apiCallCredits = apiCallCredits;
    }

    public List<Company> getData() {
        return data;
    }

    public void setData(List<Company> data) {
        this.data = data;
    }
}

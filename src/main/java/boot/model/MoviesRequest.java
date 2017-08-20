package boot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true, allowGetters = true)
public class MoviesRequest {
    Integer page;

    Integer total_results;

    Integer total_pages;

    List<ApiMovie> results;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer results) {
        this.total_results = results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer pages) {
        this.total_pages = pages;
    }

    public List<ApiMovie> getResults() {
        return results;
    }

    public void setResults(List<ApiMovie> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "MoviesRequest{" +
                "page=" + page +
                ", total_results=" + total_results +
                ", total_pages=" + total_pages +
                ", results=" + results +
                '}';
    }
}

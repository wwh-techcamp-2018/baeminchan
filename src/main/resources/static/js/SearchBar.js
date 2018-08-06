class SearchBar {
    constructor() {
        this.resultHolder = null;
        this.resultList = [];
        this.keywordString = "";
        this.selected = null;
        this.init();
    }

    init() {
        $("#searching_text").addEventListener("input", function (evt) {
            evt.preventDefault();

            if(this.keywordString != this.getQueryKeyword()) {
                this.fetchSearchRecommendations(this.getQueryKeyword());
                this.keywordString = this.getQueryKeyword();
            }

        }.bind(this));

        $("#searching_text").addEventListener("keydown",function (evt) {
            console.log(evt.keyCode);
            if(evt.keyCode == 40 || evt.keyCode == 38) {
                evt.preventDefault();
                this.handleArrowKeys(evt.keyCode);
            }

        }.bind(this));

        this.resultHolder = $("#search-results-holder");
    }

    getQueryKeyword() {
        return $_value("#searching_text");
    }

    fetchSearchRecommendations(keyword) {
        getManager({
            url: "/search/recommendations?keyword=" + keyword,
            method: "GET",
            headers: {"Content-type": "application/json"},
            callback : this.handleSearchResults.bind(this)
        });
    }

    handleSearchResults(result) {
        if(this.getQueryKeyword() != result.keyword)
            return;


        this.resultHolder.innerHTML = "";
        console.log(result);
        this.resultList = [];
        for(const product of result.products) {

            this.resultList.push(new SearchResult(this, product));
        }
    }

    handleArrowKeys(keyCode) {
        const selected = $(".search-result-selected ");
        console.log("Handling arrow keys!");
        if(keyCode == 38) { // UP
            if(!selected) {
                this.selectSearchResult(this.resultList.length - 1);
            }
        } else if (keyCode == 40) { // DOWN
            if(!selected) {
                this.selectSearchResult(0);
            }
        }
    }

    selectSearchResult(index) {
        const searchResults = $_all(".search-result");
        searchResults[index].classList.toggle("search-result-selected")
    }


}
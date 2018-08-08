class SearchBar {
    constructor() {
        this.resultHolder = null;
        this.resultList = [];
        this.keywordString = "";
        this.selected = -1;
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

        $("#searching_text").addEventListener("focusout", function(evt) {
            evt.preventDefault();
            this.resultHolder.style.display = "none";
        }.bind(this));

        $("#searching_text").addEventListener("focus", function(evt) {
            evt.preventDefault();
            this.resultHolder.style.display = "block";
        }.bind(this));


        $("#searching_text").addEventListener("keydown",function (evt) {
            console.log(evt.keyCode);
            if(evt.keyCode == 40 || evt.keyCode == 38) {
                //evt.preventDefault();
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
        this.selected = -1;

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
        this.selectSearchResult(this.selected);
        if(keyCode == 38) { // UP
            if(this.selected <= 0) {
                this.selected = this.resultList.length - 1;
            } else {
                this.selected = this.selected - 1;
            }
        } else if (keyCode == 40) { // DOWN
            if(this.selected >= this.resultList.length - 1) {
                this.selected = 0;
            } else {
                this.selected = this.selected + 1;
            }
        }
        this.selectSearchResult(this.selected);
    }

    selectSearchResult(index) {
        if(index === -1) return;
        const searchResults = $_all(".search-result");
        searchResults[index].classList.toggle("search-result-selected")
        // this.resultList[index].makeSelected();
    }


}
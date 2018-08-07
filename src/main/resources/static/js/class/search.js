class Search {
    constructor() {
        this.searchFocusIndex = undefined;
        this.previousKeyword = undefined;
        this.searchingText = $("#searching_text");
        this.searchResultList = $("#search-result-list");
        this.initEvents();
    }

    initEvents() {
        this.searchingText.addEventListener("keydown", (evt) => {
            if (evt.key === "ArrowUp") {
                this.handleArrowUp();
            }
            else if (evt.key === "ArrowDown") {
                this.handleArrowDown();
            }
            else if (evt.key === "Enter") {
                evt.preventDefault();
                this.searchingText.value = $(".search-result-selected").innerText;
                this.clearSearchResultList();
            }
        });

        this.searchingText.addEventListener("input", (evt) => {
            let keyword = evt.currentTarget.value;
            if (keyword.length < 2) {
                this.clearSearchResultList();
            }
            else if (keyword !== this.previousKeyword) {
                this.previousKeyword = keyword;
                this.getSearchRecommendations(keyword);
            }
        });
    }

    getSearchRecommendations(keyword) {
        getManager({
            url: "/search/recommendations?keyword=" + encodeURI(keyword),
            method: "GET",
            callback: this.drawSearchRecommendations.bind(this)
        })
    }

    drawSearchRecommendations(result) {
        this.initSearchFocus();
        this.searchResultList.innerHTML = "";
        result.products.forEach((product) => {
            this.searchResultList.innerHTML += this.getSearchResultLi(product, result.keyword);
        });
    }

    getSearchResultLi(product, keyword) {
        const searchLi = `<li class="search-result-item"><p>${product.title}</p></li>`
        return searchLi.replace(keyword, "<strong>" + keyword + "</strong>");
    }

    clearSearchResultList() {
        this.searchResultList.innerHTML = "";
    }

    initSearchFocus() {
        const selectedResult = $(".search-result-selected");
        if (selectedResult) {
            selectedResult.classList.toggle("search-result-selected");
        }
        this.searchFocusIndex = undefined;
    }

    handleArrowUp() {
        const itemLength = this.searchResultList.childNodes.length;
        if (itemLength === 0)
            return;

        if (this.searchFocusIndex === undefined) {
            this.searchFocusIndex = itemLength - 1;
        }
        else {
            this.searchFocusIndex = (this.searchFocusIndex + itemLength - 1) % itemLength;
        }
        this.toggleFocusing(this.searchFocusIndex);
    }

    handleArrowDown() {
        const itemLength = this.searchResultList.childNodes.length;
        if (itemLength === 0)
            return;

        if (this.searchFocusIndex === undefined) {
            this.searchFocusIndex = 0;
        }
        else {
            this.searchFocusIndex = (this.searchFocusIndex + 1) % itemLength;
        }
        this.toggleFocusing(this.searchFocusIndex);
    }

    toggleFocusing(current) {
        const selectedResult = $(".search-result-selected");
        if (selectedResult) {
            selectedResult.classList.toggle("search-result-selected")
        }
        $_all(".search-result-item")[current].classList.toggle("search-result-selected");
    }
}
class SearchResult {
    constructor(searchBar, product) {
        this.searchBar = searchBar;
        this.product = product;
        this.init();
    }

    init() {
        const highlightTemplate = Handlebars.templates["precompile/search_result_highlight"];
        const keywordStartIndex = this.product.title.indexOf(this.searchBar.keywordString);
        const preText = this.product.title.substring(0, keywordStartIndex);
        const postText = this.product.title.substring(keywordStartIndex + this.searchBar.keywordString.length, this.product.title.length);
        const text = preText + highlightTemplate({"keyword": this.searchBar.keywordString}) + postText;
        const template = Handlebars.templates["precompile/search_result_template"];
        this.searchBar.resultHolder.innerHTML += template({"title": ""});

        this.currentElement = this.searchBar.resultHolder.lastChild;
        this.currentElement.innerHTML = text;

    }

    makeSelected() {
        console.log("Selected!");
        this.currentElement.classList.toggle("search-result-selected");
        console.log(this.currentElement);
    }
}

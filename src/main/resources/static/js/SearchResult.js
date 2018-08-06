class SearchResult {
    constructor(searchBar, product) {
        this.searchBar = searchBar;
        this.product = product;
        this.init();
    }

    init() {
        var template = Handlebars.templates["precompile/search_result_template"];
        this.searchBar.resultHolder.innerHTML += template(this.product);

    }
}
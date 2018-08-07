class SearchBox {
    constructor() {
        this.init();
    }

    init() {
        $("#searching_text").addEventListener("keyup", this.getSearchResults.bind(this));
        $(".search-list-box").addEventListener("mouseover", this.makeHighlight.bind(this))
        $(".search-list-box").addEventListener("click", this.clickSearchText.bind(this))
    }

    fetchManager({ url, method, body, headers, callback }) {
        fetch(url, {method,body,headers,credentials: "same-origin"})
            .then((response) => {
                return response.json()
            }).then((result) => {
            callback(result)
        })
    }

    makeHighlight(evt) {
        evt.target.textContent.contains($("#searching_text").value)
    }

    clickSearchText(evt) {
        $("#searching_text").value = evt.target.textContent;
    }

    isEmptySearchText(evt) {
        return evt.target.value.trim().length == 0;
    }

    getSearchResults(evt) {
        if(this.isEmptySearchText(evt)){
            $(".search-list-box").innerHTML = '';
            return false;
        }
        const queryString = '?query=' + evt.target.value;
        this.fetchManager({
            url: '/api/side/dishes'.concat(queryString),
            method: 'GET',
            headers: { 'content-type': 'application/json'},
            callback: this.renderSearchResults.bind(this)
        })
    }

    isEmpty(obj) {
        obj.length == 0
    }

    renderSearchResults(results) {
        if(this.isEmpty(results)) {
            return false;
        }
        let searchBoxHtml = '';
        for(const text of results) {
            searchBoxHtml += `<li>${text.name}</li>`
        }
        $(".search-list-box").innerHTML = searchBoxHtml;
    }

}

document.addEventListener("DOMContentLoaded", () => {
    new SearchBox();
});
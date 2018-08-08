const CURRENT_AREA = "current-area"
const DOWN_ARROW = 40;
const UP_ARROW = 38;
const ENTER = 13;

class SearchBox {
    constructor() {
        this.init();
    }

    init() {
        $("#searching_text").addEventListener("keyup", this.getSearchResults.bind(this));
        $(".search-list-box").addEventListener("click", this.clickSearchText.bind(this));
    }

    fetchManager({ url, method, body, headers, callback }) {
        fetch(url, {method,body,headers,credentials: "same-origin"})
            .then((response) => {
                return response.json()
            }).then((result) => {
            callback(result)
        })
    }

    makeHighlight(searchResultTxt) {
        const originWord = $("#searching_text").value;
        const targetWord = '<span class="match-word">' + originWord + '</span>';
        return searchResultTxt.replace(originWord, targetWord);
    }


    clickSearchText(evt) {
        evt.target.closest('li').parentElement.childNodes.forEach(function (searchText) {
            searchText.classList.remove(CURRENT_AREA);
        })
        evt.target.closest('li').classList.toggle(CURRENT_AREA);
        $("#searching_text").value = evt.target.closest('li').textContent;
        $("#searching_text").focus();
    }

    isEmptySearchText(evt) {
        return evt.target.value.trim().length == 0;
    }

    getSearchResults(evt) {
        if(this.isEmptySearchText(evt)) {
            $(".search-list-box").innerHTML = '';
            return false;
        }

        if(evt.keyCode === DOWN_ARROW) { // down arrow
            this.downArrowHandler();
            return false;
        }
        if(evt.keyCode === UP_ARROW) { // up arrow
            this.upArrowHandler();
            return false;
        }
        if(evt.keyCode === ENTER) { // enter
            this.enterHandler();
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

    enterHandler() {
        if($(".current-area") != null) {
            $("#searching_text").value = $(".current-area .search-result-word").textContent;
        }
        $(".search-list-box").innerHTML = '';
        alert("검색 성공!")
    }

    upArrowHandler() {
        const currentArea = $(".search-list-box .current-area");
        if(currentArea === null) {
            return false;
        }
        if(currentArea.previousElementSibling === null) {
            currentArea.classList.toggle(CURRENT_AREA);
            return false;
        }
        currentArea.previousElementSibling.classList.toggle(CURRENT_AREA);
        currentArea.classList.toggle(CURRENT_AREA);
    }

    downArrowHandler() {
        const currentAreaBox = $(".search-list-box");
        const currentArea = $(".search-list-box .current-area");
        if(currentAreaBox.hasChildNodes() === false) {
            return false;
        }

        if(currentArea === null) {
            $(".search-list-box").firstElementChild.classList.toggle(CURRENT_AREA);
            return false;
        }
        if(currentArea.nextElementSibling === null) {
            currentArea.classList.toggle(CURRENT_AREA);
            currentArea.parentElement.firstElementChild.classList.toggle(CURRENT_AREA);
            return false;
        }
        currentArea.nextElementSibling.classList.toggle(CURRENT_AREA);
        currentArea.classList.toggle(CURRENT_AREA);
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
            let searchResultText = this.makeHighlight(text.name);
            searchBoxHtml += `<li><a href="#"><span class="search-result-word">` + searchResultText + `</span></a></li>`
        }
        $(".search-list-box").innerHTML = searchBoxHtml;
    }

}

document.addEventListener("DOMContentLoaded", () => {
    new SearchBox();
});
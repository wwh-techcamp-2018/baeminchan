window.addEventListener("DOMContentLoaded", () => {
    $("#searching_text").addEventListener("focus", autoSearchFocusHandler)
    $("#searching_text").addEventListener("keyup", autoSearchKeyUpHandler)
});

let autoSearchList;

function autoSearchFocusHandler(evt) {
    evt.preventDefault();
    if(autoSearchList === undefined)
        getData("/api/product/search", autoSearchCallback)
}

function autoSearchKeyUpHandler(evt) {
    if(autoSearchList === undefined)
        return;
    if(evt.target.value === "") {
        autoSearchList.removeAutoSearchView($(".auto_search_div"));
        return;
    }
    autoSearchList.appendAutoSearchView($(".auto_search_div"), evt.target.value)

}

function autoSearchCallback(response){
    response.json().then(res => {
        autoSearchList = new AutoSearchList(res.data);
    })
}

class AutoSearchList{
    constructor(data) {
        this.searchList = [];
        for(const searchItem of data){
            this.searchList.push(new SearchItem(searchItem.id, searchItem.keyWord));
        }
    }

    makeAutoSearchView(matchedSearchItems){
        let autoSearchHTML = `<ul class="auto_search_ul">`
        for(const matchedSearchItem of matchedSearchItems){
            autoSearchHTML += matchedSearchItem.makeSearchItemLi();
        }
        autoSearchHTML += `</ul>`;
        return autoSearchHTML;
    }

    appendAutoSearchView(target, searchValue) {
        this.removeAutoSearchView(target);
        const matchedSearchItems = this.getMatchedSearchItems(searchValue);
        if(matchedSearchItems.length === 0)
            return;
        target.insertAdjacentHTML("afterbegin", this.makeAutoSearchView(matchedSearchItems));
    }

    removeAutoSearchView(target) {
        if(target.firstElementChild === null)
            return;
        target.removeChild(target.firstElementChild);
    }

    getMatchedSearchItems(searchValue){
        return this.searchList.filter(item => item.isMatchedSearchItem(searchValue));
    }

    reducer(accumulator, currentValue){
        currentValue
    }
}

class SearchItem {
    constructor(id, keyWord) {
        this.id = id;
        this.keyWord = keyWord;
    }

    makeSearchItemLi(){
        return `<li id = "search_item_${this.id}">${this.keyWord}</li>`
    }

    isMatchedSearchItem(searchValue) {
        return Hangul.search(this.keyWord,searchValue) >= 0 ? true : false;
    }
}
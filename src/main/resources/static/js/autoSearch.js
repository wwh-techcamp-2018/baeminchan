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
    //evt.preventDefault();
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
        //autoSearchList.appendAutoSearchView($(".auto_search_div"));
    })
}

class AutoSearchList{
    constructor(data) {
        this.searchList = [];
        for(const searchItem of data){
            this.searchList.push(new SearchItem(searchItem.id, searchItem.keyWord));
        }
    }

    makeAutoSearchView(searchValue){
        let autoSearchHTML = `<ul class="auto_search_ul">`
        for(const searchItem of this.searchList){
            autoSearchHTML += searchItem.makeSearchItemLi(searchValue);
        }
        autoSearchHTML += `</ul>`;
        return autoSearchHTML;
    }

    appendAutoSearchView(target, searchValue) {
        this.removeAutoSearchView(target);
        if(this.countMatchedSearchItem(searchValue) === 0)
            return;
        target.insertAdjacentHTML("afterbegin", this.makeAutoSearchView(searchValue));
    }

    removeAutoSearchView(target) {
        if(target.firstElementChild === null)
            return;
        target.removeChild(target.firstElementChild);
    }

    countMatchedSearchItem(searchValue){
        let count = 0;
        for(const searchItem of this.searchList){
            if(searchItem.isMatchedSearchItem(searchValue))
                count++;
        }
        return count;
    }
}

class SearchItem {
    constructor(id, keyWord) {
        this.id = id;
        this.keyWord = keyWord;
    }

    makeSearchItemLi(searchValue){
        if(this.isMatchedSearchItem(searchValue))
            return `<li id = "search_item_${this.id}">${this.keyWord}</li>`
        return "";
    }

    isMatchedSearchItem(searchValue) {
        return Hangul.search(this.keyWord,searchValue) >= 0 ? true : false;
    }
}
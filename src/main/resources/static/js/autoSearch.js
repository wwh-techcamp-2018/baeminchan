window.addEventListener("DOMContentLoaded", () => {
    $("#searching_text").addEventListener("focus", autoSearchFocusHandler)
});

let autoSearchList;

function autoSearchFocusHandler(evt) {
    evt.preventDefault();
    //if(autoSearchClass === null)
    getData("/api/product/search", autoSearchCallback)
}

function autoSearchCallback(response){
    response.json().then(res => {
        autoSearchList = new AutoSearchList(res.data);
        autoSearchList.appendAutoSearchView($(".auto_search_div"));
    })
}

class AutoSearchList{
    constructor(data) {
        this.searchList = [];
        for(const searchItem of data){
            this.searchList.push(new SearchItem(searchItem.id, searchItem.keyWord));
        }
    }

    makeAutoSearchView(){
        let autoSearchHTML = `<ul class="auto_search_ul">`
        for(const searchItem of this.searchList){
            autoSearchHTML += searchItem.makeSearchItemLi();
        }
        autoSearchHTML += `</ul>`;
        return autoSearchHTML;
    }

    appendAutoSearchView(target) {
        this.removeAutoSearchView(target);
        target.insertAdjacentHTML("afterbegin", this.makeAutoSearchView());
    }

    removeAutoSearchView(target) {
        target.removeChild(target.firstElementChild);
    }
}

class SearchItem {
    constructor(id, title) {
        this.id = id;
        this.title = title;
    }

    makeSearchItemLi(){
        return `<li id = "search_item_${this.id}">${this.title}</li>`
    }
}
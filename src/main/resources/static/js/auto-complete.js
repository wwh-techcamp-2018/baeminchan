class AutoComplete {
    constructor(){
        this.initEvents();
    }

    initEvents(){
//        $(".search-form").addEventListener("submit", (e) => e.preventDefault());
         document.addEventListener("keydown", (e) => {if (e.keyCode == 13) e.preventDefault()});
        $("#searching_text").addEventListener("keyup", this.keyboardHandlerOnAutoComplete.bind(this));
        document.addEventListener("click", this.changeAutoCompleteDisplay.bind(this));
    }

    keyboardHandlerOnAutoComplete(e) {
        console.log(e.key);
        if (e.key == "Enter" && $(".suggestions .selected") != null) {
            this.fillInSearchBox($(".suggestions .selected"));
            this.clearSuggestions();
            return;
        }


        if (e.key != "ArrowDown" && e.key != "ArrowUp") {
            this.runAutoComplete();
        } else {
            if (e.key == "ArrowDown") {
                this.selectItemDownward();
            } else {
                this.selectItemUpward();
            }
        }
    }

    selectItemUpward() {
        let selectedItem =  $(".suggestions .selected");
        if (!selectedItem) {
            return;
        }
        else if (selectedItem.previousElementSibling) {
            selectedItem.classList.toggle('selected');
            selectedItem.previousElementSibling.classList.toggle('selected');
        }
        else {
            selectedItem.classList.toggle('selected');
        }
    }

    selectItemDownward() {
        let selectedItem =  $(".suggestions .selected");
        if (!selectedItem) {
            $(".suggestions ul").firstElementChild.classList.toggle('selected');
        } else if (selectedItem.nextElementSibling) {
            selectedItem.classList.toggle('selected');
            selectedItem.nextElementSibling.classList.toggle('selected');
        }
    }

    runAutoComplete() {
        const keyword = $("#searching_text").value;
        if (keyword !== "") {
            const url = "/api/products/search/" + keyword;
            this.getSuggestion(url, keyword);
         }
         else {
            this.clearSuggestions();
         }
    }

    renderSuggestions(suggestions, keyword) {
        let suggestionsHTML = ``;
        suggestions.forEach((item) => suggestionsHTML += this.autoCompleteTemplate(item, keyword));
        this.clearSuggestions();
        $(".suggestions ul").insertAdjacentHTML("beforeend", suggestionsHTML);

    }

    changeAutoCompleteDisplay(e) {
        if (e.target.closest(".suggestions") != null) {
            this.fillInSearchBox(e.target.closest('li'));
        }

        this.clearSuggestions();

        if (e.target.name == "searching_text") {
            this.runAutoComplete();
        }

    }

    extractText(liElement) {
        liElement.replaceChild(liElement.firstElementChild.firstChild, liElement.firstElementChild);
        return liElement.innerText;
    }

    fillInSearchBox(liElement) {
        $("#searching_text").value = this.extractText(liElement);
    }

    clearSuggestions() {
        $(".suggestions ul").innerHTML = "";
    }

    autoCompleteTemplate(item, keyword) {
        let index = item.indexOf(keyword);
        const highlightedItem = item.substring(0, index) + '<strong>' + keyword + '</strong>' + item.substring(index+keyword.length);
        return `<li>${highlightedItem}</li>`;
    }

    getSuggestion(url, keyword) {
        fetch (url,
            {method: "GET",
             credentials: 'same-origin'}).then(
                (response) => { if (response.ok)
                                response.json().then((suggestions) => {this.renderSuggestions(suggestions, keyword);});
                                });

//        const suggestions = ["연어", "연어샐러드", "연어장", "연어덮밥", "연어알", "자연어처리", "연어초밥", "훈제연어", "생연어"];
//        return suggestions;
    }
}
document.addEventListener("DOMContentLoaded", () => {
    new AutoComplete();
})
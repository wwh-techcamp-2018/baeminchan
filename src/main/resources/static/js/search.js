class SearchBar {
    constructor() {
        this.inputBox = $('#searching_text');
        this.autocompleteResultBox = $('#autocomplete');
        this.currentTimeout = null;
        this.autoCompleteListItems = [];
        this.currentIndex = -1;
        this.currentWord = "";

        this.registerEvents();
    }

    registerEvents() {
        const KEY_UP = 38;
        const KEY_DOWN = 40;
        const KEY_ENTER = 13;
        this.inputBox.addEventListener('keydown', (evt) => {
            if (evt.keyCode === KEY_UP) {
                this.moveSelectedUp();
                return false;
            }

            if (evt.keyCode === KEY_DOWN) {
                this.moveSelectedDown();
                return false;
            }

            if (evt.keyCode === KEY_ENTER) {
                this.changeInputBoxValue();
                return false;
            }

        });

        this.inputBox.addEventListener('input', (evt) => {
            window.clearTimeout(this.currentTimeout);
            if (this.inputBox.value === "") {
                this.clearAutocompleteResultBox();
                return;
            }

            if (this.inputBox.value !== this.currentWord) {
                this.currentWord = this.inputBox.value;
                this.currentTimeout = window.setTimeout(() =>
                    fetchManager({
                        url: "/api/search/autocomplete?q=" + this.inputBox.value,
                        method: "GET",
                        onSuccess: this.renderAutocompleteResults.bind(this)
                    })
                    , 500);
            }
        });
    }

    moveSelectedUp () {
        const nextIndex = (this.currentIndex - 1) >= 0 ? this.currentIndex-1 : this.autoCompleteListItems.length - 1;
        this.moveSelected(nextIndex);
    }

    moveSelectedDown () {
        const nextIndex = (this.currentIndex + 1) % this.autoCompleteListItems.length;
        this.moveSelected(nextIndex);
        
    }

    moveSelected(nextIndex) {
        if (this.currentIndex >= 0) {
            $('.autocomplete-item-selected').classList.toggle('autocomplete-item-selected');
        }

        this.autoCompleteListItems[nextIndex].classList.toggle('autocomplete-item-selected');

        this.currentIndex = nextIndex;
    }

    changeInputBoxValue () {
        this.currentWord = this.inputBox.value;
        this.inputBox.value = this.autoCompleteListItems[this.currentIndex].textContent;
        this.clearAutocompleteResultBox();
    }



    renderAutocompleteResults({json}) {
        this.autocompleteResultBox.innerHTML = json.data.map(title => `<li class="autocomplete-item">${title.replace(this.currentWord, `<span class="highlighting">${this.currentWord}</span>`)}</li>`).join('\n');
        this.autoCompleteListItems = $All('.autocomplete-item');
        this.currentIndex = -1;
    }

    clearAutocompleteResultBox() {
        this.autocompleteResultBox.innerHTML = '';
    }
}


document.addEventListener('DOMContentLoaded', () => {
    new SearchBar();
})
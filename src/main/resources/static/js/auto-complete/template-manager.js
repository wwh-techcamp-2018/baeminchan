class TemplateManager {
    constructor() {}

    getAutoCompleteParent() {
        return $('.auto-complete');
    }

    getAutoCompleteChildren() {
        return $All('.auto-complete > li');
    }

    getSearchTextElement() {
        return $('#searching_text');
    }

    clearAutoComplete() {
        this.getAutoCompleteChildren().forEach((el) => {
            el.parentElement.removeChild(el);
        });
    }

    appendAutoCompletes(autoCompleteTexts) {
        this.clearAutoComplete();
        const autoCompleteEl = this.getAutoCompleteParent();
        autoCompleteTexts.forEach((text) => {
            autoCompleteEl.insertAdjacentHTML('beforeend', this.extractHighlightingAutoCompleteHTML(text.name));
        });
    }

    extractHighlightingAutoCompleteHTML(name) {
        const searchText = this.getSearchTextElement().value;
        const index = name.indexOf(searchText);
        const prev = name.substr(0, index);
        const match = name.substr(index, searchText.length);
        const next = name.substr(index + searchText.length, name.length);

        return `<li>${prev}<strong>${match}</strong>${next}</li>`
    }
}
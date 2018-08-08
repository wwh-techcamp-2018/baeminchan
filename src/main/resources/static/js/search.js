class SearchManager {
  constructor() {
    this.titles = [];
    this.loadProductTitles();
  }

  initializeTitles(response) {
    response.json().then(titles => {
      this.titles = titles;
    });
  }

  loadProductTitles() {
    fetchManager({
      url: "/api/products/titles",
      method: "GET",
      headers: { "content-type": "application/json" },
      callback: this.initializeTitles.bind(this)
    });
  }

  refreshSearchBox(event) {
    const searchText = event.target.value;
    const searchBox = $("#search-box");
    
    if (isArrowKey(event.key)) return;
    
    removeAllChildren(searchBox);
    resetClassAttribute(searchBox, "on");
    
    if (!isEmpty(searchText)) {
        this.insertSearchBox(searchText);;
    }
  }

  search(word) {
    const searcher = new Hangul.Searcher(word);
    return this.titles.filter(title => searcher.search(title) >= 0);
  }

  navigateSearchBoxUp(event) {
    if (event.key === "ArrowUp") {
      const current = $("#search-box > li.on");
      if (current === null) {
        setClassAttribute($("#search-box").lastElementChild, "on");
        return;
      }
      resetClassAttribute(current, "on");
      setClassAttribute(getPrevElement(current), "on");
    }
  }

  navigateSearchBoxDown(event) {
    if (event.key === "ArrowDown") {
      const current = $("#search-box > li.on");
      if (current === null) {
        setClassAttribute($("#search-box").firstElementChild, "on");
        return;
      }
      resetClassAttribute(current, "on");
      setClassAttribute(getNextElement(current), "on");
    }
  }

  navigateSearchBoxByMouseOver(event) {
    resetClassAttribute($("#search-box > li.on"), "on");
    let current = event.target;
    if (current.tagName === "SPAN") {
      current = current.parentElement;
    }
    setClassAttribute(current, "on");
  }

  insertSearchBox(searchText) {
    const searchBox = $("#search-box");
    this.generateSearchBoxTemplate(this.search(searchText), searchText).forEach(
      li => searchBox.insertAdjacentHTML("beforeEnd", li)
    );
    setClassAttribute(searchBox, "on");
  }

  generateSearchBoxTemplate(searchList, searchText) {
    return searchList.map(title => {
      if (title.includes(searchText)) {
        let startIndex = title.indexOf(searchText);
        let endIndex = startIndex + searchText.length;
        return `<li>${title.slice(0, startIndex)}
                    <span>${title.slice(startIndex,endIndex)}</span>
                ${title.slice(endIndex, title.length)}</li>`;
      }
      return `<li>${title}</li>`;
    });
  }
}

function createSearchManagerOnSearchBarFocus() {
  searchManager = undefined;
  $("#searching_text").addEventListener("focus", () => {
    if (searchManager === undefined) {
      searchManager = new SearchManager();
    }
  });
}

function addKeyEventOnSearchBarHandler() {
  $("#searching_text").addEventListener("keyup", event => {
    searchManager.refreshSearchBox(event);
    searchManager.navigateSearchBoxUp(event);
    searchManager.navigateSearchBoxDown(event);
  });
}

function addMouseOverEventOnSearchBarHandler() {
  $("#search-box").addEventListener("mouseover", event => {
    searchManager.navigateSearchBoxByMouseOver(event);
  });
}

function addSearchEventHandlers() {
  createSearchManagerOnSearchBarFocus();
  addKeyEventOnSearchBarHandler();
  addMouseOverEventOnSearchBarHandler();
}

function changeTabContents(tabName) {
    const parent = $(".tab-content-group-box");
    const children = [...parent.children];
    clearChildrenStyle(parent, "on");

    for (const child of children) {
        if (child.getAttribute("data-tab-name") === tabName) {
            child.classList.add("on");
        }
    }
}

function changeTab(evt) {
    changeTabContents(evt.target.text);
}

const contentTemplate = `
<li>
    <a class="thumbnail-box" href="#">
        <div class="thumbnail">
            <img src="{imgUrl}" alt="{title}">
        </div>
        <dl class="content">
            <dt class="title">{title}</dt>
            <dd class="desc">{description}</dd>
            <dd class="price-wrapper">
                <span class="final-price">
                    <span class="number">{price}</span>
                    <span class="unit">원</span>
                </span>
            </dd>
        </dl>
    </a>
</li>
`;

function fillContentHTML(content) {
    let template = contentTemplate;
    template = template.replace(/{title}/g, content.title)
        .replace(/{imgUrl}/g, content.imgUrl)
        .replace(/{description}/g, content.description)
        .replace(/{price}/g, content.price);
    return template;
}

function insertBestTabContentHTML(tabs) {
    const parent = $(".tab-content-group-box");
    for (const tab in tabs) {
        contents = `<li data-tab-name="${tabs[tab][0]['bestTab']}"><ul class="tab-content-box">`;
        tabs[tab].forEach(content => {
            contents += fillContentHTML(content);
        })
        contents += `</ul></li>`;
        parent.insertAdjacentHTML("beforeEnd", contents);
    }
}

function reducer(accumulator, value) {
    accumulator[value.bestTab] = 
        accumulator[value.bestTab] ? [...accumulator[value.bestTab], value] : [value];
    return accumulator;
}

function organizeTab(products) {
    let tabs = {};
    products.reduce(reducer, tabs);
    return tabs;
}

function productCallback(response) {
    response.json().then(res => {
        insertBestTabContentHTML(organizeTab(res.data));
        focusInitialTab();
    })
}

function focusInitialTab() {
    const parent = $(".tab-btn-box");
    const children = [...parent.children];
    const target = children[Math.floor(Math.random() * 10) % children.length].firstElementChild;
    target.focus();
    changeTabContents(target.text);
}

window.addEventListener("DOMContentLoaded", function (event) {
    getData("/api/product", productCallback);
    initEvents(".tab-btn-box", changeTab);
});
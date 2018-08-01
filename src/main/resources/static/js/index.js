const callMenuApi = () => {
    fetchManager({
        url : 'api/menu',
        method : 'GET',
        callback : setMenu,
    });
}

const setMenu = (status, data) => {
    const root = initList('menu');
    root.setAttribute('id', 'category-menu');

    data.children.forEach(child => {
        const node = setElement(child, './side-dishs.html');
        if(child.childCount !== 0) {
            const childList = initList('sub-menu');
            child.children.forEach(child => {
                const node = setElement(child, '#');
                childList.appendChild(node);
            });
            node.appendChild(childList);
        }
        root.appendChild(node);
    });

    getElement($, idString, 'category-wrapper').appendChild(root);
    root.lastChild.className = 'brand';
};

const initList = (className) => {
    const node = document.createElement('ul');
    node.className = className;
    return node;
};


const setElement = (data, url) => {
    const node = document.createElement('li');
    const urlLink = document.createElement('a');
    urlLink.setAttribute('href', url);
    urlLink.innerText = data.name;
    node.appendChild(urlLink);
    return node;
}

window.addEventListener('load', callMenuApi);
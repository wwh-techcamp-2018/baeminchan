const $ = (selector) => {
    return document.querySelector(selector);
};

const fetchManager = async ({url, method, body, headers, callback}) => {
    const response = await fetch(url, {
        method,
        body,
        headers,
        credentials: "same-origin"
    });
    const data = await response.json();
    callback(response.status, data);
};

const getElementValue = (selector, indexMaker, identifier) => {
    return selector(indexMaker(identifier)).value;
};

const getElements = (selector, indexMaker, identifier) => {
    return selector(indexMaker(identifier));
};

const idString = (input) => {
    return '#' + input;
};

const classString = (input) => {
    return '.' + input;
};





const $ = (selector) => {
    return document.querySelector(selector);
};

const $All = (selector => {
    return document.querySelectorAll(selector);
})

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

const redirect = (url) => {
    return document.location = url;
};

const getElement = (selector, indexMaker, identifier) => {
    return selector(indexMaker(identifier));
};

const idString = (input) => {
    return '#' + input;
};

const classString = (input) => {
    return '.' + input;
};

const prefixMaker = (indexMaker, name, preFix) => {
    return preFix + indexMaker(name);
};

const postfixMaker = (indexMaker, name, postFix) => {
    return indexMaker(name) + postFix;
};






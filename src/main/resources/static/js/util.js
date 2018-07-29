const $ = (selector) => {
    return document.querySelector(selector);
}

// const fetchManager = ({ url, method, body, headers, success, error }) =>{
//     fetch(url, {
//         method,
//         body,
//         headers,
//         credentials: "same-origin"
//     }).then((response) => {
//         return {data : response.json(), status : response.status};
//     }).then((result) => {
//         console.log(result);
//         if(result.status === 201){
//             console.log("call success");
//             success(result.data);
//         } else{
//             console.log("call error");
//             error(result.data);
//         }
//     })
// }
// const fetchManager = async ({ url, method, body, headers, callback }) =>{
//     const response = await fetch(url, {
//         method,
//         body,
//         headers,
//         credentials: "same-origin"
//     });
//     const data = await response.json();
//     callback(response.status, data);
// }

const fetchManager =  ({ url, method, body, headers, callback }) =>{
    fetch(url, {
        method,
        body,
        headers,
        credentials: "same-origin"
    }).then((response) => {
        const status = response.status;
        response.json().then((data) => {
            callback(status, data);
        })
        // return (null, response.json(), response  .status);
    })
        // .then((result, status) => {
        // console.log(result);
        // console.log(status);
        // callback(status, result);
    // })
}

const getElementValue = (selector, indexMaker, identifier) => {
    return selector(indexMaker(identifier)).value;
}

const getElements = (selector, indexMaker, identifier) => {
    return selector(indexMaker(identifier));
}

const idString = (input) => {
    return '#'+input;
}

const classString = (input) => {
    return '.'+input;
}





function $(selector) {
    return document.querySelector(selector);
}

function all(selector) {
  return document.querySelectorAll(selector);
}

function initEvents(selector, handler) {
    const initBtn = $(selector);
    if (initBtn === null) return;
    initBtn.addEventListener("click", handler);
}

function initEventsList(list,handler){
  list.forEach(element => {
      if(element !== null){
        element.addEventListener("click",handler);
      }
  });
}

function fetchManager({ url, method, body, headers, callback }) {
  fetch(url, { method, body, headers, credentials: "same-origin" }).then(
    response => {
      callback(response);
    }
  );
}


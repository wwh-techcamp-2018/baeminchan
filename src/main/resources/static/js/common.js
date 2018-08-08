const arrowKey = ["ArrowUp", "ArrowDown", "ArrowRight", "ArrowLeft"];


function $(selector) {
  return document.querySelector(selector);
}

function $all(selector) {
  return document.querySelectorAll(selector);
}

function isEmpty(value) {
  return value.length === 0;
}

function fetchManager({ url, method, body, headers, callback }) {
  fetch(url, { method, body, headers, credentials: "same-origin" }).then(
    response => {
      callback(response);
    }
  );
}

function removeAllChildren(parent) {
  [...parent.children].forEach(child => child.remove());
}

function setClassAttribute(tag, attribute) {
  if (tag !== null) {
    tag.classList.add(attribute);
  }
}

function resetClassAttribute(tag, attribute) {
  if (tag !== null) {
    tag.classList.remove(attribute);
  }
}

function getNextElement(tag) {
    return tag === tag.parentElement.lastElementChild ?
        tag.parentElement.firstElementChild : tag.nextElementSibling;
}

function getPrevElement(tag) {
    return tag === tag.parentElement.firstElementChild ?
        tag.parentElement.lastElementChild : tag.previousElementSibling;
}

function isArrowKey(key) {
  return arrowKey.includes(key);
}

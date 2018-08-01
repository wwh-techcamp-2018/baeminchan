document.addEventListener('DOMContentLoaded', () =>{
    fetchManager({
      url: '/api/categories',
      method: 'GET',
      headers: { 'content-type': 'application/json'},
      callback: appendAnswer
    });
}, false);

function appendAnswer(data) {
  const outer = ({name}, subMenu) => `<li> <a href="#">${name}</a><ul class="sub-menu"> ${subMenu} </ul></li>`;
  const inner = ({name})=> `<li><a href="#">${name}</a></li>`;

  let resultHTML = data.reduce( function(p,c){
    return p + outer(c, c.children.map((e)=> inner(e)).join());}, "");
  document.querySelector("#nav-menu").insertAdjacentHTML('afterbegin', resultHTML);
}
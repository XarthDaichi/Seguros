/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


var backend = "http://localhost:8080/SegurosBackEnd/api";

var globalstate = {user:null};

function lodaded(){
    app = new App();
    document.querySelector('#root').replaceChildren(app.dom);
}

document.addEventListener("DOMContentLoaded", loaded);

function errorMessage(code){
    alert(`Error. Status: ${code}`);
}
// JavaScript Document
const categoryUrl = 'http://localhost:8080/StoreToys-API/category/';
const brandUrl = 'http://localhost:8080/StoreToys-API/brand/';
const productUrl = 'http://localhost:8080/StoreToys-API/product/';
const categoryItem = document.getElementById("category-name");
const brandItem = document.getElementById("brand-name");
const numberCart = document.querySelector(".number-product");
const profile = document.querySelector(".fa-user-circle");
var tokens = JSON.parse(localStorage.getItem('tokens')); 
const userId = tokens ? tokens.userId : null;
const cartUrl = `http://localhost:80808080/StoreToys-BE/API/cart?user_id=${userId}`;

const urlParams = new URLSearchParams(window.location.search);
const brandName = urlParams.get('brand_name');

const searchForm = document.querySelector(".search-form");

start();

function start(){
	getCategory();
    getBrand();
    getQuantityCart();
	openProfile();
}
function getCategory(){
    fetch(categoryUrl)
    .then(function(res){
        return res.json();
    })  
    .then(function(datas) {
        var htmls = datas.data.map(renderCategory);
        var html = htmls.join('');
        categoryItem.innerHTML = html;
    })
    .catch(error => console.log(error));
}

function renderCategory(data){
	const encodedCategoryName = encodeURIComponent(data.category_name);
    return ` <li class="list-content hover-region">
                 <a href="Category.html?category_name=${encodedCategoryName}" class="content">${data.category_name}</a>
             </li>`;
}

function getBrand(){
    fetch(brandUrl)
    .then(function(res){
        return res.json();
    })
    .then(function(datas){
        var htmls = datas.data.map(renderBrand);
        var html = htmls.join('');
        brandItem.innerHTML = html;
    })
    .catch(error => console.log(error));
}

function renderBrand(data) {
    const encodedBrandName = encodeURIComponent(data.brand_name);
    return `<li class="list-content hover-region">
              <a href="Brand.html?brand_name=${encodedBrandName}" class="content">${data.brand_name}</a>
            </li>`;
}
function performSearch(event) {
    event.preventDefault();
    const searchInput = document.querySelector(".search-input");
    const searchTerm = searchInput.value;
    window.location.href = `Search.html?search=${encodeURIComponent(searchTerm)}`;
}
searchForm.addEventListener("submit", performSearch);

function getQuantityCart(){
    fetch(cartUrl)
    .then(function(res){
        return res.json();
    })
    .then(function(datas){
        let quantityCart = datas.length;
        renderQuantityCart(quantityCart);
    })
    .catch(error => console.log(error));
}

function renderQuantityCart(quantity){
    numberCart.textContent = `(${quantity})`;  
}

function openProfile(){
	if(userId != null){
		profile.href = "Profile.html";
	} else{
		profile.href = "../login/Login.html";
	}
}
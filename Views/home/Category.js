// JavaScript Document
const categoryUrl = 'http://localhost:8080/StoreToys-API/category/';
const brandUrl = 'http://localhost:8080/StoreToys-API/brand/';
const productUrl = 'http://localhost:8080/StoreToys-API/product/category/';
const imgUrl = 'http://localhost:8080/StoreToys-API/img/'
const categoryItem = document.getElementById("category-name");
const brandItem = document.getElementById("brand-name");
const numberCart = document.querySelector(".number-product");
const profile = document.querySelector(".fa-user-circle");
const categoryTitle = document.querySelector(".title");
const user = JSON.parse(localStorage.getItem('user')); 
const userId = user ? user.user_id : null;
const cartUrl = `http://localhost/StoreToys-BE/API/cart?user_id=${userId}`;
const urlParams = new URLSearchParams(window.location.search);
const categoryName = urlParams.get('category_name'); 
const searchForm = document.querySelector(".search-form");
searchForm.addEventListener("submit", performSearch);
start();

function start(){
    getCategory();
    getBrand();
    if (categoryName) { 
      getProductByCategory(decodeURIComponent(categoryName)); 
  } else {
      getProduct();
  };
    getQuantityCart();
	openProfile();
}

function getProductByCategory(categoryName) {
    let urlWithName = `${productUrl}${categoryName}`;
    fetch(urlWithName)
    .then(function(res) {
      return res.json();
    })
    .then(function(datas) {
      var filteredProducts = datas.data;
      filteredProducts.forEach(renderProduct);
    })
    .catch(error => console.log(error));
}

function getCategory(){
    fetch(categoryUrl)
    .then(function(res){
        return res.json();
    })
    .then(function(datas){
        var htmls = datas.data.map(renderCategory);
        var html = htmls.join('');
        categoryItem.innerHTML = html;
    })
    .catch(error => console.log(error));
}

function renderCategory(data) {
    const encodedCategoryName = encodeURIComponent(data.category_name);
    return `<li class="list-content hover-region">
              <a href="Category.html?category_name=${encodedCategoryName}" class="content">${data.category_name}</a>
            </li>`;
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
    searchProduct(searchTerm);
  }
  
  function searchProduct(searchTerm) {
    fetch(productUrl)
      .then(function(res) {
        return res.json();
      })
      .then(function(datas) {
        const regex = new RegExp('.*' + searchTerm + '.*', 'i');
        const filteredProducts = datas.filter(function(product) {
            return regex.test(product.product_name);
        });
        const productList = document.querySelector('.product-list');
        productList.innerHTML = '';
        filteredProducts.forEach(renderProduct);
      })
      .catch(error => console.log(error));
  }



function getProduct(){
    fetch(productUrl)
    .then(function(res){
        return res.json();
    })
    .then(function(datas){
        datas.forEach(renderProduct)
    })
    .catch(error => console.log(error))
}

function renderProduct(data){
    categoryTitle.textContent = categoryName;
    const productList = document.querySelector('.product-list');
    
    const productImg = document.createElement('div');
    productImg.classList.add('product-link-image');
    const img = document.createElement('img');
    img.src = `${imgUrl}${data.product_image}`;
    productImg.appendChild(img);
    
    const productBrand = document.createElement('div');
    productBrand.classList.add('product-link-brand');
    productBrand.textContent = `Thương hiệu: ${data.brand_name}`;
    
    const productName = document.createElement('div');
    productName.classList.add('product-link-name');
    productName.textContent = `Tên sản phẩm: ${data.product_name}`;
    
    const productPrice = document.createElement('div');
    productPrice.classList.add('product-link-price');
    productPrice.textContent = `Giá: ${data.product_price} VNĐ`;
    
    const productLink = document.createElement('a');
    productLink.classList.add('product-link');
    productLink.href = `product.html?id=${data.product_id}`;
    
    productLink.appendChild(productImg);
    productLink.appendChild(productBrand);
    productLink.appendChild(productName);
    productLink.appendChild(productPrice);
    
    const addButton = document.createElement('button');
    addButton.classList.add('add-button');
    addButton.textContent = "Thêm vào giỏ hàng";
    addButton.addEventListener('click', function(){
        addCart(data.product_id);
    });
    const addButtonDiv = document.createElement('div');
    addButtonDiv.classList.add('product-add-button');
    addButtonDiv.appendChild(addButton);
    
    const productInfor = document.createElement('div');
    productInfor.classList.add('product-information');
    productInfor.appendChild(productLink);
    productInfor.appendChild(addButtonDiv);
    
    productList.appendChild(productInfor);
}

function addCart(id){
    let user = JSON.parse(localStorage.getItem('user')); 
    if (!user || !user.user_id) {
        alert("Vui lòng đăng nhập trước khi thêm sản phẩm vào giỏ hàng.");
        return;
    }
    let product = {
        user_id: user.user_id, 
        product_id : id,
        quantity : 1
    }
    let option = {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    }
    fetch(cartUrl, option)
    .then(function(res){
        res.json()
    })
    .then(function(){
        getQuantityCart();
    })
    .catch(error => console.log(error));
}


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


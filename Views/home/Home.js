// JavaScript Document
const categoryUrl = 'http://localhost:8080/StoreToys-API/category/';
const brandUrl = 'http://localhost:8080/StoreToys-API/brand/';
const productUrl = 'http://localhost:8080/StoreToys-API/product/';
const imgUrl = 'http://localhost:8080/StoreToys-API/img/'
const categoryItem = document.getElementById("category-name");
const brandItem = document.getElementById("brand-name");
const numberCart = document.querySelector(".number-product");
const profile = document.querySelector(".fa-user-circle");
const paginationContainer = document.querySelector('.pagination');
const tokens = JSON.parse(localStorage.getItem('tokens')); 
const userId = tokens ? tokens.userId : null;
const cartUrl = `http://localhost:8080/StoreToys-API/cart`;
const productsPerPage = 4;
let currentPage = 1;

const searchForm = document.querySelector(".search-form");
searchForm.addEventListener("submit", performSearch);

start();

function start(){
    setInterval(changeSl, 3000);
    getCategory();
    getBrand();
    getProduct().then(products => {
        renderProductsByPage(products, currentPage);
    });
    getQuantityCart();
    openProfile();
}

function getCategory(){
    fetch(categoryUrl)
    .then(res => res.json())
    .then(datas => {
        const htmls = datas.data.map(renderCategory);
        categoryItem.innerHTML = htmls.join('');
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
    .then(res => res.json())
    .then(datas => {
        const htmls = datas.data.map(renderBrand);
        brandItem.innerHTML = htmls.join('');
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

function getProduct(){
    return fetch(productUrl)
    .then(res => res.json());
}

function renderProductsByPage(products, page) {
    const startIndex = (page - 1) * productsPerPage;
    const endIndex = startIndex + productsPerPage;
    const productsToShow = products.data.slice(startIndex, endIndex);
    const productList = document.querySelector('.product-list');
    productList.innerHTML = ''; 

    productsToShow.forEach(renderProduct);
    renderPagination(Math.ceil(products.data.length / productsPerPage));
}

function renderProduct(data){
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
    if (!tokens) {
        alert("Vui lòng đăng nhập trước khi thêm sản phẩm vào giỏ hàng.");
        return;
    }
    let cart = {
        user_id: userId,
        product_id : id,
        quantity : 1
    }
    let option = {
        method: 'POST',
        headers:{
            'Authorization': `Bearer ${tokens.accessToken}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cart)
    }
    fetch(cartUrl, option)
    .then(res => res.json())
    .then(() => {
        alert("Thêm giỏ hàng thành công")
        getQuantityCart();
    })
    .catch(error => console.log(error));
}

function getQuantityCart(){
    let options = {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${tokens.accessToken}`
        }
    }
    fetch(cartUrl+`/${userId}`, options)
    .then(res => res.json())
    .then(datas => {
        const quantityCart = datas.data.length;
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

function renderPagination(totalPages) {
    paginationContainer.innerHTML = '';

    const prevPageButton = document.createElement('button');
    prevPageButton.classList.add('prev-btn');
    prevPageButton.textContent = 'Trước';
    prevPageButton.addEventListener('click', function() {
        if (currentPage > 1) {
            currentPage--;
            getProduct().then(products => {
                renderProductsByPage(products, currentPage);
            });
        }
    });
    paginationContainer.appendChild(prevPageButton);

    for (let i = 1; i <= totalPages; i++) {
        const pageButton = document.createElement('button');
        pageButton.classList.add('page-btn');
        if (i === currentPage) {
            pageButton.classList.add('active');
        }
        pageButton.textContent = i;
        pageButton.addEventListener('click', function() {
            currentPage = i;
            getProduct().then(products => {
                renderProductsByPage(products, currentPage);
            });
        });
        paginationContainer.appendChild(pageButton);
    }

    const nextPageButton = document.createElement('button');
    nextPageButton.classList.add('next-btn');
    nextPageButton.textContent = 'Sau';
    nextPageButton.addEventListener('click', function() {
        if (currentPage < totalPages) {
            currentPage++;
            getProduct().then(products => {
                renderProductsByPage(products, currentPage);
            });
        }
    });
    paginationContainer.appendChild(nextPageButton);
}

function changeSl(){
    const radios = document.querySelectorAll('input[name="inputslider"]');
    let cr = 0;
    radios.forEach((radio, index) => {
        if(radio.checked){
            cr = index;
            return;
        }
    });
    const nr = (cr + 1) % radios.length;
    radios[nr].checked = true;
}

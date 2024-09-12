// JavaScript Document
const categoryUrl = 'http://localhost/StoreToys-BE/API/category';
const brandUrl = 'http://localhost/StoreToys-BE/API/brand';
const productUrl = 'http://localhost/StoreToys-BE/API/product';
const cartUrl = 'http://localhost/StoreToys-BE/API/cart'
const categoryItem = document.getElementById("category-name");
const brandItem = document.getElementById("brand-name");
const numberCart = document.querySelector(".number-product");
var radios = document.getElementsByName("inputslider");
var cr = 0;
start();

function start(){
	setInterval(changeSl, 3000);
	getCategory();
	getBrand();
	getProduct();
	getQuantityCart();
}

function getCategory(){
	fetch(categoryUrl)
	.then(function(res){
		return res.json();
	})	
	.then(function(datas) {
		var htmls = datas.map(renderCategory);
		var html = htmls.join('');
		categoryItem.innerHTML = html;
	})
	.catch(error => console.log(error));
}

function renderCategory(data){
	return ` <li class="list-content hover-region">
                 <a href="" class="content">${data.category_name}</a>
             </li>`;
}

function getBrand(){
	fetch(brandUrl)
	.then(function(res){
		return res.json();
	})
	.then(function(datas){
		var htmls = datas.map(renderBrand);
		var html = htmls.join('');
		brandItem.innerHTML = html;
	})
	.catch(error => console.log(error));
}

function renderBrand(data){
	return ` <li class="list-content hover-region">
                 <a href="" class="content">${data.brand_name}</a>
             </li>`;
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
	const productList = document.querySelector('.product-list');
	
//	const productItem = document.createElement('div');
//	productItem.classList.add('product-information');
	
	const productImg = document.createElement('div');
	productImg.classList.add('product-link-image');
	const img = document.createElement('img');
	img.src = `../../${data.product_img}`;
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
	let product = {
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

function changeSl(){
	for (var i = 0; i < radios.length; i++){
		if(radios[i].checked){
			cr = i;
			break;
		}
	}
	var nr = (cr+1) % radios.length;
	radios[nr].checked = true;
}





// JavaScript Document
const proUrl = 'http://localhost:8081/StoreToys-API/product/';
const catUrl = 'http://localhost:8081/StoreToys-API/category';
const braUrl = 'http://localhost:8081/StoreToys-API/brand';
const imgUrl = 'http://localhost:8081/StoreToys-API/img/'
const name = document.getElementById('product');
const modal = document.querySelector('.js-modal');
const inputCategory = document.getElementById('category');
const inputBrand = document.getElementById('brand');
const inputName = document.getElementById('product_name');
const inputQuantity = document.getElementById('product_quantity');
const inputImg = document.getElementById('product_img');
const inputSex = document.getElementById('product_sex');
const inputPrice = document.getElementById('product_price');
const btnConfirmUpdate = document.getElementById('update');
const categorySelect = document.querySelector('select[name="category_id"]');
const brandSelect = document.querySelector('select[name="brand_id"]');
const updateForm = document.getElementById('product-update-form');
var storedToken = JSON.parse(localStorage.getItem('tokens'));
let index = 0;
var ID = 0;
document.addEventListener('DOMContentLoaded', start);

function start() {    
    getProduct();
    
}
function getProduct() {
    fetch(proUrl)
        .then(res => {
            if (!res.ok) {
                throw new Error('Network response was not ok');
            }
            return res.json();
        })
        .then(datas => {
            console.log(datas)
            index = 0;
            const htmls = datas.data.map(renderProduct);
            const html = htmls.join('');
            name.innerHTML = html;
            
        })
        .catch(error => console.error('Error fetching data:', error));
    
}

function renderProduct(data) {
    const stt = ++index;
    return `<tr>
                <th scope="row">${stt}</th>
                <td>${data.product_name}</td>
                <td><img src="${imgUrl}${data.product_image}" alt="" style="max-width: 100px; max-height: 100px;"></td>
                <td>${data.product_sex}</td>
                <td>${data.product_price}</td>
                <td>${data.product_quantity}</td>
                <td>${data.category_name}</td>
                <td>${data.brand_name}</td>
                
                <td>
                    <button class="btn btn-primary" onclick="handleUpdateProduct(${data.product_id})">Update</button>
                    <button class="btn btn-primary" onclick="handleDeleteProduct(${data.product_id})">Delete</button>
                </td>
            </tr>`;
}

function handleDeleteProduct(id) {
    deleteProduct(id);
}
function deleteProduct(id) {
    let urlWithID = `${proUrl}${id}`;
    let options = {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${storedToken.accessToken}`
        },
    };
    fetch(urlWithID, options)
        .then(res => {
            if (!res.ok) {
                throw new Error('Network response was not ok');
            }
            return res.json();
        })
        .then(() => {
            getProduct(); 
        })
        .catch(error => console.error('Error deleting product:', error));
}


function renderProductByID(datas){
    console.log(datas.data.category_name);
    console.log(datas.data.brand_name);
    inputName.value = datas.data.product_name;
    inputQuantity.value = datas.data.product_quantity;
    setValueForCatSelect(datas.data.category_name);
    setValueForBraSelect(datas.data.brand_name)
    inputSex.value = datas.data.product_sex;
    inputPrice.value = datas.data.product_price;
}
function getProductByID(id){
    let urlWithID = `${proUrl}${id}`;
    fetch(urlWithID)
    .then(function(res){
        return res.json();
    })
    .then(function(datas){
        renderProductByID(datas);
    })
    .catch(error => console.log(error));
}

function handleUpdateProduct(id){
    fetchCategoryAndBrand();
    getProductByID(id);
    modal.classList.add('open');
    ID = id;
}

btnConfirmUpdate.addEventListener('click', function(event){
    event.preventDefault();
    let product = new FormData(updateForm);
    updateProduct(product);
    hiddenUpdateProduct();
});

function updateProduct(data){
    let urlWithID = `${proUrl}${ID}`;
    console.log(storedToken.accessToken)
    let options = {
        method: 'PUT',
        headers:{
            'Authorization': `Bearer ${storedToken.accessToken}`
        },
        body: data
    } 
    fetch(urlWithID, options)
    .then(function(res){
        res.json();
    })
    .then(function(){
        getProduct();
    })
}

function hiddenUpdateProduct(){
    modal.classList.remove('open');
}
function fetchCategoryAndBrand() {
    fetch(catUrl)
    .then(response => response.json())
    .then(categories => {
        categories.data.forEach(category => {
            const option = document.createElement('option');
            option.value = category.category_id;
            option.textContent = category.category_name;
            categorySelect.appendChild(option);
        });
    })
    .catch(error => console.error('Error fetching categories:', error));
	
    fetch(braUrl)
        .then(response => response.json())
        .then(brands => {
            brands.data.forEach(brand => {
                const option = document.createElement('option');
                option.value = brand.brand_id;
                option.textContent = brand.brand_name;
                brandSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching brands:', error));
}

function setValueForCatSelect(a){
    // Thiết lập giá trị mặc định cho <select> dựa trên category_name
    const options = categorySelect.options;
    
    for (let i = 0; i < options.length; i++) {
        if (options[i].textContent === a) {
            categorySelect.selectedIndex = i; // Thiết lập giá trị mặc định
            break;
        }
    }
}
function setValueForBraSelect(a){
    // Thiết lập giá trị mặc định cho <select> dựa trên category_name
    const options = brandSelect.options;
    
    for (let i = 0; i < options.length; i++) {
        if (options[i].textContent === a) {
            brandSelect.selectedIndex = i; // Thiết lập giá trị mặc định
            break;
        }
    }
}

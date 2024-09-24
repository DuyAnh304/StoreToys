const userUrl = 'http://localhost:8080/StoreToys-API/user/';
const cartUrl = 'http://localhost:8080/StoreToys-API/cart/';
const imgUrl = 'http://localhost:8080/StoreToys-API/img/'
const ordUrl = 'http://localhost:8080/StoreToys-API/order';
var storedToken = JSON.parse(localStorage.getItem('tokens'));
const nameInput = document.querySelector('input[name="name"]');
const phoneInput = document.querySelector('input[name="phone"]');
const addressInput = document.querySelector('input[name="address"]');
const noteInput = document.querySelector('textarea[name="note"]');
const table = document.querySelector(".product-list");
const sum = document.querySelector(".order-sum");
const btnOrder = document.querySelector('.pay-button');
let cartItems = [];
start();

function start() {
    getUserByID(storedToken.userId);
    getCartItems();
}

function getCartItems() {
    let url = `${cartUrl}${storedToken.userId}`;
    let options = {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${storedToken.accessToken}`
        }
    }
    fetch(url, options)
        .then(response => response.json())
        .then(datas => {
            datas.data.forEach(renderCartItems);
            sum.textContent = totalMoney(datas.data) +'đ';
            cartItems = datas.data.map(cart =>{
                return {
                    product_id: cart.product.product_id,
                    quantity: cart.quantity,
                    total_price: cart.product.product_price * cart.quantity
                }
            })
        })
        .catch(error => console.error('Error:', error));
}

function totalMoney(item){
    let sum = 0;
    for(let i = 0; i< item.length; i++){
        sum += item[i].product.product_price* item[i].quantity;
    }
    return sum;
}

function renderCartItems(item){
    const cartList = document.createElement('tr');
    const productImgInf = document.createElement('td');
    productImgInf.classList.add('product-img');

    const productImg = document.createElement('img');
    productImg.src = `${imgUrl}${item.product.product_image}`;

    const productQuantity = document.createElement('span');
    productQuantity.classList.add('product-quantity');
    productQuantity.textContent = item.quantity;

    const productName = document.createElement('td');
    productName.classList.add('product-name');
    productName.textContent = item.product.product_name;

    const productMoney = document.createElement('td');
    productMoney.classList.add('product-money');
    productMoney.textContent = item.product.product_price * item.quantity +'đ';

    productImgInf.appendChild(productImg);
    productImgInf.appendChild(productQuantity);
    cartList.appendChild(productImgInf);
    cartList.appendChild(productName);
    cartList.appendChild(productMoney);

    table.appendChild(cartList);
}

function getUserByID(id) {
    let urlWithID = `${userUrl}${id}`;
    let options = {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${storedToken.accessToken}`
        }
    }
    fetch(urlWithID, options)
        .then(function (res) {
            return res.json();
        })
        .then(function (datas) {
            nameInput.value = datas.data.fullname;
            phoneInput.value = datas.data.phone;
            addressInput.value = datas.data.address;
        })
        .catch(error => console.log(error));
}

btnOrder.addEventListener('click', function(event){
    event.preventDefault();
    let userId = storedToken.userId;
    let customerName = nameInput.value;
    let customerPhone = phoneInput.value;
    let customerAddress = addressInput.value;
    let note = noteInput.value;
    let data = {
        user_id: userId,
        customer_name: customerName,
        customer_phone: customerPhone,
        customer_address: customerAddress,
        note: note,
        cart: cartItems
    }
    console.log(cartItems);
    console.log(data)
    order(data);
});

function order(data){
    let option = {
		method: 'POST',
        headers:{
			'Authorization': `Bearer ${storedToken.accessToken}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
	}
	fetch(ordUrl, option)
	.then(function(res){
        res.json();
    })
    .then(function(){
		// window.location.href = '../home/Home.html'
    })
	.catch(error => console.error(error));
}
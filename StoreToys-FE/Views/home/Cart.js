const categoryUrl = 'http://localhost:8080/StoreToys-API/category/';
const brandUrl = 'http://localhost:8080/StoreToys-API/brand/';
const imgUrl = 'http://localhost:8080/StoreToys-API/img/'
const categoryItem = document.getElementById("category-name");
const brandItem = document.getElementById("brand-name");
const cartContainer = document.querySelector(".cart-container");
const totalQuantity = document.getElementById('totalQuantity');
const totalPrice = document.getElementById('totalPrice');
const profile = document.querySelector(".fa-user-circle");
const numberCart = document.querySelector(".number-product");
var index = 0;
const tokens = JSON.parse(localStorage.getItem('tokens'));
const userId = tokens ? tokens.userId : null;
const cartUrl = `http://localhost:8080/StoreToys-API/cart/${userId}`;
const cartUpdateUrl = 'http://localhost:8080/StoreToys-API/cart/';
const urlParams = new URLSearchParams(window.location.search);
const brandName = urlParams.get('brand_name');

const searchForm = document.querySelector(".search-form");

window.onload = function () {
    getCategory();
    getBrand();
    getQuantityCart();
    getCartItems();
    openProfile();
    let cartItems = [];
    let sumQuantity = 0;
    let sumPrice = 0;

    function getCartItems() {
        let options = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${tokens.accessToken}`
            }
        }
        fetch(cartUrl, options)
            .then(response => response.json())
            .then(datas => {
                if (datas.data && datas.data.length > 0) {
                    cartItems = datas.data;
                    sumPrice = 0;
                    datas.data.forEach(item => {
                        sumQuantity += item.quantity;
                        sumPrice += parseInt(item.product.product_price * item.quantity);
                    });
                    renderCartItems(sumPrice);
                } else {
                    renderCarEmpty();
                }
            })
            .catch(error => console.error('Error:', error));
    }

    function performSearch(event) {
        event.preventDefault();
        const searchInput = document.querySelector(".search-input");
        const searchTerm = searchInput.value;
        window.location.href = `Search.html?search=${encodeURIComponent(searchTerm)}`;
    }
    searchForm.addEventListener("submit", performSearch);

    function getCategory() {
        fetch(categoryUrl)
            .then(res => res.json())
            .then(datas => {
                var htmls = datas.data.map(renderCategory);
                var html = htmls.join('');
                categoryItem.innerHTML = html;
            })
            .catch(error => console.log(error));
    }

    function renderCategory(data) {
        const encodedCategoryName = encodeURIComponent(data.category_name);
        return ` <li class="list-content hover-region">
                 	<a href="Category.html?category_name=${encodedCategoryName}" class="content">${data.category_name}</a>
             	</li>`;
    }

    function getBrand() {
        fetch(brandUrl)
            .then(res => res.json())
            .then(datas => {
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

    function getQuantityCart() {
        let options = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${tokens.accessToken}`
            }
        }
        fetch(cartUrl, options)
            .then(res => res.json())
            .then(datas => {
                let quantityCart = datas.data.length;
                renderQuantityCart(quantityCart);
            })
            .catch(error => console.log(error));
    }

    function renderQuantityCart(quantity) {
        numberCart.textContent = `(${quantity})`;
    }

    function renderCarEmpty() {
        cartContainer.innerHTML = '';
        const cartEmpty = document.createElement('div');
        cartEmpty.classList.add('cart-empty');
        const emptyImg = document.createElement('img');
        emptyImg.src = '../../Assets/image/no-cart.png';
        const cartNotify = document.createElement('div');
        cartNotify.textContent = 'Giỏ hàng của bạn đang trống';
        const homeBtn = document.createElement('a');
        homeBtn.href = 'Home.html';
        homeBtn.textContent = 'Tiếp tục mua hàng';
        cartEmpty.appendChild(emptyImg);
        cartEmpty.appendChild(cartNotify);
        cartEmpty.appendChild(homeBtn);
        cartContainer.appendChild(cartEmpty);
    }

    function renderCartItems(sumPrice) {
        cartContainer.innerHTML = '';
        const cartTable = document.createElement('table');
        cartTable.border = 2;
        const cartInfor = document.createElement('tr');

        const cartIndex = document.createElement('th');
        cartIndex.classList.add('product-number', 'product-title');
        cartIndex.textContent = 'STT';

        const cartName = document.createElement('th');
        cartName.classList.add('product-name', 'product-title');
        cartName.textContent = 'Tên sản phẩm';

        const cartImg = document.createElement('th');
        cartImg.classList.add('product-img', 'product-title');
        cartImg.textContent = 'Ảnh';

        const cartPrice = document.createElement('th');
        cartPrice.classList.add('product-price', 'product-title');
        cartPrice.textContent = 'Đơn giá';

        const cartQuantity = document.createElement('th');
        cartQuantity.classList.add('product-quantity', 'product-title');
        cartQuantity.textContent = 'Số lượng';

        const cartMoney = document.createElement('th');
        cartMoney.classList.add('product-money', 'product-title');
        cartMoney.textContent = 'Thành tiền';

        const cartOption = document.createElement('th');
        cartOption.classList.add('product-delete', 'product-title');
        cartOption.textContent = 'Tùy chọn';

        cartInfor.appendChild(cartIndex);
        cartInfor.appendChild(cartName);
        cartInfor.appendChild(cartImg);
        cartInfor.appendChild(cartPrice);
        cartInfor.appendChild(cartQuantity);
        cartInfor.appendChild(cartMoney);
        cartInfor.appendChild(cartOption);
        cartTable.appendChild(cartInfor);

        cartItems.forEach(item => {
            const cartList = document.createElement('tr');
            index++;
            const productIndex = document.createElement('td');
            productIndex.classList.add('product-number');
            productIndex.textContent = index;

            const productName = document.createElement('td');
            productName.classList.add('product-name');
            productName.textContent = `${item.product.product_name}`;

            const productImg = document.createElement('td');
            const img = document.createElement('img');
            productImg.classList.add('product-img');
            img.src = `${imgUrl}${item.product.product_image}`;
            productImg.appendChild(img);

            const productPrice = document.createElement('td');
            productPrice.classList.add('product-price');
            productPrice.textContent = `${item.product.product_price} VNĐ`;

            const productQuantity = document.createElement('td');
            const inputQuantity = document.createElement('input');
            productQuantity.classList.add('product-quantity');

            inputQuantity.type = 'text';
            inputQuantity.value = `${item.quantity}`;
            // inputQuantity.dataset.productId = item.product_id; // Lưu productId vào dataset
            inputQuantity.addEventListener('blur', (event) => {
                let cartId = item.cart_id;
                let data = {
                    product_id: item.product.product_id,
                    user_id: userId,
                    quantity: event.target.value
                };
                console.log(cartId, data);
                updateCartItems(cartId, data);
            })
            productQuantity.appendChild(inputQuantity);

            const productMoney = document.createElement('td');
            productMoney.classList.add('product-price');
            let money = item.product.product_price * item.quantity;
            productMoney.textContent = `${money} VNĐ`;

            const productOption = document.createElement('td');
            productOption.classList.add('product-delete');
            // productOption.dataset.productId = item.product_id; // Lưu productId vào dataset
            // productOption.dataset.productPrice = item.product_price; // Lưu productPrice vào dataset
            const btnDelete = document.createElement('button');
            btnDelete.textContent = 'Xóa sản phẩm';
            btnDelete.classList.add('delete-button');
            btnDelete.addEventListener('click', (event) => {
                let cartId = item.cart_id;
                removeCartItem(cartId);
            })
            productOption.appendChild(btnDelete);

            cartList.appendChild(productIndex);
            cartList.appendChild(productName);
            cartList.appendChild(productImg);
            cartList.appendChild(productPrice);
            cartList.appendChild(productQuantity);
            cartList.appendChild(productMoney);
            cartList.appendChild(productOption);
            cartTable.appendChild(cartList);
        });

        const totalTR = document.createElement('tr');
        const emptyTH = document.createElement('th');
        emptyTH.classList.add('product-number', 'product-title');
        const totalMoney = document.createElement('th');
        totalMoney.classList.add('product-number', 'product-title');
        totalMoney.colSpan = 6;
        totalMoney.textContent = `Tổng tiền: ${sumPrice} VNĐ`;

        const payment = document.createElement('div');
        payment.classList.add('cart-empty');
        payment.style.marginTop = '50px';
        const paymentBtn = document.createElement('a');
        paymentBtn.href = 'Order.html';
        paymentBtn.textContent = 'Tiến hành thanh toán';
        payment.appendChild(paymentBtn);


        totalTR.appendChild(emptyTH);
        totalTR.appendChild(totalMoney);
        cartTable.appendChild(totalTR);

        cartContainer.appendChild(cartTable);
        cartContainer.appendChild(payment);
    }

    function updateCartItems(cartId, data) {
        let url = `${cartUpdateUrl}${cartId}`;       
        let options = {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${tokens.accessToken}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }
        fetch(url, options)
            .then(function (res) {
                res.json();
            })
            .then(function () {
                alert('Cập nhật thành công');
                getCartItems();
            })
            .catch(error => console.error(error));
    }

    // function updateCartItems(userId, updates) {
    //     const updatePromises = updates.map(update => {
    //         return fetch(`http://localhost:8080/StoreToys-API/cart/`, {
    //             method: 'PATCH',
    //             headers: {
    //                 'Content-Type': 'application/json'
    //             },
    //             body: JSON.stringify({ quantity: update.quantity })
    //         })
    //         .then(response => response.json())
    //         .then(data => {
    //             const updatedItem = cartItems.find(item => item.product_id === update.id);
    //             if (updatedItem) {
    //                 updatedItem.quantity = update.quantity;
    //             }
    //         })
    //         .catch(error => console.error('Error:', error));
    //     });

    //     Promise.all(updatePromises)
    //         .then(() => {
    //             sumQuantity = 0;
    //             sumPrice = 0;
    //             cartItems.forEach(item => {
    //                 sumQuantity += item.quantity;
    //                 sumPrice += parseInt(item.product_price * item.quantity);
    //             });

    //             if (totalQuantity && totalPrice) {
    //                 totalQuantity.textContent = sumQuantity;
    //                 totalPrice.textContent = sumPrice.toLocaleString('vi-VN') + ' VNĐ';
    //             }
    //             getCartItems();
    // 		    getQuantityCart();
    //         })
    //         .catch(error => console.error('Error:', error));
    // }

    function removeCartItem(id) {
        let url = `${cartUpdateUrl}${id}`;
        let options = {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${tokens.accessToken}`,
            },
        }
        fetch(url, options)
            .then(response => response.json())
            .then(data => {
                getCartItems();
                getQuantityCart();
            })
            .catch(error => console.error('Error:', error));
    }

    function openProfile() {
        if (userId != null) {
            profile.href = "Profile.html";
        } else {
            profile.href = "../login/Login.html";
        }
    }
};

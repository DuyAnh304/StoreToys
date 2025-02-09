window.onload = function() {
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('id');
    const user = JSON.parse(localStorage.getItem('user'));
    const userId = user ? user.user_id : null;
    const numberCart = document.querySelector(".number-product");
    console.log(userId);
    fetch(`http://localhost/StoreToys-BE/API/product?id=${productId}`)
        .then(response => response.json())
        .then(product => {
            document.getElementById("productImage").src = `../../${product.product_img}`;
            document.querySelector(".product-name").textContent = product.product_name;
            document.querySelector(".product-price").textContent = `Giá: ${product.product_price} VNĐ`;
            document.querySelector(".product-category").textContent = `Danh mục: ${product.category_name}`;
            document.querySelector(".product-brand").textContent = `Thương hiệu: ${product.brand_name}`;
            document.querySelector(".product-sex").textContent = `Giới tính: ${product.product_sex}`;

            const addToCartBtn = document.getElementById("addToCartBtn");
            addToCartBtn.addEventListener('click', function() {
                addToCart(product.product_id, userId); 
            });
        })
        .catch(error => console.error('Error:', error));

    function addToCart(productId, userId) {
        if (!userId) {
            alert('Vui lòng đăng nhập để thêm sản phẩm vào giỏ hàng!');
            return;
        }

        fetch('http://localhost/StoreToys-BE/API/cart', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({user_id: userId, product_id: productId, quantity: 1 })
            })
            .then(response => {
                if (response.ok) {
                    alert('Đã thêm sản phẩm vào giỏ hàng thành công!');
                    getQuantityCart()
                } else {
                    alert('Thêm sản phẩm vào giỏ hàng thất bại!');
                }
            })
            .catch(error => console.error('Error:', error));
    }
    function getQuantityCart(){
        fetch(cartUrl+`?user_id=${userId}`)
        .then(res => res.json())
        .then(datas => {
            const quantityCart = datas.length;
            renderQuantityCart(quantityCart);
        })
        .catch(error => console.log(error));
    }
    
    function renderQuantityCart(quantity){
        numberCart.textContent = `(${quantity})`;  
    }
};

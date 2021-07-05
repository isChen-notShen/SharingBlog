const token = getToken();
const userInfo = getUserInfo();

if (token !== undefined && token !== '') {
    hideNavbarItem('#login');
    showNavbarItem('#after-login');
}

if (userInfo !== undefined){
    $('#after-login img').prop('src','/user/portrait?userId=' + userInfo.userId)
}

function showNavbarItem(selector) {
    let item = $(selector);
    item.removeClass('d-none');
    item.addClass('d-flex');
}

function hideNavbarItem(selector) {
    let item = $(selector);
    item.removeClass('d-flex');
    item.addClass('d-none');
}

function addCookie(name, value, expires, path) {
    let cookie = '';
    if (name !== undefined && name !== '' && value !== undefined && value !== '') {
        cookie = name + '=' + value;
    }
    if (expires !== undefined && expires !== '') {
        cookie += ';' + 'expires=' + expires;
    }
    if (path !== null && path !== '') {
        cookie += ';' + 'path=' + path
    }
    if (cookie !== '') {
        document.cookie = cookie;
        return true;
    }
    return false;
}

function getCookie(cookeName) {
    let name = cookeName + "=";
    let ca = document.cookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i].trim();
        if (c.indexOf(name) === 0)
            return c.substring(name.length, c.length);
    }
    return "";
}

function removeCookie(name) {
    if (getCookie(name) === "") {
        return false;
    }
    let exp = new Date();
    exp.setTime(exp.getTime() - 1);
    document.cookie = name + '=;' + 'expires=' + exp.toUTCString() + ";path=/"
    return true;
}

function saveToken(token) {
    let exp = new Date();
    exp.setDate(exp.getDate() + 7)
    addCookie('token', token, exp.toUTCString(), '/')
}

function getToken() {
    return getCookie('token')
}

function cleanToken() {
    return removeCookie('token');
}

function saveUserInfo(userInfo) {
    userInfo = JSON.stringify(userInfo)
    localStorage.userInfo = userInfo
}

function getUserInfo() {
    return JSON.parse(localStorage.userInfo)
}

function cleanUserInfo(){
    localStorage.removeItem('userInfo')
}
function fn(){
    console.log("excuting fn()");
}

function fn1(method){
    console.log(typeof method);
    console.log(method instanceof Object);
    method.call();
}

fn1(() =>{console.log("test")});
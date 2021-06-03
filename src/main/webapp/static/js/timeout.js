function fn(){
    console.log("A");

    //该函数创建了一个子线程，在给定的时间后，执行回调函数
    setTimeout(() => {console.log("B");},3000);

    console.log("C");
}

fn();
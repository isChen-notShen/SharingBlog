function print(delay, message) {
	return new Promise(function (resolve, reject) {
		setTimeout(function () {
			console.log(message)
			resolve()
			//console.log("after");
		}, delay)
	})
}

print(1000, 'First')
	.then(function () {
		//如果在then中返回一个Promise，之后的then将基于该Promise执行
		//但其余子句基于原来的Promise执行
		return print(4000, 'Second')
	})
	.then(function () {
		print(3000, 'Third')
	})
	.finally(function () {
		print(2000, 'Default')
	})

// new Promise(function (resolve, reject) {
//     console.log("A");
//     //只有在调用resolve后，then,finally才会执行
//     resolve();
// }).then(function (value) {
//     console.log("B");
// }).catch(function (err) {
//     console.error(err);
// }).finally(function () {
//     console.log("default");
// })

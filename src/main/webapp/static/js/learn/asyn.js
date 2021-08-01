/**
 * async关键词和await操作符
 * await操作符后必须跟Promise对象
 */
function print(delay, message) {
	return new Promise(function (resolve, reject) {
		setTimeout(function () {
			console.log(message)
			resolve()
			//console.log("after");
		}, delay)
	})
}

async function asyncFunc() {
	await print(1000, 'First')
	await print(4000, 'Second')
	await print(3000, 'Third')
}

asyncFunc()

/**
 * 对象冒充机制实现继承
 * @param {*} arg 一个需要被继承的参数
 */
// function ClassA(arg) {
// 	this.arg = arg
// }

// function ClassB(arg) {
// 	this.classA = ClassA
// 	this.classA(arg)
// 	delete this.classA

// 	this.arg = 'Chen'
// }

// var object = new ClassB('Shen')
// console.log(object.arg)

/**
 * 与对象冒充等效方式实现继承
 * 除了可用call方法外，还可以使用apply方法
 * 两者的差异只在于apply方法的第二个参数需要传入一个参数数组
 * @param {*} arg
 */
// function ClassA(arg) {
// 	this.arg = arg
// }

// function ClassB(arg) {
// 	ClassA.call(this, arg)
// }

// var object = new ClassB('Chen')
// console.log(object.arg)

/**
 * 基于对象冒充和原型链的组合继承
 * 通过原型链可以用instanceof得到对象的继承关系
 * 但对于对象具体是哪个类需要我们自己设置的constructor属性判断
 * 下面这种写法的确定在于，需要两次创建ClassA对象
 * @param {*} arg
 */
// function ClassA(arg) {
// 	this.arg = arg
// }

// ClassA.prototype.setArg = function (arg) {
// 	this.arg = arg
// }

// function ClassB(arg, arg2) {
// 	ClassA.call(this, arg)
// 	this.arg2 = arg2
// }

// ClassB.prototype = new ClassA()
// ClassB.prototype.constructor = ClassB
// var object = new ClassB('Chen', 'Shen')
// console.log(object.arg)
// console.log(object.arg2)
// console.log(object instanceof ClassB)
// console.log(object instanceof ClassA)
// console.log(object.constructor)

/**
 * 这是上面继承方式的一种改良，但这种继承
 * 方式有个缺点。ClassB的prototype属性和ClassA的
 * protoType属性指向的同一个对象
 * 所有我们无法通过指定constructor属性来判断类型
 * 因为这会导致两个类的constructor都是一个类型
 * @param {*} arg
 */
// function ClassA(arg) {
// 	this.arg = arg
// }

// ClassA.prototype.setArg = function (arg) {
// 	this.arg = arg
// }

// function ClassB(arg, arg2) {
// 	ClassA.call(this, arg)
// 	this.arg2 = arg2
// }

// ClassB.prototype = ClassA.prototype
// var o = new ClassB('Chen', 'Shen')
// console.log(o.arg)
// console.log(o.arg2)
// console.log(o instanceof ClassA)
// console.log(o instanceof ClassB)
// console.log(o.constructor)
// o.setArg('new Arg')
// console.log(o.arg)

/**
 * 这种方法利用Object.create()函数创建了一个父类prototype
 * 的副本，这样就不用担心，子类在重写prototype属性时对父类的污染
 * @param {*} arg
 */
function ClassA(arg) {
	this.arg = arg
}

ClassA.prototype.setArg = function (arg) {
	this.arg = arg
}

function ClassB(arg, arg2) {
	ClassA.call(this, arg)
	this.arg2 = arg2
}

ClassB.prototype = Object.create(ClassA.prototype)
ClassB.prototype.constructor = ClassB
var objectA = new ClassA('Ling')
var objectB = new ClassB('Chen', 'Shen')
console.log(objectA.arg)
console.log(objectB.arg)
console.log(objectB.arg2)
console.log(objectA instanceof ClassA)
console.log(objectA instanceof ClassB)
console.log(objectB instanceof ClassA)
console.log(objectB instanceof ClassB)
console.log(objectA.constructor)
console.log(objectB.constructor)

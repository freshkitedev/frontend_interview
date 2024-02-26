// map, filter, reduce



// Array.prototype.myMap = function(cb) {
//     let temp = []
//     console.log(this);
//     for (let i = 0; i < this.length; i++) {
//         temp.push(cb(this[i], i, this));
//     }
//     return temp;
// }

// const arr = [1,2,3,4]

// function add3(val, index, arr) {
//     console.log(val, index, arr);
//     return val + 3;

// }
// const output = arr1.myMap(add3)
// console.log("output:", output);

// const arr = [1,2,3,4]
// Array.prototype.myFilter = function(cb) {
//     let temp = [];
//     for (let i = 0; i < this.length; i++) {
//         if (cb(this[i], i, this)) {
//             temp.push(this[i]);
//         }
//     }
//     return temp;
    
// }
// function greaterThan2(val, index, arr) {
//     return val > 2;
// }
// const output = arr.myFilter(greaterThan2);
// console.log("output:", output);

const arr = [1,2,3,4]

Array.prototype.myReduce = function(cb, initialVal) {
    let acc = initialVal;
    for (let i = 0; i < this.length; i++) {
        acc = cb(acc, this[i]);
    }
    return acc;
}

function sum(acc, curr) {
    return acc + curr;
}
const output = arr.myReduce(sum, 0);
console.log("output:", output);

// const users = [
//     {firstName:"Bala", lastName:"Shanmugam", rollNo:1, age:26},
//     {firstName:"Dhakshan", lastName:"pandi", rollNo:2, age:45},
//     {firstName:"Divya", lastName:"Raghu", rollNo:3, age:26},
//     {firstName:"Ram", lastName:"Ranga", rollNo:4, age:56},
// ]



// // Return total age of students who age is > 60 after adding 20 to their ages.

// //const output = users.map((val) => val.age + 20).filter((val) => val > 60).reduce((acc, curr) => acc + curr, 0)

// const output = users.reduce((acc, curr) => {
//     if (curr.age + 20 > 60) {
//         acc += curr.age + 20;
//     }
//     return acc;
// }, 0)
// console.log(output);
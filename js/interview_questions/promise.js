function promiseCallback(res, rej) {
    setTimeout(() => {
        res("Promise1 resolved")
    }, 5000)
}

function promiseCallback2(res, rej) {
    setTimeout(() => {
        res("Promise2 resolved")
    }, 2000)
}

const p1 = new Promise(promiseCallback);
const p2 = new Promise(promiseCallback2);
async function handlePromise() {
    const val1 = await p1;
    console.log("Bala1");
    console.log(val1);
    const val2 = await p2;
    console.log("Bala2");
    console.log(val2);
}
handlePromise()
console.log("Bala end");



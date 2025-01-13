function lengthOrValue(myParam: number | string): number{
    if(typeof (myParam) === "number"){
        return myParam;
    }
    if(typeof (myParam) === "string"){
        return myParam.length;
    }

    return 0;
}
console.log(lengthOrValue(5));
console.log(lengthOrValue("something value"));
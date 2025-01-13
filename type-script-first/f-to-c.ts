function farenhit_to_celcius(farenhit: number): string {
    let celcius: number =(farenhit -32)*(5/9);
    return `${farenhit}F=${celcius}C`;
}

let currentTemperature: number =50;
let tempMessage: string = farenhit_to_celcius(currentTemperature);
console.log(tempMessage);
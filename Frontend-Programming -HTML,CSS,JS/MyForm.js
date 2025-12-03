alert("Welcome to JS file");

let userName = "Rohan";
let age = 24;
let password = "Rohan@123";

//VALIDATION
function validateForm(userName,age, password)
{
let userNameType = typeof userName;
let ageType = typeof age;
let passwordType = typeof password;

console.log("Username: "+userName+" Datatype: "+userNameType);
console.log("Age "+age+" Datatype: "+ageType);
console.log("Password "+password+" Datatype: "+passwordType);

//Conversion
password = Number(password);
console.log("After conversion : "+typeof(password));

}

validateForm(userName,age,password);

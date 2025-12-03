document.getElementById("myForm").addEventListener('submit',function(event)
{
    event.preventDefault(); //prevents default page submission
    let getUserName = document.getElementById('username').value;
    let getPassword = document.getElementById('password').value;

    

    if(getPassword == "" && getUserName=="")
    {
        alert("Enter the valid username & password");
    }
    else
    {
    alert(getUserName+" is the Submitter");
    }

    console.log(getUserName);


});
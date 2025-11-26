const apiUrl = "https://dummy.restapiexample.com/api/v1/employees";

fetch(apiUrl).then(response => {
    
    if (!response.ok) {
      throw new Error("Network response was not ok " + response.statusText);
    }
    return response.json();
  })
  .then(data => {

    console.log("Employee Data:", data);
  })
  .catch(error => {
    
    console.error("Problem with the fetch opertion", error);
  });
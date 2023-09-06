let userInput  = document.getElementById("userInput")
let table = document.getElementById('table')
let startDatePicker = document.getElementById("startDatePicker")
let endDatePicker = document.getElementById("endDatePicker")



function addToList(){

    let newTodo = {
        txt:userInput.value,
        startdate: startDatePicker.value,
        enddate: endDatePicker.value
    }

    fetch("http://localhost:8080/todo", {
        method: "POST",
        body: JSON.stringify(newTodo),
        headers: {
            "Content-type": "application/json"
        }
    })
        .then(response => response.json())
        .then(json => {
            newTodo.id = json.id


            let newIdCell = document.createElement('td')
            newIdCell.textContent = newTodo.id
            newIdCell.className = "idColumn"
            let newTxtCell = document.createElement('td')
            newTxtCell.textContent = newTodo.txt
            newTxtCell.className ="txtColumn"
            let newStartDateCell= document.createElement('td')
            newStartDateCell.textContent = newTodo.startdate
            newStartDateCell.className = "startDateColumn"
            let newEndDateCell = document.createElement('td')
            newEndDateCell.textContent = newTodo.enddate
            newEndDateCell.className = "endDateColumn"


            let newRow = document.createElement('tr')
            newRow.appendChild(newIdCell)
            newRow.appendChild(newTxtCell)
            newRow.appendChild(newStartDateCell)
            newRow.appendChild(newEndDateCell)

            table.appendChild(newRow)

        })
}

function removeElement(element){
    element.remove()
}